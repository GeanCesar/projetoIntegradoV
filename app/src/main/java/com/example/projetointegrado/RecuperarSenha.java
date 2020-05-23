package com.example.projetointegrado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecuperarSenha extends AppCompatActivity implements View.OnClickListener {

    EditText etEmailRecuperar;

    Button btEnviarEmail;

    LinearLayout llEmail;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        etEmailRecuperar = (EditText) findViewById(R.id.etEmailRecuperar);

        btEnviarEmail = (Button) findViewById(R.id.btEnviarEmailRecuperar);

        btEnviarEmail.setOnClickListener(this);

        llEmail = (LinearLayout) findViewById(R.id.ll_EmailRecuperar);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btEnviarEmail.getId()){
           validaCampos();
        }
    }

    private void validaCampos(){
        String email = etEmailRecuperar.getText().toString();
        if(!email.isEmpty()){
            firebaseAuth.sendPasswordResetEmail(email);
            Toast.makeText(this, "Email enviado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}
