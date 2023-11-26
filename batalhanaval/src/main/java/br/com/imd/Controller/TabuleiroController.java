package br.com.imd.Controller;

import br.com.imd.Model.Quadrante;

public class TabuleiroController {

    private Quadrante[][] quadrantes;


    public TabuleiroController() {
        this.quadrantes = new Quadrante[10][10];
    }
    

    public Quadrante[][] getQuadrantes() {
        return this.quadrantes;
    }

    public void setQuadrantes(Quadrante[][] quadrantes) {
        this.quadrantes = quadrantes;
    }

    public void iniciarTabuleiro(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                quadrantes[i][j] = new Quadrante(i, j);
            }
        }

        
    }


    
}