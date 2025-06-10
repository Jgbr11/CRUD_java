package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;


public class Locatario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int contador = 1;

    private int idLocatario;
    //private int logins_id;
    private String locatario_cnpj;
    private String locatario_nome;
    private String locatario_telefone;
    private String locatario_email;

    public Locatario(String cnpj, String nome, String email, String telefone) {
        idLocatario = contador++;
        locatario_cnpj = cnpj;
        locatario_nome = nome;
        locatario_telefone = telefone;
        locatario_email = email;
    }

    @Override
    public String toString() {
        return "ID: " + this.idLocatario +
                "\nNome: " + this.locatario_nome +
                "\nCNPJ: " + this.locatario_cnpj +
                "\nEmail: " + this.locatario_email +
                "\nTelefone: " + this.locatario_telefone + "\n";
    }

    public int getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(int idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getLocatario_cnpj() {
        return locatario_cnpj;
    }

    public void setLocatario_cnpj(String locatario_cnpj) {
        this.locatario_cnpj = locatario_cnpj;
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
