package com.example.projetointegrado.modelos;

import com.example.projetointegrado.Uteis;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Usuario extends RealmObject {

    public Usuario(String email, String nome){
        this.email = email;
        this.nome = nome;
    }

    public Usuario(){}

    @PrimaryKey
    @Required
    private String email;

    @Required
    private String nome;

    @Required
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Uteis.MD5(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
