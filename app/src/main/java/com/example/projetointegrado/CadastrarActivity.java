package com.example.projetointegrado;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.modelos.User;
import com.example.projetointegrado.modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    Button btCadastrar;
    EditText etNomeCadastro;
    EditText etEmailCadastro;
    EditText etSenhaCadastro;
    EditText etSenha2Cadastro;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

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

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
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

    private void chamaCadastro(final String nome, final String email, String senha){
        if(verificaEmail(email)) {

            progressDialog = ProgressDialog.show(this,"Cadastrando usuário","Aguarde...",false,false);

            firebaseAuth.createUserWithEmailAndPassword(email, Uteis.MD5(senha)).addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                    if(task.isSuccessful()){

                        User user = new User(email, nome, "Professor");
                        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

                        databaseReference.child(usuario.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(CadastrarActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    progressDialog.dismiss();
                                }
                                else{
                                    Toast.makeText(CadastrarActivity.this, "Falhou!", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(CadastrarActivity.this, "Falhou!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            });
            /*// Instancia do objeto e inserir valores
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

            banco.close();*/



        }else{
            Toast.makeText(this, "Email já cadastrado", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private boolean verificaEmail(String email){
        /*Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);

        RealmResults<Usuario> usuario = realm.where(Usuario.class)
                .equalTo("email", email).findAll();

        if(usuario.size() > 0){
            realm.close();
            return false;
        }
        realm.close();*/
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}
