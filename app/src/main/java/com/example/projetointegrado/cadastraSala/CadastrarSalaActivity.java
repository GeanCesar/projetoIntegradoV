package com.example.projetointegrado.cadastraSala;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.R;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.Sala;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CadastrarSalaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_usuario_salas;
    TextView tv_cargo_salas;
    EditText etSala;
    Switch sw_projetor;
    Switch sw_laboratorio;
    Button bt_cadastrarSala;

    DatabaseReference databaseSala;
    boolean cadastrou = false;
    private ProgressDialog progressDialog;
    private Sala salaACadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_sala);

        tv_usuario_salas = (TextView) findViewById(R.id.tv_usuario_salas);
        tv_cargo_salas = (TextView) findViewById(R.id.tv_cargo_salas);

        etSala = (EditText) findViewById(R.id.etNSala);

        sw_projetor = (Switch) findViewById(R.id.sw_Projetor);
        sw_laboratorio = (Switch) findViewById(R.id.sw_Laboratorio);

        bt_cadastrarSala = (Button) findViewById(R.id.btCadastrarSala);
        bt_cadastrarSala.setOnClickListener(this);

        databaseSala = FirebaseDatabase.getInstance().getReference("Salas");
        databaseSala.orderByChild("laboratorio/nSala");
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == bt_cadastrarSala.getId()){
            if(consisteDados()){
                boolean laboratorio = sw_laboratorio.isChecked();
                boolean projetor = sw_projetor.isChecked();
                int nSala = Uteis.converteStringToInt(etSala.getText().toString());

                salaACadastrar = new Sala();
                salaACadastrar.setnSala(nSala);
                salaACadastrar.setLaboratorio(laboratorio);
                salaACadastrar.setProjetor(projetor);

                existe();
            }
        }

    }

    private void cadastrar(){
        if(consisteDados()){

            boolean laboratorio = sw_laboratorio.isChecked();
            boolean projetor = sw_projetor.isChecked();
            int nSala = Uteis.converteStringToInt(etSala.getText().toString());

            Sala sala = new Sala();
            sala.setnSala(nSala);
            sala.setProjetor(projetor);
            sala.setLaboratorio(laboratorio);

            if(!this.isFinishing()) {
                progressDialog = ProgressDialog.show(this, "Cadastrando sala", "Aguarde...", false, false);
            }

            cadastrou = true;

            databaseSala.child((sala.isLaboratorio() ? "Laboratorio-" : "Sala-" ) + sala.getnSala()).setValue(sala).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(CadastrarSalaActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                    else{
                        Toast.makeText(CadastrarSalaActivity.this, "Falhou!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        cadastrou = false;
                    }
                }
            });
        }
    }

    private void existe(){

        databaseSala.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    cadastrar();
                }
                else{
                    boolean encontrou = false;
                    for(DataSnapshot s : dataSnapshot.getChildren()){
                        Sala sala = s.getValue(Sala.class);

                        if(sala!=null){
                            if (sala.isLaboratorio() == salaACadastrar.isLaboratorio() && sala.getnSala() == salaACadastrar.getnSala()) {
                                encontrou = true;
                                if(!cadastrou){
                                    Toast.makeText(CadastrarSalaActivity.this, "Sala já cadastrada", Toast.LENGTH_SHORT).show();
                                    continue;
                                }

                            }
                        }else{
                            cadastrar();
                            limpaDataBase();
                        }
                    }

                    if(encontrou==false){
                        cadastrar();
                        limpaDataBase();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}

        });
    }

    private void limpaDataBase(){
        databaseSala.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private boolean consisteDados(){
        if(Uteis.converteStringToInt(etSala.getText().toString())==0){
            etSala.setError("O número da sala é obrigatório");
            etSala.requestFocus();
            return false;
        }
        if(Uteis.converteStringToInt(etSala.getText().toString()) < 1){
            etSala.setError("Número da sala inválido");
            etSala.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }
}
