package br.pucpr.crud_java.models;

public class Contrato {
    private int idContrato;
    private int empresaid;
    private int espacoid;
    private String nomeLocatario;
    private String dataInicio;
    private double valorMensal;
    private boolean ContratoStatus;

    public Contrato(int idContrato, int empresaid, int  espacoid, String nomeLocatario, String dataInicio, double valorMensal, boolean ContratoStatus) {
        this.idContrato = idContrato;
        this.empresaid = empresaid;
        this.espacoid = espacoid;
        this.nomeLocatario = nomeLocatario;
        this.dataInicio = dataInicio;
        this.valorMensal = valorMensal;
        this.ContratoStatus = ContratoStatus;
    }


}
