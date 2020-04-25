package com.example.projetointegrado.aprovarReserva;

import com.example.projetointegrado.modelos.Sala;

import java.util.Calendar;

public class ValoresRetornados {

    private Sala sala;
    private Calendar dataHora;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }
}
