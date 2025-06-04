package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;

public class Espaco implements Serializable {
    @Serial
    private static final long serialVersionUIDn = 1L;

    private static int contador = 0;
    private int idEspaco;
    private int piso;
    private double area;
    private boolean espacoStatus;

    public Espaco(int piso, double area) {
        this.idEspaco = ++contador;
        this.piso = piso;
        this.area = area;
    }
    @Override
    public String toString() { return " Piso: " + this.piso + " Area: " + this.area; }

    public int getIdEspaco() {return idEspaco;}

    public void setId(int idEspaco) {this.idEspaco = idEspaco;}

    public int getPiso() {return piso;}
    public void setPiso(int piso) {this.piso = piso;}

    public double getArea() {return area;}

    public void setArea(double area) {this.area = area;}

    public boolean isStatus() {return espacoStatus;}

    public void setStatus(boolean status) {this.espacoStatus = status;}
}
