package br.com.imd.Model;

public abstract class Embarcacao {

    private int tamanho;
    private EmbarcacaoENUM tipo;
    private boolean destruido;
    private Quadrante posicao[];
    private boolean ehVertical;

    /**
     * @return the posicao
     */

    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public EmbarcacaoENUM getTipo() {
        return this.tipo;
    }

    public void setTipo(EmbarcacaoENUM tipo) {
        this.tipo = tipo;
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

    public Quadrante[] getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Quadrante[] posicao) {
        this.posicao = posicao;
    }

    public boolean isEhVertical() {
        return this.ehVertical;
    }

    public boolean getEhVertical() {
        return this.ehVertical;
    }

    public void setEhVertical(boolean ehVertical) {
        this.ehVertical = ehVertical;
    }

}