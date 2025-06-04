package br.pucpr.crud_java.models;
import java.io.Serial;
import java.io.Serializable;

public class Contrato implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

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

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getEmpresaid() {
        return empresaid;
    }

    public void setEmpresaid(int empresaid) {
        this.empresaid = empresaid;
    }

    public int getEspacoid() {
        return espacoid;
    }

    public void setEspacoid(int espacoid) {
        this.espacoid = espacoid;
    }

    public String getNomeLocatario() {
        return nomeLocatario;
    }

    public void setNomeLocatario(String nomeLocatario) {
        this.nomeLocatario = nomeLocatario;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public boolean isContratoStatus() {
        return ContratoStatus;
    }

    public void setContratoStatus(boolean contratoStatus) {
        ContratoStatus = contratoStatus;
    }
}
