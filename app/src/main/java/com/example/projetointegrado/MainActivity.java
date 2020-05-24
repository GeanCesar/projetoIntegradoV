package com.example.projetointegrado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetointegrado.modelos.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    Button btCadastrar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseUsuario;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(this);

        UsuarioLogado.usuarioLogado = new User();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");
        databaseUsuario.equalTo(UsuarioLogado.usuarioLogado.getEmail(), "email");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");

        progressDialog = ProgressDialog.show(this,"Verificando login","Aguarde...",false,false);

        if(firebaseAuth.getCurrentUser() != null) {
            databaseUsuario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()) {
                        User usuario = usuarioSnapshot.getValue(User.class);

                        if (usuario.getEmail().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())) {
                            UsuarioLogado.usuarioLogado.setNome(usuario.getNome());
                            UsuarioLogado.cargo = usuario.getCargo();
                            UsuarioLogado.usuarioLogado.setEmail(firebaseAuth.getCurrentUser().getEmail());

                            progressDialog.dismiss();

                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

                            startActivityForResult(intent, Requests.LOGAR.getCod());
                            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
                        }
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }else{
            progressDialog.dismiss();
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
        startActivityForResult(intent, Requests.CADASTRAR.getCod());
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(firebaseAuth.getCurrentUser() != null) {
                databaseUsuario.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()) {
                            User usuario = usuarioSnapshot.getValue(User.class);

                            if (usuario.getEmail().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())) {
                                UsuarioLogado.usuarioLogado.setNome(usuario.getNome());
                                UsuarioLogado.cargo = usuario.getCargo();
                                UsuarioLogado.usuarioLogado.setEmail(firebaseAuth.getCurrentUser().getEmail());

                                progressDialog.dismiss();

                                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

                                startActivityForResult(intent, Requests.LOGAR.getCod());
                                overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
                            }
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }else{
                progressDialog.dismiss();
            }
        }
    }

}
