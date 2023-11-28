package com.example.ejerciciotecnico.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")

public class ModeloFamilias implements Serializable{
    private int FAMILIAID;
    private String FAMILIANAME;
    private int CLASEID;

    public ModeloFamilias() {}

    public ModeloFamilias(int FAMILIAID, String FAMILIANAME, int CLASEID) {
        this.FAMILIAID = FAMILIAID;
        this.FAMILIANAME = FAMILIANAME;
        this.CLASEID = CLASEID;
    }

    public int getFAMILIAID() {
        return FAMILIAID;
    }

    public String getFAMILIANAME() {
        return FAMILIANAME;
    }

    public int getCLASEID() {
        return CLASEID;
    }
}
