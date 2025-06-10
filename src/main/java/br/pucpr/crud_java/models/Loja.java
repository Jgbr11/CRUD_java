package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;

public class Loja implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private static int contador = 0;

    private int lojaId;
    private String lojaNome;
    private String lojaTelefone;
    private String lojaTipo;

    public Loja(String lojaNome, String lojaTelefone, String lojaTipo) {
        this.lojaId = ++contador;
        this.lojaNome = lojaNome;
        this.lojaTelefone = lojaTelefone;
        this.lojaTipo = lojaTipo;
    }

    @Override
    public String toString() {
        return "ID: " + this.lojaId +
                "\nNome: " + this.lojaNome +
                "\nTelefone: " + this.lojaTelefone +
                "\nTipo: " + this.lojaTipo + "\n";
    }


    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public String getLojaNome() {
        return lojaNome;
    }

    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
    }

    public String getLojaTelefone() {
        return lojaTelefone;
    }

    public void setLojaTelefone(String lojaTelefone) {
        this.lojaTelefone = lojaTelefone;
    }

    public static void setContador(int contador) {
        Loja.contador = contador;
    }

    public String getLojaTipo() {
        return lojaTipo;
    }

    public void setLojaTipo(String lojaTipo) {
        this.lojaTipo = lojaTipo;
    }
}