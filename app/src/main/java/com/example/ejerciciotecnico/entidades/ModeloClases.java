package com.example.ejerciciotecnico.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")

public class ModeloClases implements Serializable{
    private int CLASEID;
    private String CLASENAME;
    private int DEPARTAMENTOID;

    public ModeloClases() {}

    public ModeloClases(int CLASEID, String CLASENAME, int DEPARTAMENTOID) {
        this.CLASEID = CLASEID;
        this.CLASENAME = CLASENAME;
        this.DEPARTAMENTOID = DEPARTAMENTOID;
    }

    public int getCLASEID() {
        return CLASEID;
    }

    public String getCLASENAME() {
        return CLASENAME;
    }

    public int getDEPARTAMENTOID() {
        return DEPARTAMENTOID;
    }
}
