package com.example.projetointegrado.modelos;

public class ModeloRecyclerView {

    private String header;
    private String content;

    private Reservas reservas;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reservas getReservas() { return reservas;  }

    public void setReservas(Reservas reservas) { this.reservas = reservas; }
}
