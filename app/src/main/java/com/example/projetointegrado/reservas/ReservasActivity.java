package com.example.projetointegrado.reservas;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ModeloRecyclerView;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;
import com.example.projetointegrado.reservarSala.AdapterRecyclerView;
import com.example.projetointegrado.reservarSala.ReservarActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class ReservasActivity extends AppCompatActivity implements View.OnClickListener, DialogDetalhes.BottomSheetListener{

    RecyclerView mRecyclerView;
    AdapterRecylcerView myAdapter;

    TextView tvUsuario;
    TextView tvCargo;

    LinearLayout llNenhum;
    ArrayList<ModeloRecyclerView> lista = new ArrayList<>();

    DatabaseReference databaseReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        tvUsuario = (TextView) findViewById(R.id.tv_usuario_reservas);
        tvCargo = (TextView) findViewById(R.id.tv_cargo_reservas);

        tvUsuario.setText(UsuarioLogado.usuarioLogado.getNome());
        tvCargo.setText(UsuarioLogado.cargo);

        llNenhum = (LinearLayout) findViewById(R.id.llNenhumReservas);

        mRecyclerView = findViewById(R.id.rv_Reservas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReservas = FirebaseDatabase.getInstance().getReference("Reservas");
        databaseReservas.child("sala").orderByChild("laboratorio");

        databaseReservas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lista = new ArrayList();

                for(DataSnapshot s : dataSnapshot.getChildren()){
                    Reservas reserva = s.getValue(Reservas.class);
                    if(reserva.getStatus() == StatusReserva.APROVADO.getCodigo() && reserva.getUsuario().getEmail().equalsIgnoreCase(UsuarioLogado.getUsuarioLogado().getEmail())){

                        Calendar filtroData = Calendar.getInstance();
                        filtroData.setTime(reserva.getData());
                        filtroData.add(Calendar.DATE, +10);

                        if(Calendar.getInstance().compareTo(filtroData) < 1){
                            ModeloRecyclerView modelo = new ModeloRecyclerView();
                            modelo.setHeader((reserva.getSala().isLaboratorio() ? "Laboratório " : "Sala ") +  reserva.getSala().getnSala() + "");
                            modelo.setContent(Uteis.converteDataHora(reserva.getData()));
                            modelo.setReservas(reserva);
                            lista.add(modelo);
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

                myAdapter = new AdapterRecylcerView(ReservasActivity.this, lista, getSupportFragmentManager());
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
    }

    @Override
    public void onClick(View v) {}

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }

    @Override
    public void onButtonClicker(String text, Reservas reservas) {

    }
}
