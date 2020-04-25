package com.example.projetointegrado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.cadastraSala.CadastrarSalaActivity;
import com.example.projetointegrado.reservarSala.ReservarActivity;
import com.example.projetointegrado.reservas.ReservasActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUsuario;
    TextView tvCargo;
    ImageView ivSair;
    RelativeLayout btReservas;
    RelativeLayout btSala;
    RelativeLayout btReservar;
    RelativeLayout btAprovar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String nome = UsuarioLogado.usuarioLogado.getNome();
        String cargo = UsuarioLogado.cargo;

        tvUsuario = (TextView) findViewById(R.id.tv_usuario);
        tvUsuario.setText(nome);

        tvCargo = (TextView) findViewById(R.id.tv_cargo);
        tvCargo.setText(cargo);

        ivSair = (ImageView) findViewById(R.id.iv_sair);
        ivSair.setOnClickListener(this);

        btReservas = (RelativeLayout) findViewById(R.id.bt_Reservas);
        btReservas.setOnClickListener(this);

        btSala = (RelativeLayout)findViewById(R.id.bt_Sala);
        btSala.setOnClickListener(this);

        btAprovar = (RelativeLayout)findViewById(R.id.bt_Aprovar);
        btAprovar.setOnClickListener(this);

        btReservar = (RelativeLayout)findViewById(R.id.bt_Reservar);
        btReservar.setOnClickListener(this);

        validaMenu();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == ivSair.getId()){
            UsuarioLogado.usuarioLogado = null;
            UsuarioLogado.cargo = null;
            setResult(Activity.RESULT_OK);
            finish();
        }else if(v.getId() == btReservas.getId()){
            Intent intent = new Intent(this, ReservasActivity.class);
            startActivity(intent);
        }else if(v.getId() == btSala.getId()){
            Intent intent = new Intent(this, CadastrarSalaActivity.class);
            startActivity(intent);
        }else if(v.getId() == btReservar.getId()){
            Intent intent = new Intent(this, ReservarActivity.class);
            startActivity(intent);
        }
    }

    private void validaMenu(){
        if(UsuarioLogado.cargo.equalsIgnoreCase("Administrador")){
            btSala.setVisibility(View.VISIBLE);
            btAprovar.setVisibility(View.VISIBLE);
        }else{
            btSala.setVisibility(View.INVISIBLE);
            btAprovar.setVisibility(View.INVISIBLE);
        }
    }
}
