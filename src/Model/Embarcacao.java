package Model;

public abstract class Embarcacao {

    private int tamanho, tipo;
    private Quadrante[] quadrantesOcupados;

    /*
     * 2 - Coverta
     * 3 - submarino
     * 4 - Fragata
     * 5 - Destroyer
     */

    private boolean destruido;

    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isDestruido() {
        return this.destruido;
    }

    public boolean getDestruido() {
        return this.destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
