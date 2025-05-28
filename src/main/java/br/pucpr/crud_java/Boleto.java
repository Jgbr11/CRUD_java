package br.pucpr.crud_java;

public class Boleto {
    private static int id;
    private int numeroDocumento;
    private double valor;
    private String vencimento;
    private Locatario pagador;
    private final String cedente;
    private String banco;
    private String codBarras;
    private String linhaDigitavel;

    public Boleto() {
        id += 1;
        this.numeroDocumento = 0;
        this.valor = 0.0;
        this.vencimento = "";
        this.pagador = null;
        this.cedente = "Tijucas Open";
        this.banco = "Banco do Brasil";
        this.codBarras = "";
        this.linhaDigitavel = "";
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
