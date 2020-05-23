package com.example.projetointegrado;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.modelos.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            String email = etEmailCadastro.getText().toString().trim();
            String nome = etNomeCadastro.getText().toString().trim();
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

        progressDialog = ProgressDialog.show(this,"Cadastrando usuário","Aguarde...",false,false);

        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                if(task.isSuccessful()){

                    User user = new User(email, nome, nome.equalsIgnoreCase("Admin") ? "Administrador" : "Professor");
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
                                progressDialog.dismiss();
                            }
                        }
                    });
                }else{

                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        etSenhaCadastro.setError("Senha fraca");
                        etSenhaCadastro.requestFocus();
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        etEmailCadastro.setError("Email inválido");
                        etEmailCadastro.requestFocus();
                    } catch(FirebaseAuthUserCollisionException e) {
                        etEmailCadastro.setError("Email ja utilizado");
                        etEmailCadastro.requestFocus();
                    } catch (Exception e){

                    }

                    progressDialog.dismiss();
                }

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}
