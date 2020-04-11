package com.example.projetointegrado.modelos;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Sala extends RealmObject {

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
