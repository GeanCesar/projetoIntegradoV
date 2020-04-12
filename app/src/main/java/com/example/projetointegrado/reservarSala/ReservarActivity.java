package com.example.projetointegrado.reservarSala;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetointegrado.R;
import com.example.projetointegrado.modelos.ModeloRecyclerViewReservar;
import com.example.projetointegrado.modelos.Sala;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class ReservarActivity extends AppCompatActivity implements View.OnClickListener {


    public CalendarView calendario;

    RecyclerView mRecyclerView;
    AdapterRecyclerView myAdapter;

    public ValoresRetornados valores = new ValoresRetornados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);


        mRecyclerView = findViewById(R.id.rv_Reservar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new AdapterRecyclerView(this, listarSalas(), getSupportFragmentManager());
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {

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

    private void teste(){
        Toast.makeText(this, valores.getDataHora()  + "", Toast.LENGTH_SHORT).show();
    }

    public void chamaCalendario(){
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
        datePickerDialog.show();
    }

    public void chamaHora(){
        Calendar c = valores.getDataHora();

        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c = valores.getDataHora();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                valores.setDataHora(c);
                teste();
            }
        }, hora, minutos, false);
        timePickerDialog.show();
    }
}
