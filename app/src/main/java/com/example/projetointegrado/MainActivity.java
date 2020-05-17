package com.example.projetointegrado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetointegrado.modelos.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    Button btCadastrar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(this);



        UsuarioLogado.usuarioLogado = new User();

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");

        if(firebaseAuth.getCurrentUser() != null){
            Intent intent = new Intent(this, DashboardActivity.class);
            UsuarioLogado.usuarioLogado = new User();
            UsuarioLogado.usuarioLogado.setEmail( firebaseAuth.getCurrentUser().getEmail() );
            UsuarioLogado.cargo = "Professor";


            startActivityForResult(intent, Requests.LOGAR.getCod());
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btLogin.getId())
            chamaLogin();
        else if(v.getId() == btCadastrar.getId())
            chamaCadastrar();
    }

    private void chamaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }

    private void chamaCadastrar(){
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}
