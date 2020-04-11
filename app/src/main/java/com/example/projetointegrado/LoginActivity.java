package com.example.projetointegrado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetointegrado.modelos.Usuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    EditText etEmailLogin;
    EditText etSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        etSenhaLogin = (EditText) findViewById(R.id.etSenhaLogin);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btLogin.getId()){
            if(validaCampos()) {
                String email = etEmailLogin.getText().toString();
                String senha = etSenhaLogin.getText().toString();
                fazLogin(email, senha);
            }
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
            // Inicializa o Realm
            Realm.init(getApplicationContext());

            // Cria a configuração do realm
            RealmConfiguration config = new RealmConfiguration.Builder().build();
            Realm.setDefaultConfiguration(config);
            Realm realm = Realm.getInstance(config);

            //Busca todos os usuarios cadastrados
            RealmResults<Usuario> usuario = realm.where(Usuario.class)
                    .equalTo("email", email)
                    .equalTo("senha", Uteis.MD5(senha)).findAll();

            if (usuario.size() > 0) {
                Intent intent = new Intent(this, DashboardActivity.class);
                UsuarioLogado.usuarioLogado = new Usuario();
                UsuarioLogado.usuarioLogado.setNome(usuario.get(0).getNome());
                UsuarioLogado.cargo = "Professor";

                startActivityForResult(intent, Requests.LOGAR.getCod());
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }

            //Fecha conexão
            realm.close();
        }else{
            Intent intent = new Intent(this, DashboardActivity.class);
            UsuarioLogado.usuarioLogado = new Usuario();
            UsuarioLogado.usuarioLogado.setNome("Admin");
            UsuarioLogado.cargo = "Administrador";
            startActivityForResult(intent, Requests.LOGAR.getCod());
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
}
