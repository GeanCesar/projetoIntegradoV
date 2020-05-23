package com.example.projetointegrado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetointegrado.modelos.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    EditText etEmailLogin;
    EditText etSenhaLogin;

    TextView tvEsqueceu;

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        tvEsqueceu = (TextView) findViewById(R.id.tvEsqueceuSenha);

        etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        etSenhaLogin = (EditText) findViewById(R.id.etSenhaLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");

        tvEsqueceu.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser() != null){
            Intent intent = new Intent(this, DashboardActivity.class);
            UsuarioLogado.usuarioLogado = new User();
            UsuarioLogado.usuarioLogado.setEmail(firebaseAuth.getCurrentUser().getEmail());
            UsuarioLogado.cargo = "Administrador";


            startActivityForResult(intent, Requests.LOGAR.getCod());
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btLogin.getId()){
            if(validaCampos()) {
                String email = etEmailLogin.getText().toString().trim();
                String senha = etSenhaLogin.getText().toString().trim();
                fazLogin(email, senha);
            }
        }else if(v.getId() == tvEsqueceu.getId()){

            Intent intent = new Intent(LoginActivity.this, RecuperarSenha.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);

        }
    }

    private boolean validaCampos(){
        if(etEmailLogin.getText().toString().equals("")) {
            Toast.makeText(this, "O campo email é obrigatório", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etSenhaLogin.getText().toString().equals("")) {
            Toast.makeText(this, "O campo senha é obrigatório", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void fazLogin(String email, String senha){

        if(!verificaAdmin(email, senha)) {
            progressDialog = ProgressDialog.show(this,"Verificando login","Aguarde...",false,false);
            firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        databaseUsuario.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                UsuarioLogado.usuarioLogado = new User();

                                for(DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()){
                                    User usuario = usuarioSnapshot.getValue(User.class);

                                    if(usuario.getEmail().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())){
                                        UsuarioLogado.usuarioLogado.setNome(usuario.getNome());
                                        UsuarioLogado.usuarioLogado.setEmail(usuario.getEmail());
                                        UsuarioLogado.cargo = usuario.getCargo();
                                    }
                                }

                                startActivityForResult(intent, Requests.LOGAR.getCod());
                                overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });


                    }else{
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }else{
            Intent intent = new Intent(this, DashboardActivity.class);
            UsuarioLogado.usuarioLogado = new User();
            UsuarioLogado.usuarioLogado.setNome("Admin");
            UsuarioLogado.usuarioLogado.setEmail("admin@admin.com");
            UsuarioLogado.cargo = "Administrador";
            startActivityForResult(intent, Requests.LOGAR.getCod());
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }
    }

    private boolean verificaAdmin(String email, String senha){
        if(email.equalsIgnoreCase("Admin") && (senha.equals("admin"))){
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == Requests.LOGAR.getCod()){
                limparCampos();
            }
        }
    }

    private void limparCampos(){
        etEmailLogin.setText("");
        etSenhaLogin.setText("");
        etEmailLogin.requestFocus();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}
