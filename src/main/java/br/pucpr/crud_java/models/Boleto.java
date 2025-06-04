package br.pucpr.crud_java.models;

import java.io.Serial;
import java.io.Serializable;

public class Boleto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int contador = 1;
    private int idBoleto;
    private int numeroDocumento;
    private final double valor;
    private String vencimento;
    private final String cedente;
    private final String banco;
    private String codBarras;
    private String linhaDigitavel;
    private Contrato contrato;

    public Boleto() {
        this.idBoleto = ++contador;
        this.numeroDocumento = 0;
        this.valor = 3000;
        this.vencimento = "";
        this.cedente = "Tijucas Open";
        this.banco = "Banco do Brasil";
        this.codBarras = "";
        this.linhaDigitavel = "";
    }

    @Override
    public String toString() {
        return "ID: " + this.idBoleto +
                "\nNúmero do Documento: " + this.numeroDocumento +
                "\nValor: R$" + this.valor +
                "\nVencimento: " + this.vencimento +
                "\nCedente: " + this.cedente +
                "\nBanco: " + this.banco +
                "\nCódigo de barras: " + this.codBarras +
                "\nLinha digitável: " + this.linhaDigitavel +
                "\n";
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int id) {
        this.idBoleto = id;
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

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getBanco() {
        return banco;
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getCedente() {
        return cedente;
    }


}
