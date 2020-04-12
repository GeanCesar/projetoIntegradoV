package com.example.projetointegrado;

import android.content.Context;

import com.example.projetointegrado.modelos.Usuario;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UsuarioLogado {

    public static Usuario usuarioLogado;
    public static String cargo;

    public static Usuario getUsuarioLogadoRealm(Context context, String email){
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm banco = Realm.getInstance(config);

        Usuario usuario = banco.where(Usuario.class).equalTo("email", email).findFirst();

        Usuario u = new Usuario();
        u.setEmail(usuario.getEmail());
        u.setSenha(usuario.getEmail());
        u.setNome(usuario.getEmail());

        
        if(u!=null)
            return usuario;
        else
            return null;
    }

}
