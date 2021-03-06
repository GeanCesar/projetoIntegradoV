package com.example.projetointegrado.aprovarReserva;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ControleDataReservas;
import com.example.projetointegrado.modelos.ModeloRecyclerViewAprovar;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.StatusReserva;
import com.example.projetointegrado.reservarSala.ReservarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AprovarActivity extends AppCompatActivity implements View.OnClickListener, DialogAprovar.BottomSheetListener {

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;

    TextView tvUsuario;
    TextView tvCargo;
    LinearLayout llNenhuma;

    DatabaseReference databaseReservas;

    ArrayList<ModeloRecyclerViewAprovar> lista = new ArrayList<>();
    ArrayList<ControleDataReservas> listaReservas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovar);

        String nome = UsuarioLogado.usuarioLogado.getNome();
        String cargo = UsuarioLogado.cargo;

        tvUsuario = (TextView) findViewById(R.id.tv_usuario_aprovar);
        tvUsuario.setText(nome);

        tvCargo = (TextView) findViewById(R.id.tv_cargo_aprovar);
        tvCargo.setText(cargo);

        mRecyclerView = findViewById(R.id.rv_aprovar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        llNenhuma = (LinearLayout) findViewById(R.id.llNenhumAprovar);


        databaseReservas = FirebaseDatabase.getInstance().getReference("Reservas");
        databaseReservas.orderByChild("laboratorio/nSala");

        atualizaListView();

        if(lista.size() <= 4){
            mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }



        databaseReservas.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lista = new ArrayList();
                listaReservas = new ArrayList();

                for(DataSnapshot s : dataSnapshot.getChildren()){
                    Reservas reserva = s.getValue(Reservas.class);

                    ControleDataReservas controle = new ControleDataReservas();

                    Calendar data = Calendar.getInstance();
                    data.setTime(reserva.getData());

                    Calendar dataAntes = Calendar.getInstance();
                    dataAntes.setTime(data.getTime());
                    dataAntes.add(Calendar.HOUR, -1);
                    dataAntes.add(Calendar.MINUTE, -30);
                    dataAntes.set(Calendar.SECOND, 0);

                    Calendar dataDepois = Calendar.getInstance();
                    dataDepois.setTime(data.getTime());
                    dataDepois.add(Calendar.HOUR, 1);
                    dataDepois.add(Calendar.MINUTE, 30);
                    dataDepois.set(Calendar.SECOND, 0);

                    controle.setReserva(reserva);
                    controle.setDataAntes(dataAntes);
                    controle.setDataDepois(dataDepois);

                    listaReservas.add(controle);

                    if(reserva.getStatus() == StatusReserva.PENDENTE.getCodigo()){
                        ModeloRecyclerViewAprovar modelo = new ModeloRecyclerViewAprovar();
                        modelo.setReserva(reserva);
                        lista.add(modelo);
                    }

                }

                if(lista.size() == 0){
                    llNenhuma.setVisibility(View.VISIBLE);
                }else{
                    llNenhuma.setVisibility(View.GONE);
                }

                if(lista.size() <= 4){
                    mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                myAdapter = new AdapterRecyclerView(AprovarActivity.this, lista, getSupportFragmentManager());
                mRecyclerView.setAdapter(myAdapter);

                if(lista.size() > 0){
                    llNenhuma.setVisibility(View.GONE);
                }else{
                    llNenhuma.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    @Override
    public void onClick(View v) {}


    public void resultadoAprovacao(String resultado, Reservas reservas){
        if(resultado.equalsIgnoreCase("Aprovado")){

            boolean ok = true;
            Calendar data = Calendar.getInstance();
            data.setTime(reservas.getData());
            for (int i = 0; i < listaReservas.size(); i++){
                if(listaReservas.get(i).getReserva().getStatus() == StatusReserva.APROVADO.getCodigo()){
                    if(listaReservas.get(i).getReserva().getSala().getnSala() == reservas.getSala().getnSala()){
                        if(listaReservas.get(i).getReserva().getSala().isLaboratorio() == reservas.getSala().isLaboratorio()) {
                            if ((listaReservas.get(i).getDataAntes().compareTo(data) <= 0) && (listaReservas.get(i).getDataDepois().compareTo(data) >= 0)) {
                                ok = false;
                            }
                        }
                    }
                }
            }

            if(ok){
                insereNoBanco(true, reservas);
                String mensagem;
                mensagem = "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<title></title>" +
                        "<style type='text/css'>"+
                        "*{" +
                        "font-family: 'Arial';" +
                        "}" +
                        "h3, h4{" +
                        "font-weight: normal" +
                        "}"+
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<h3>Olá, <b>  " + reservas.getUsuario().getNome() + " </b> </h3>" +
                        "<h4>O administrador analisou seu pedido da reserva  <b> " + (reservas.getSala().isLaboratorio() ? " </b> do laboratório: <b> Nº " : "</b> da sala: <b> Nº " ) + reservas.getSala().getnSala() + " </b> no dia <b> " + Uteis.converteDataHora(reservas.getData()) + " </b>, e aprovou sua solicitação. </h4>" +
                        "<h3>Parabéns, agora basta utilizá-la no dia solicitado!</h3>" +
                        "</body>" +
                        "</html>";

                Uteis.enviarEmail(reservas.getUsuario().getEmail(), "Resultado solicitação reserva", mensagem, this);
            }else{
                Toast.makeText(this, "Falhou!\nA sala selecionada já está reservada no periodo solicitado", Toast.LENGTH_LONG).show();
            }
        }else if(resultado.equalsIgnoreCase("Recusado")){
            insereNoBanco(false, reservas);
            String mensagem;
            mensagem = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title></title>" +
                    "<style type='text/css'>"+
                    "*{" +
                        "font-family: 'Arial';" +
                    "}" +
                    "h3, h4{" +
                        "font-weight: normal" +
                    "}"+
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h3>Olá, <b>  " + reservas.getUsuario().getNome() + " </b> </h3>" +
                    "<h4>O administrador analisou seu pedido da reserva  <b> " + (reservas.getSala().isLaboratorio() ? " </b> do laboratório: <b> Nº " : "</b> da sala: <b> Nº " ) + reservas.getSala().getnSala() + " </b> no dia <b> " + Uteis.converteDataHora(reservas.getData()) + " </b>, e não encontrou disponibilidade no dia, hora e local solicitado. </h4>" +
                    "<h3>Tente uma outra sala ou um outro dia!</h3>" +
                    "</body>" +
                    "</html>";
            Uteis.enviarEmail(reservas.getUsuario().getEmail(), "Resultado solicitação reserva", mensagem, this);
        }else{
            return;
        }
    }

    private void insereNoBanco(boolean aprovado, Reservas reserva){

        if(aprovado)
            reserva.setStatus(StatusReserva.APROVADO.getCodigo());
        else
            reserva.setStatus(StatusReserva.RECUSADO.getCodigo());

        reserva.setAprovador(UsuarioLogado.getUsuarioLogado());

        databaseReservas.child(reserva.getPk()).setValue(reserva).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AprovarActivity.this, "Aprovado com sucesso!", Toast.LENGTH_SHORT).show();
                    atualizaListView();
                }
                else{
                    Toast.makeText(AprovarActivity.this, "Falhou!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void atualizaListView(){
        myAdapter = new AdapterRecyclerView(this, lista, getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);

        if(lista.size() == 0){
            llNenhuma.setVisibility(View.VISIBLE);
        }else{
            llNenhuma.setVisibility(View.GONE);
        }
    }

    @Override
    public void onButtonClicker(String text, Reservas reservas) {
        resultadoAprovacao(text, reservas);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
