package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;


public class Locatario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int contador = 1;

    private int idLocatario;
    //private int logins_id;
    private String locatario_cpj;
    private String locatario_nome;
    private String locatario_telefone;
    private String locatario_email;

    public Locatario(String cnpj, String nome, String telefone, String email) {
        idLocatario = contador++;
        locatario_cpj = cnpj;
        locatario_nome = nome;
        locatario_telefone = telefone;
        locatario_email = email;
    }

    @Override
    public String toString() {
        return "ID: " + this.idLocatario +
                "\nNome: " + this.locatario_nome +
                "\nCNPJ: " + this.locatario_cpj +
                "\nEmail: " + this.locatario_email +
                "\nTelefone: " + this.locatario_telefone + "\n";
    }

    public int getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(int idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getLocatario_cpj() {
        return locatario_cpj;
    }

    public void setLocatario_cpj(String locatario_cpj) {
        this.locatario_cpj = locatario_cpj;
    }

    public String getLocatario_nome() {
        return locatario_nome;
    }

    public void setLocatario_nome(String locatario_nome) {
        this.locatario_nome = locatario_nome;
    }

    public String getLocatario_telefone() {
        return locatario_telefone;
    }

    public void setLocatario_telefone(String locatario_telefone) {
        this.locatario_telefone = locatario_telefone;
    }

    public String getLocatario_email() {
        return locatario_email;
    }

    public void setLocatario_email(String locatario_email) {
        this.locatario_email = locatario_email;
    }
}
