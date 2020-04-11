package com.example.projetointegrado;

public enum Requests {
    LOGAR(1);

    Requests(int cod){
        this.setCod(cod);
    }

    private int cod;


    public int getCod() {
        return cod;
    }

    private void setCod(int cod) {
        this.cod = cod;
    }
}
