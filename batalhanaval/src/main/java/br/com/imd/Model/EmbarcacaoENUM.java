package br.com.imd.Model;

public enum EmbarcacaoENUM {
    DESTROYER("Destroyer"),
    CORVETA("Corveta"),
    SUBMARINO("Submarino"),
    FRAGATA("Fragata");

    private String tipo;

    EmbarcacaoENUM(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
