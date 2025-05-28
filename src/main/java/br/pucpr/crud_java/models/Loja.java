package br.pucpr.crud_java.models;

public class Loja {
    private int lojaId;
    private String lojaNome;
    private String lojaTelefone;
    private String lojaLogo;
    private String lojaAndar;
    private String lojaTipo;


    public Loja(int lojaId, String lojaNome, String lojaTelefone, String lojaLogo, String lojaAndar, String lojaTipo) {
        this.lojaId = lojaId;
        this.lojaNome = lojaNome;
        this.lojaTelefone = lojaTelefone;
        this.lojaLogo = lojaLogo;
        this.lojaAndar = lojaAndar;
        this.lojaTipo = lojaTipo;

    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public String getLojaNome() {
        return lojaNome;
    }

    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
    }

    public String getLojaTelefone() {
        return lojaTelefone;
    }

    public void setLojaTelefone(String lojaTelefone) {
        this.lojaTelefone = lojaTelefone;
    }

    public String getLojaLogo() {
        return lojaLogo;
    }

    public void setLojaLogo(String lojaLogo) {
        this.lojaLogo = lojaLogo;
    }

    public String getLojaAndar() {
        return lojaAndar;
    }

    public void setLojaAndar(String lojaAndar) {
        this.lojaAndar = lojaAndar;
    }

    public String getLojaTipo() {
        return lojaTipo;
    }

    public void setLojaTipo(String lojaTipo) {
        this.lojaTipo = lojaTipo;
    }
}