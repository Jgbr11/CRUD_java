package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;

public class Boleto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int contador = 0;
    private int id;
    private int numeroDocumento;
    private double valor;
    private String vencimento;
    private Locatario pagador;
    private final String cedente;
    private String banco;
    private String codBarras;
    private String linhaDigitavel;

    public Boleto() {
        this.id = ++contador;
        this.numeroDocumento = 0;
        this.valor = 0.0;
        this.vencimento = "";
        this.pagador = null;
        this.cedente = "Tijucas Open";
        this.banco = "Banco do Brasil";
        this.codBarras = "";
        this.linhaDigitavel = "";
    }

    @Override
    public String toString(){
        return "ID: " + this.id + "\nCedente: " + this.cedente + "\nBanco: " + this.banco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public Locatario getPagador() {
        return pagador;
    }

    public void setPagadorNome(Locatario pagador) {
        this.pagador = pagador;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }
}
