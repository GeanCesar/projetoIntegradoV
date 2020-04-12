package com.example.projetointegrado.cadastraSala;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.R;
import com.example.projetointegrado.UsuarioLogado;
import com.example.projetointegrado.Uteis;
import com.example.projetointegrado.modelos.Sala;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class CadastrarSalaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_usuario_salas;
    TextView tv_cargo_salas;
    EditText etSala;
    Switch sw_projetor;
    Switch sw_laboratorio;
    Button bt_cadastrarSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_sala);

        tv_usuario_salas = (TextView) findViewById(R.id.tv_usuario_salas);
        tv_cargo_salas = (TextView) findViewById(R.id.tv_cargo_salas);

        tv_usuario_salas.setText(UsuarioLogado.usuarioLogado.getNome());
        tv_cargo_salas.setText(UsuarioLogado.cargo);

        etSala = (EditText) findViewById(R.id.etNSala);

        sw_projetor = (Switch) findViewById(R.id.sw_Projetor);
        sw_laboratorio = (Switch) findViewById(R.id.sw_Laboratorio);

        bt_cadastrarSala = (Button) findViewById(R.id.btCadastrarSala);
        bt_cadastrarSala.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == bt_cadastrarSala.getId()){
            cadastrar();
        }

    }

    private void cadastrar(){
        if(consisteDados()){

            boolean laboratorio = sw_laboratorio.isChecked();
            boolean projetor = sw_projetor.isChecked();
            int nSala = Uteis.converteStringToInt(etSala.getText().toString());


            if(!existe(laboratorio, nSala)){
                Sala sala = new Sala();
                sala.setnSala(nSala);
                sala.setProjetor(projetor);
                sala.setLaboratorio(laboratorio);

                // Inicializa o Realm
                Realm.init(getApplicationContext());

                // Cria a configuração do realm
                RealmConfiguration config = new RealmConfiguration.Builder().build();
                Realm.setDefaultConfiguration(config);
                Realm banco = Realm.getInstance(config);


                banco.beginTransaction();
                banco.copyToRealm(sala);
                banco.commitTransaction();

                banco.close();
                Toast.makeText(this, "Cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Sala já cadastrada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean existe(boolean laboratorio, int numero){

        // Inicializa o Realm
        Realm.init(getApplicationContext());

        // Cria a configuração do realm
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm banco = Realm.getInstance(config);

        Sala s = banco.where(Sala.class).equalTo("laboratorio", laboratorio)
                .equalTo("nSala", numero).findFirst();

        if(s == null){
            return false;
        }

        return true;
    }


    private boolean consisteDados(){
        if(Uteis.converteStringToInt(etSala.getText().toString())==0){
            Toast.makeText(this, "O número da sala é obrigatório", Toast.LENGTH_SHORT).show();
            etSala.requestFocus();
            return false;
        }
        if(Uteis.converteStringToInt(etSala.getText().toString()) < 1){
            Toast.makeText(this, "Número da sala inválido", Toast.LENGTH_SHORT).show();
            etSala.requestFocus();
            return false;
        }
        return true;
    }
}
