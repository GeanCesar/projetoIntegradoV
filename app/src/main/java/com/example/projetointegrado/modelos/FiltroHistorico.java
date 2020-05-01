package com.example.projetointegrado.modelos;

public enum FiltroHistorico {

    TODOS(1),
    PENDENTE(2),
    APROVADO(3),
    RECUSADO(4);

    FiltroHistorico(int codigo){
        this.setCodigo(codigo);
    }

    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
