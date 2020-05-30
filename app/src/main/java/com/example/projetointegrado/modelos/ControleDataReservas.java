package com.example.projetointegrado.modelos;

import java.util.Calendar;

public class ControleDataReservas {

    private Reservas reserva;

    private Calendar dataAntes;

    private Calendar dataDepois;

    public Reservas getReserva() {
        return reserva;
    }

    public void setReserva(Reservas reserva) {
        this.reserva = reserva;
    }

    public Calendar getDataAntes() {
        return dataAntes;
    }

    public void setDataAntes(Calendar dataAntes) {
        this.dataAntes = dataAntes;
    }

    public Calendar getDataDepois() {
        return dataDepois;
    }

    public void setDataDepois(Calendar dataDepois) {
        this.dataDepois = dataDepois;
    }
}
