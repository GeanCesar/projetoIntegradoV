package com.example.projetointegrado.aprovarReserva;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.modelos.ModeloRecyclerViewAprovar;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;
import com.example.projetointegrado.modelos.Usuario;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AprovarActivity extends AppCompatActivity implements View.OnClickListener, DialogAprovar.BottomSheetListener {

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;

    public ValoresRetornados valores = new ValoresRetornados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovar);

        mRecyclerView = findViewById(R.id.rv_aprovar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        atualizaListView();

        if(listarReservas().size() <= 4){
            mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

    }

    @Override
    public void onClick(View v) {

    }

    private ArrayList<ModeloRecyclerViewAprovar> listarReservas(){

        ArrayList<ModeloRecyclerViewAprovar> lista = new ArrayList<>();

        Realm.init(getApplicationContext());

        // Cria a configuração do realm
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        //Busca todos os usuarios cadastrados
        RealmResults<Reservas> reservas = realm.where(Reservas.class).equalTo("status", StatusReserva.PENDENTE.getCodigo())
                .findAll();

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

            ModeloRecyclerViewAprovar modelo = new ModeloRecyclerViewAprovar();
            modelo.setReserva(r);

            lista.add(modelo);
        }
        realm.close();

        return lista;
    }

    public void resultadoAprovacao(String resultado, Reservas reservas){
        if(resultado.equalsIgnoreCase("Aprovado")){
            insereNoBanco(true, reservas);
        }else if(resultado.equalsIgnoreCase("Recusado")){
            insereNoBanco(false, reservas);
        }else{
            return;
        }
    }

    private void insereNoBanco(boolean aprovado, Reservas reserva){

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm banco = Realm.getInstance(config);

        banco.beginTransaction();

        Reservas r = banco.where(Reservas.class)
                .equalTo("data", reserva.getData())
                .equalTo("usuario.email", reserva.getUsuario().getEmail())
                .equalTo("sala.nSala", reserva.getSala().getnSala()).findFirst();

        if(aprovado)
            r.setStatus(StatusReserva.APROVADO.getCodigo());
        else
            r.setStatus(StatusReserva.RECUSADO.getCodigo());

        banco.copyToRealm(r);
        banco.commitTransaction();

        banco.close();

        if(aprovado)
            Toast.makeText(this, "Reserva aprovada com sucesso!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Reserva recusada com sucesso!", Toast.LENGTH_SHORT).show();

        atualizaListView();
    }

    private void atualizaListView(){
        myAdapter = new AdapterRecyclerView(this, listarReservas(), getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);
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
