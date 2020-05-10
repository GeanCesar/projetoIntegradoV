package com.example.projetointegrado.modelos;

public class User {

    public User(String email, String nome, String cargo){
        this.setEmail(email);
        this.setNome(nome);
        this.setCargo(cargo);
    }

    public User(){}

    private String email;

    private String nome;

    private String cargo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
