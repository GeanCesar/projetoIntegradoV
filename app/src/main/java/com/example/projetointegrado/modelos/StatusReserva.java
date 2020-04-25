package com.example.projetointegrado.modelos;

public enum StatusReserva {

    APROVADO(1),
    PENDENTE(2),
    RECUSADO(3);

    private int codigo;

    StatusReserva(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
