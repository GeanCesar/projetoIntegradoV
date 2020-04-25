package com.example.projetointegrado.modelos;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Reservas extends RealmObject {


    private Usuario usuario;

    @Required
    private Date data;

    private Sala sala;

    private int status;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
}
