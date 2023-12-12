package br.com.imd.Model;

public abstract class Embarcacao {

    private int tamanho;
    private EmbarcacaoENUM tipo;
    private boolean destruido;
    private Quadrante posicao[];
    private boolean ehVertical;

    /**
     * @return
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * @param tamanho
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return
     */
    public EmbarcacaoENUM getTipo() {
        return this.tipo;
    }

    /**
     * @param tipo
     */
    public void setTipo(EmbarcacaoENUM tipo) {
        this.tipo = tipo;
    }

    /**
     * @return
     */
    public boolean isDestruido() {
        return this.destruido;
    }

    /**
     * @return
     */
    public boolean getDestruido() {
        return this.destruido;
    }

    /**
     * @param destruido
     */
    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    /**
     * @return
     */
    public Quadrante[] getPosicao() {
        return this.posicao;
    }

    /**
     * @param posicao
     */
    public void setPosicao(Quadrante[] posicao) {
        this.posicao = posicao;
    }

    /**
     * @return
     */
    public boolean isEhVertical() {
        return this.ehVertical;
    }

    /**
     * @return
     */
    public boolean getEhVertical() {
        return this.ehVertical;
    }

    /**
     * @param ehVertical
     */
    public void setEhVertical(boolean ehVertical) {
        this.ehVertical = ehVertical;
    }

}