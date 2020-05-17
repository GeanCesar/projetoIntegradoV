package com.example.projetointegrado.modelos;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Reservas {

    public Reservas(String pk,User usuario, Date data, Sala sala, int status){
        this.pk = pk;
        this.data = data;
        this.usuario = usuario;
        this.sala = sala;
        this.status = status;
    }

    public Reservas(){}

    private String pk;

    private User usuario;

    private Date data;

    private Sala sala;

    private int status;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
}
