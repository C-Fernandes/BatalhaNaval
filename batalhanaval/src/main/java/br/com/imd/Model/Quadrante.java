package br.com.imd.Model;

public class Quadrante {

    // Coordenadas do quadrante
    private int x, y;
    private boolean preenchidoPorNavio = false, atacado = false;

    public Quadrante(int x, int y) {
        this.x = x;
        this.y = y;
        this.atacado = false;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
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

}