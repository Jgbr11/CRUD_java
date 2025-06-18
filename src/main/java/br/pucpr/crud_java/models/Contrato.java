package br.pucpr.crud_java.models;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Contrato implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private int contratoId;
    private Locatario locatario;
    private LocalDate dataInicio;
    private double valorMensal;
    private boolean contratoStatus;
    private ArrayList<Boleto> boletos;

    public Contrato(Locatario locatario, LocalDate dataInicio,
                    double valorMensal, boolean ContratoStatus) {
        this.locatario = locatario;
        this.dataInicio = dataInicio;
        this.valorMensal = valorMensal;
        this.contratoStatus = ContratoStatus;
        this.boletos = new ArrayList<>();
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public Locatario getlocatario() {
        return locatario;
    }

    public void setNomeLocatario(Locatario locatario) {
        this.locatario = locatario;
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

    public ArrayList<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(ArrayList<Boleto> boletos) {
        this.boletos = boletos;
    }
}