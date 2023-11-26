package br.com.imd.Model;

public class Quadrante {

    //Coordenadas do quadrante
    private int x,y;
    private boolean atacado;


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

}