package br.com.imd.Model;

public abstract class Embarcacao {

    private int tamanho;
    private EmbarcacaoENUM tipo;
    private boolean destruido;
    private Quadrante posicao[];

    /**
     * @return the posicao
     */
    public Quadrante[] getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(Quadrante[] posicao) {
        this.posicao = posicao;
    }

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

    public EmbarcacaoENUM getTipo() {
        return this.tipo;
    }

    public void setTipo(EmbarcacaoENUM tipo) {
        this.tipo = tipo;
    }

}