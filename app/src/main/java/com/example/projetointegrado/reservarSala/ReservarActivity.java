package com.example.projetointegrado.reservarSala;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.CadastrarActivity;
import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReservarActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;

    Button btReservarSala;
    Button btLimpar;

    TextView tvUsuario;
    TextView tvCargo;

    LinearLayout llNenhum;

    public ValoresRetornados valores = new ValoresRetornados();
    public ArrayList<ModeloRecyclerViewReservar> lista = new ArrayList<>();

    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReserva;
    DatabaseReference databaseSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        String nome = UsuarioLogado.usuarioLogado.getNome();
        String cargo = UsuarioLogado.cargo;

        tvUsuario = (TextView) findViewById(R.id.tv_usuario_reservar);
        tvUsuario.setText(nome);

        tvCargo = (TextView) findViewById(R.id.tv_cargo_reservar);
        tvCargo.setText(cargo);

        llNenhum = (LinearLayout) findViewById(R.id.llNenhumReservar);

        mRecyclerView = findViewById(R.id.rv_Reservar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btReservarSala = (Button) findViewById(R.id.btReservarSala);
        btReservarSala.setOnClickListener(this);

        btLimpar = (Button) findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(this);

        databaseSala = FirebaseDatabase.getInstance().getReference("Salas");
        databaseReserva = FirebaseDatabase.getInstance().getReference("Reservas");

        databaseSala.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lista = new ArrayList();

                for(DataSnapshot s : dataSnapshot.getChildren()){
                    Sala sala = s.getValue(Sala.class);
                    ModeloRecyclerViewReservar modelo = new ModeloRecyclerViewReservar();
                    modelo.setHeader((sala.isLaboratorio() ? "Laboratório " : "Sala ") + sala.getnSala());
                    modelo.setSala(sala);
                    lista.add(modelo);
                }

                if(lista.size() == 0){
                    llNenhum.setVisibility(View.VISIBLE);
                }else{
                    llNenhum.setVisibility(View.GONE);
                }

                if(lista.size() <= 4){
                    mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                myAdapter = new AdapterRecyclerView(ReservarActivity.this, lista, getSupportFragmentManager());
                mRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btLimpar.getId()){
            showButtons(View.GONE);
            valores = new ValoresRetornados();
        }else if(v.getId() == btReservarSala.getId()){
            if(consisteDados()){
                insereNoBanco();
            }
        }
    }


    public void chamaCalendario(){
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.CalendarioAzul, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/" + month + "/" + year;
                String parts[] = date.split("/");

                int dia = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int ano = Integer.parseInt(parts[2]);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, ano);
                calendar.set(Calendar.MONTH, mes);
                calendar.set(Calendar.DAY_OF_MONTH, dia);

                valores.setDataHora(calendar);

                chamaHora();
            }
        }, ano, mes, dia);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }

    public void chamaHora(){
        Calendar c = valores.getDataHora();

        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.CalendarioAzul, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c = valores.getDataHora();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                valores.setDataHora(c);
                showButtons(View.VISIBLE);
            }
        }, hora, minutos, false);
        timePickerDialog.show();
    }

    private void showButtons(int visibilidade){
        btReservarSala.setVisibility(visibilidade);
        btLimpar.setVisibility(visibilidade);
    }

    private boolean consisteDados(){
        if(valores.getSala() == null){
            Toast.makeText(this, "Sala não selecionada", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(valores.getDataHora() == null){
            Toast.makeText(this, "Data ou hora não selecionados", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    private void insereNoBanco(){

        progressDialog = ProgressDialog.show(this,"Cadastrando usuário","Aguarde...",false,false);

        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

        String chave = Uteis.gerarChave() + UsuarioLogado.getUsuarioLogado().getNome();

        Reservas res = new Reservas();
        res.setUsuario(UsuarioLogado.getUsuarioLogado());
        res.setSala(valores.getSala());
        res.setData(valores.getDataHora().getTime());
        res.setStatus(StatusReserva.PENDENTE.getCodigo());
        res.setUsuario(UsuarioLogado.usuarioLogado);
        res.setPk(chave);

        databaseReserva.child(chave).setValue(res).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ReservarActivity.this, "Reserva solicitada com sucesso!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();

                }
                else{
                    Toast.makeText(ReservarActivity.this, "Falhou!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
