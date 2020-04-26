package com.example.projetointegrado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(this);
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
