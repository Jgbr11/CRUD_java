package br.pucpr.crud_java.models;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;


    private int contador = 1;
    private int idContrato;
    private int empresaid;
    private int espacoid;
    private String nomeLocatario;
    private LocalDate dataInicio;
    private double valorMensal;
    private boolean contratoStatus;

    public Contrato(int empresaid, int  espacoid, String nomeLocatario, LocalDate dataInicio, double valorMensal, boolean ContratoStatus) {
        this.idContrato = contador++;
        this.empresaid = empresaid;
        this.espacoid = espacoid;
        this.nomeLocatario = nomeLocatario;
        this.dataInicio = dataInicio;
        this.valorMensal = valorMensal;
        this.contratoStatus = ContratoStatus;
    }

    public int getIdContrato() {return idContrato;}

    public void setIdContrato(int idContrato) {this.idContrato = idContrato;}

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
