package com.example.projetointegrado.historico;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.modelos.FiltroHistorico;
import com.example.projetointegrado.modelos.ModeloRecyclerViewHistorico;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;
import com.example.projetointegrado.modelos.Usuario;

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

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.itens_filtro_historico, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spFiltro.setAdapter(adapter);

        spFiltro.setSelection(FiltroHistorico.PENDENTE.getCodigo() - 1);

        mRecyclerView = findViewById(R.id.rv_historico);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        atualizaLista(1);

        spFiltro.setOnItemSelectedListener(this);
    }

    private void atualizaLista(int filtro){
        myAdapter = new AdapterRecyclerView(this, listarReservas(filtro), getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);
    }


    private ArrayList<ModeloRecyclerViewHistorico> listarReservas(int codigo){

        ArrayList<ModeloRecyclerViewHistorico> lista = new ArrayList<>();

        Realm.init(getApplicationContext());

        // Cria a configuração do realm
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        String []fieldNames={"status","data"};
        Sort sort[]={Sort.ASCENDING,Sort.ASCENDING};

        RealmResults<Reservas> reservas = null;

        //Busca todos os usuarios cadastrados
        if(codigo == FiltroHistorico.TODOS.getCodigo()){
            reservas = realm.where(Reservas.class).equalTo("usuario.nome", UsuarioLogado.usuarioLogado.getNome())
                    .findAllSorted(fieldNames, sort);
        }else if(codigo == FiltroHistorico.PENDENTE.getCodigo()){
           reservas = realm.where(Reservas.class)
                    .equalTo("usuario.nome", UsuarioLogado.usuarioLogado.getNome())
                    .equalTo("status", StatusReserva.PENDENTE.getCodigo())
                    .findAllSorted(fieldNames, sort);
        }else if(codigo == FiltroHistorico.APROVADO.getCodigo()) {
            reservas = realm.where(Reservas.class)
                    .equalTo("usuario.nome", UsuarioLogado.usuarioLogado.getNome())
                    .equalTo("status", StatusReserva.APROVADO.getCodigo())
                    .findAllSorted(fieldNames, sort);
        }else if(codigo == FiltroHistorico.RECUSADO.getCodigo()) {
            reservas = realm.where(Reservas.class)
                    .equalTo("usuario.nome", UsuarioLogado.usuarioLogado.getNome())
                    .equalTo("status", StatusReserva.RECUSADO.getCodigo())
                    .findAllSorted(fieldNames, sort);
        }

        for (Reservas s : reservas){

            Sala sala = new Sala();
            sala.setProjetor(s.getSala().isProjetor());
            sala.setLaboratorio(s.getSala().isLaboratorio());
            sala.setnSala(s.getSala().getnSala());

            Usuario usuario = new Usuario();
            usuario.setNome(s.getUsuario().getNome());
            usuario.setEmail(s.getUsuario().getEmail());

            Reservas r = new Reservas();
            r.setStatus(s.getStatus());
            r.setData(s.getData());
            r.setSala(sala);
            r.setUsuario(usuario);

            ModeloRecyclerViewHistorico modelo = new ModeloRecyclerViewHistorico();
            modelo.setReserva(r);

            lista.add(modelo);
        }
        realm.close();

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
}
