package com.example.ejerciciotecnico.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ModeloArticulos implements Serializable {
    private int SKU;
    private String ARTICULO;
    private String MARCA;
    private String MODELO;
    private int DEPARTAMENTO;
    private int CLASE;
    private int FAMILIA;
    private String FECHAALTA;
    private int STOCK;
    private int CANTIDAD;
    private int DESCONTINUADO;
    private String FECHABAJA;

    public ModeloArticulos() {}

    public ModeloArticulos(int SKU, String ARTICULO, String MARCA, String MODELO, int DEPARTAMENTO, int CLASE, int FAMILIA, String FECHAALTA, int STOCK, int CANTIDAD, int DESCONTINUADO, String FECHABAJA) {
        this.SKU = SKU;
        this.ARTICULO = ARTICULO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.DEPARTAMENTO = DEPARTAMENTO;
        this.CLASE = CLASE;
        this.FAMILIA = FAMILIA;
        this.FECHAALTA = FECHAALTA;
        this.STOCK = STOCK;
        this.CANTIDAD = CANTIDAD;
        this.DESCONTINUADO = DESCONTINUADO;
        this.FECHABAJA = FECHABAJA;
    }

    public int getSKU() {
        return SKU;
    }

    public String getARTICULO() {
        return ARTICULO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public int getDEPARTAMENTO() {
        return DEPARTAMENTO;
    }

    public int getCLASE() {
        return CLASE;
    }

    public int getFAMILIA() {
        return FAMILIA;
    }

    public String getFECHAALTA() {
        return FECHAALTA;
    }

    public int getSTOCK() {
        return STOCK;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public int getDESCONTINUADO() {
        return DESCONTINUADO;
    }

    public String getFECHABAJA() {
        return FECHABAJA;
    }
}
