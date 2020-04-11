package com.example.projetointegrado;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.modelos.Usuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    Button btCadastrar;
    EditText etNomeCadastro;
    EditText etEmailCadastro;
    EditText etSenhaCadastro;
    EditText etSenha2Cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        btCadastrar = (Button) findViewById(R.id.btCadastrar);

        etNomeCadastro = (EditText) findViewById(R.id.etNomeCadastrar);
        etEmailCadastro = (EditText) findViewById(R.id.etEmailCadastrar);
        etSenhaCadastro = (EditText) findViewById(R.id.etSenhaCadastro);
        etSenha2Cadastro = (EditText) findViewById(R.id.etSenha2Cadastro);

        btCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btCadastrar.getId()){
            String email = etEmailCadastro.getText().toString();
            String nome = etNomeCadastro.getText().toString();
            String senha = etSenhaCadastro.getText().toString();
            String senha2 = etSenha2Cadastro.getText().toString();

            if(validaCampos(nome, email, senha, senha2)){
                chamaCadastro(nome, email, senha);
            }
        }
    }

    private boolean validaCampos(String nome, String email, String senha, String senha2){

        if(nome.equals("")){
            Toast.makeText(this, "O nome é obrigatório", Toast.LENGTH_SHORT).show();
            etNomeCadastro.requestFocus();
            return false;
        }
        if(email.equals("")){
            Toast.makeText(this, "O email é obrigatório", Toast.LENGTH_SHORT).show();
            etEmailCadastro.requestFocus();
            return false;
        }
        if(senha.equals("")){
            Toast.makeText(this, "A senha é obrigatória", Toast.LENGTH_SHORT).show();
            etSenhaCadastro.requestFocus();
            return false;
        }
        if(senha2.equals("")){
            Toast.makeText(this, "A confirmação da senha é obrigatória", Toast.LENGTH_SHORT).show();
            etSenha2Cadastro.requestFocus();
            return false;
        }
        if(!senha.equals(senha2)){
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            etSenhaCadastro.requestFocus();
            return false;
        }
        return true;
    }

    private void chamaCadastro(String nome, String email, String senha){
        if(verificaEmail(email)) {
            // Instancia do objeto e inserir valores
            Usuario usu = new Usuario();
            usu.setEmail(email);
            usu.setSenha(senha);
            usu.setNome(nome);

            // Inicializa o Realm
            Realm.init(getApplicationContext());

            // Cria a configuração do realm
            RealmConfiguration config = new RealmConfiguration.Builder().build();
            Realm.setDefaultConfiguration(config);
            Realm banco = Realm.getInstance(config);


            banco.beginTransaction();
            banco.copyToRealm(usu);
            banco.commitTransaction();

            banco.close();

            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Email já cadastrado", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private boolean verificaEmail(String email){
        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        RealmResults<Usuario> usuario = realm.where(Usuario.class)
                .equalTo("email", email).findAll();

        if(usuario.size() > 0){
            realm.close();
            return false;
        }
        realm.close();
        return true;
    }
}
