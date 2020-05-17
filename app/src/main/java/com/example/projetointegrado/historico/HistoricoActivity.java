package com.example.projetointegrado.historico;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.aprovarReserva.AprovarActivity;
import com.example.projetointegrado.modelos.FiltroHistorico;
import com.example.projetointegrado.modelos.ModeloRecyclerViewAprovar;
import com.example.projetointegrado.modelos.ModeloRecyclerViewHistorico;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;
import com.example.projetointegrado.modelos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class HistoricoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView tvUsuario;
    TextView tvCargo;

    Spinner spFiltro;

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;
    LinearLayout llNenhum;

    DatabaseReference databaseReservas;
    ArrayList<ModeloRecyclerViewHistorico> lista = new ArrayList<>();

    int filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        String nome = UsuarioLogado.usuarioLogado.getNome();
        String cargo = UsuarioLogado.cargo;

        tvUsuario = (TextView) findViewById(R.id.tv_usuario_historico);
        tvUsuario.setText(nome);

        tvCargo = (TextView) findViewById(R.id.tv_cargo_historico);
        tvCargo.setText(cargo);

        spFiltro = (Spinner) findViewById(R.id.sp_FiltroHist);

        llNenhum = (LinearLayout) findViewById(R.id.llNenhumHistorico);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.itens_filtro_historico, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spFiltro.setAdapter(adapter);

        filtro = FiltroHistorico.TODOS.getCodigo();
        spFiltro.setSelection(FiltroHistorico.TODOS.getCodigo() - 1);

        databaseReservas = FirebaseDatabase.getInstance().getReference("Reservas");
        databaseReservas.orderByChild("status/data");

        mRecyclerView = findViewById(R.id.rv_historico);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        atualizaLista(1);

        spFiltro.setOnItemSelectedListener(this);
    }

    private void atualizaLista(int filtro){
        this.filtro = filtro;
        myAdapter = new AdapterRecyclerView(this, listarReservas(), getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);
        if(listarReservas().size() > 0){
            llNenhum.setVisibility(View.GONE);
        }else{
            llNenhum.setVisibility(View.VISIBLE);
        }
    }


    private ArrayList<ModeloRecyclerViewHistorico> listarReservas(){

        databaseReservas.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lista = new ArrayList();

                for(DataSnapshot s : dataSnapshot.getChildren()){

                    Reservas reserva = s.getValue(Reservas.class);
                    ModeloRecyclerViewHistorico m = new ModeloRecyclerViewHistorico();

                    if(filtro == FiltroHistorico.TODOS.getCodigo()){

                        if(reserva.getUsuario().getEmail().equalsIgnoreCase(UsuarioLogado.usuarioLogado.getEmail())){
                            m.setReserva(reserva);
                            lista.add(m);
                        }

                    }else if(filtro == FiltroHistorico.PENDENTE.getCodigo()){
                        if(reserva.getUsuario().getEmail().equalsIgnoreCase(UsuarioLogado.usuarioLogado.getEmail())){
                            if(reserva.getStatus() == StatusReserva.PENDENTE.getCodigo()){
                                m.setReserva(reserva);
                                lista.add(m);
                            }
                        }
                    }else if(filtro == FiltroHistorico.APROVADO.getCodigo()) {
                        if(reserva.getUsuario().getEmail().equalsIgnoreCase(UsuarioLogado.usuarioLogado.getEmail())){
                            if(reserva.getStatus() == StatusReserva.APROVADO.getCodigo()){
                                m.setReserva(reserva);
                                lista.add(m);
                            }
                        }
                    }else if(filtro == FiltroHistorico.RECUSADO.getCodigo()) {
                        if(reserva.getUsuario().getEmail().equalsIgnoreCase(UsuarioLogado.usuarioLogado.getEmail())){
                            if(reserva.getStatus() == StatusReserva.RECUSADO.getCodigo()){
                                m.setReserva(reserva);
                                lista.add(m);
                            }
                        }
                    }
                }

                if(lista.size() == 0){
                    llNenhum.setVisibility(View.VISIBLE);
                }else{
                    llNenhum.setVisibility(View.GONE);
                }

                if(lista.size() <= 4){
                    mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                myAdapter = new AdapterRecyclerView(HistoricoActivity.this, lista, getSupportFragmentManager());
                mRecyclerView.setAdapter(myAdapter);

                if(lista.size() > 0){
                    llNenhum.setVisibility(View.GONE);
                }else{
                    llNenhum.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });


        return lista;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        atualizaLista(position + 1 );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
