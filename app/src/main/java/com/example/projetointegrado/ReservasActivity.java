package com.example.projetointegrado;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.modelos.ModeloRecyclerView;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Usuario;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ReservasActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView mRecyclerView;
    AdapterRecylcerView myAdapter;

    TextView tvUsuario;
    TextView tvCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        tvUsuario = (TextView) findViewById(R.id.tv_usuario_reservas);
        tvCargo = (TextView) findViewById(R.id.tv_cargo_reservas);

        tvUsuario.setText(UsuarioLogado.usuarioLogado.getNome());
        tvCargo.setText(UsuarioLogado.cargo);

        mRecyclerView = findViewById(R.id.rv_Reservas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new AdapterRecylcerView(this, listarReservas());
        mRecyclerView.setAdapter(myAdapter);
    }

    private ArrayList<ModeloRecyclerView> listarReservas(){

        ArrayList<ModeloRecyclerView> lista = new ArrayList<>();

        Realm.init(getApplicationContext());

        // Cria a configuração do realm
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        //Busca todos os usuarios cadastrados
        RealmResults<Reservas> reservas = realm.where(Reservas.class)
                .equalTo("usuario.nome", UsuarioLogado.usuarioLogado.getNome()).findAll();

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
}
