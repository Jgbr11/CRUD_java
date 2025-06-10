package br.pucpr.crud_java.models;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private String nomeLocatario;
    private LocalDate dataInicio;
    private double valorMensal;
    private boolean contratoStatus;

    public Contrato(String nomeLocatario, LocalDate dataInicio, double valorMensal, boolean ContratoStatus) {
        this.nomeLocatario = nomeLocatario;
        this.dataInicio = dataInicio;
        this.valorMensal = valorMensal;
        this.contratoStatus = ContratoStatus;
    }

    public String getNomeLocatario() {
        return nomeLocatario;
    }

    public void setNomeLocatario(String nomeLocatario) {
        this.nomeLocatario = nomeLocatario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public boolean isContratoStatus() {
        return contratoStatus;
    }

    public void setContratoStatus(boolean contratoStatus) {
        contratoStatus = contratoStatus;
    }

    public boolean isAtivo() {
        return contratoStatus;
    }
}
