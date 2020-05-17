package com.example.projetointegrado.modelos;

public class Sala {

    public Sala(){}

    public Sala(int nSala, boolean laboratorio, boolean projetor){
        this.nSala = nSala;
        this.laboratorio = laboratorio;
        this.projetor = projetor;
    }

    private int nSala;

    private boolean laboratorio;

    private boolean projetor;

    public int getnSala() {
        return nSala;
    }

    public void setnSala(int nSala) {
        this.nSala = nSala;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public boolean isProjetor() {
        return projetor;
    }

    public void setProjetor(boolean projetor) {
        this.projetor = projetor;
    }
}
