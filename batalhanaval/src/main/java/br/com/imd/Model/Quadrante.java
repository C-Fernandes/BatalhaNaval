package br.com.imd.Model;

public class Quadrante {

    // Coordenadas do quadrante
    private int linha, coluna;
    private boolean atacado = false, preenchidoPorNavio = false;
    private EmbarcacaoENUM ocupadoPor = null;

    public Quadrante() {
    }

    public Quadrante(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.atacado = false;
        this.preenchidoPorNavio = false;
    }

    public Quadrante(int linha, int coluna, boolean atacado, boolean preenchidoPorNavio) {
        this.linha = linha;
        this.coluna = coluna;
        this.atacado = atacado;
        this.preenchidoPorNavio = preenchidoPorNavio;
    }

    public int getLinha() {
        return this.linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return this.coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean isPreenchidoPorNavio() {
        return this.preenchidoPorNavio;
    }

    public boolean getPreenchidoPorNavio() {
        return this.preenchidoPorNavio;
    }

    public void setPreenchidoPorNavio(boolean preenchidoPorNavio) {
        this.preenchidoPorNavio = preenchidoPorNavio;
    }

    public boolean isAtacado() {
        return this.atacado;
    }

    public boolean getAtacado() {
        return this.atacado;
    }

    public void setAtacado(boolean atacado) {
        this.atacado = atacado;
    }

    public EmbarcacaoENUM getOcupadoPor() {
        return this.ocupadoPor;
    }

    public void setOcupadoPor(EmbarcacaoENUM ocupadoPor) {
        this.ocupadoPor = ocupadoPor;
    }

}