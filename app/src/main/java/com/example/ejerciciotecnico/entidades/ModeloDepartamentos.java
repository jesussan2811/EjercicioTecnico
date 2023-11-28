package com.example.ejerciciotecnico.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")

public class ModeloDepartamentos implements Serializable{
    private int DEPARTAMENTOID;
    private String DEPARTAMENTONAME;

    public ModeloDepartamentos() {}

    public ModeloDepartamentos(int DEPARTAMENTOID, String DEPARTAMENTONAME) {
        this.DEPARTAMENTOID = DEPARTAMENTOID;
        this.DEPARTAMENTONAME = DEPARTAMENTONAME;
    }

    public int getDEPARTAMENTOID() {
        return DEPARTAMENTOID;
    }

    public String getDEPARTAMENTONAME() {
        return DEPARTAMENTONAME;
    }
}
