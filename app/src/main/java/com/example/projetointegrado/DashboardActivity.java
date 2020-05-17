package com.example.projetointegrado;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetointegrado.aprovarReserva.AprovarActivity;
import com.example.projetointegrado.cadastraSala.CadastrarSalaActivity;
import com.example.projetointegrado.historico.HistoricoActivity;
import com.example.projetointegrado.modelos.User;
import com.example.projetointegrado.reservarSala.ReservarActivity;
import com.example.projetointegrado.reservas.ReservasActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUsuario;
    TextView tvCargo;
    ImageView ivSair;
    RelativeLayout btReservas;
    RelativeLayout btSala;
    RelativeLayout btReservar;
    RelativeLayout btAprovar;
    RelativeLayout btHistorico;
    LinearLayout llAdmin;
    LinearLayout llProfessor;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Users");
        databaseUsuario.equalTo(UsuarioLogado.usuarioLogado.getEmail(), "email");

        llAdmin = (LinearLayout) findViewById(R.id.ll_Admin);
        llProfessor = (LinearLayout) findViewById(R.id.ll_Professor);

        tvUsuario = (TextView) findViewById(R.id.tv_usuario);

        tvCargo = (TextView) findViewById(R.id.tv_cargo);

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

        btHistorico = (RelativeLayout)findViewById(R.id.bt_Historico);
        btHistorico.setOnClickListener(this);

        databaseUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()){
                    User usuario = usuarioSnapshot.getValue(User.class);

                    if(usuario.getEmail().equalsIgnoreCase(firebaseAuth.getCurrentUser().getEmail())){
                        UsuarioLogado.usuarioLogado.setNome(usuario.getNome());
                        UsuarioLogado.cargo = usuario.getCargo();
                        tvUsuario.setText(UsuarioLogado.usuarioLogado.getNome());
                        tvCargo.setText(UsuarioLogado.cargo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tvUsuario.setText(UsuarioLogado.usuarioLogado.getNome());
        tvCargo.setText(UsuarioLogado.cargo);
        validaMenu();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == ivSair.getId()){
            finish();
        }else if(v.getId() == btReservas.getId()){
            Intent intent = new Intent(this, ReservasActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }else if(v.getId() == btSala.getId()){
            Intent intent = new Intent(this, CadastrarSalaActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }else if(v.getId() == btReservar.getId()){
            Intent intent = new Intent(this, ReservarActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }else if(v.getId() == btAprovar.getId()){
            Intent intent = new Intent(this, AprovarActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }else if(v.getId() == btHistorico.getId()){
            Intent intent = new Intent(this, HistoricoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
        }
    }

    private void validaMenu(){
        if(UsuarioLogado.cargo.equalsIgnoreCase("Administrador")){
            llAdmin.setVisibility(View.VISIBLE);
            llProfessor.setVisibility(View.GONE);
        }else{
            llProfessor.setVisibility(View.VISIBLE);
            llAdmin.setVisibility(View.GONE);
        }
    }

    private void deslogar(){
        UsuarioLogado.usuarioLogado = null;
        UsuarioLogado.cargo = null;
        setResult(Activity.RESULT_OK);
        firebaseAuth.signOut();
        super.finish();
        overridePendingTransition(R.anim.from_fade_in, R.anim.from_fade_out);
    }

    @Override
    public void finish() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.alertaAzul)
                .setTitle("Sair")
                .setMessage("Tem certeza que deseja sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        deslogar();
                    }})
                .setNegativeButton("Não", null);

        dialog.create().show();
    }
}
