package com.example.projetointegrado.reservarSala;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Reservas;
import com.example.projetointegrado.modelos.Sala;
import com.example.projetointegrado.modelos.StatusReserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class ReservarActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;

    Button btReservarSala;
    Button btLimpar;

    TextView tvUsuario;
    TextView tvCargo;

    public ValoresRetornados valores = new ValoresRetornados();

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

        mRecyclerView = findViewById(R.id.rv_Reservar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new AdapterRecyclerView(this, listarSalas(), getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);

        if(listarSalas().size() <= 4){
            mRecyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        btReservarSala = (Button) findViewById(R.id.btReservarSala);
        btReservarSala.setOnClickListener(this);

        btLimpar = (Button) findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(this);

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

    private ArrayList<ModeloRecyclerViewReservar> listarSalas(){

        ArrayList<ModeloRecyclerViewReservar> lista = new ArrayList<>();

        Realm.init(getApplicationContext());

        // Cria a configuração do realm
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        //Busca todos os usuarios cadastrados
        RealmResults<Sala> salas = realm.where(Sala.class)
                .findAll().sort("laboratorio", Sort.DESCENDING);

        for (Sala s : salas){
            Sala sala = new Sala();
            sala.setLaboratorio(s.isLaboratorio());
            sala.setnSala(s.getnSala());
            sala.setProjetor(s.isProjetor());

            ModeloRecyclerViewReservar modelo = new ModeloRecyclerViewReservar();
            modelo.setHeader(((s.isLaboratorio() ? "Laboratório " : "Sala " ) + s.getnSala()));
            modelo.setSala(sala);

            lista.add(modelo);
        }
        realm.close();

        return lista;
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
        // Instancia do objeto e inserir valoresro
        Reservas res = new Reservas();
        res.setUsuario(UsuarioLogado.getUsuarioLogadoRealm(this, UsuarioLogado.usuarioLogado.getEmail()));
        res.setSala(valores.getSala());
        res.setData(valores.getDataHora().getTime());
        res.setStatus(StatusReserva.PENDENTE.getCodigo());

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm banco = Realm.getInstance(config);

        banco.beginTransaction();
        banco.copyToRealm(res);
        banco.commitTransaction();

        banco.close();
        Toast.makeText(this, "Reserva solicitada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
