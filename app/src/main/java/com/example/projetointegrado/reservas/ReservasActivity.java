package com.example.projetointegrado.reservas;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.ModeloRecyclerView;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.StatusReserva;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class ReservasActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView mRecyclerView;
    AdapterRecylcerView myAdapter;

    TextView tvUsuario;
    TextView tvCargo;

    LinearLayout llNenhum;

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

        listaReservas();

    }

    private void listaReservas(){
        myAdapter = new AdapterRecylcerView(this, listarReservas());
        mRecyclerView.setAdapter(myAdapter);

        if(listarReservas().size() > 0){
            llNenhum.setVisibility(View.GONE);
        }else{
            llNenhum.setVisibility(View.VISIBLE);
        }
    }

    private ArrayList<ModeloRecyclerView> listarReservas(){

        ArrayList<ModeloRecyclerView> lista = new ArrayList<>();

        Realm.init(getApplicationContext());

        // Cria a configuração do realmw
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        //Busca todos as reservas confirmadas
        RealmResults<Reservas> reservas = realm.where(Reservas.class)
                .equalTo("usuario.email", UsuarioLogado.usuarioLogado.getEmail())
                .equalTo("status", StatusReserva.APROVADO.getCodigo()).findAll()
                .sort("sala.laboratorio", Sort.DESCENDING);


        for (Reservas r : reservas){
            ModeloRecyclerView modelo = new ModeloRecyclerView();
            modelo.setHeader((r.getSala().isLaboratorio() ? "Laboratório " : "Sala ") +  r.getSala().getnSala() + "");
            modelo.setContent(Uteis.converteDataHora(r.getData()));

            lista.add(modelo);
        }
        realm.close();

        return lista;

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
