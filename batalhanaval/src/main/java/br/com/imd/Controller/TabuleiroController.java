package br.com.imd.Controller;

import java.io.IOException;

import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.Quadrante;
import java.util.Random;

public class TabuleiroController {

    private Quadrante[][] quadrantes;

    public TabuleiroController() {
        this.quadrantes = new Quadrante[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                quadrantes[i][j] = new Quadrante(i, j);
            }
        }

    }

    public Quadrante[][] getQuadrantes() {
        return this.quadrantes;
    }

    public void setQuadrantes(Quadrante quadrante) {
        this.quadrantes[quadrante.getX()][quadrante.getY()] = quadrante;
    }

    public void setQuadrantes(Quadrante[][] quadrantes) {
        this.quadrantes = quadrantes;
    }

    public Embarcacao[] verificarNavioDestruido(Embarcacao e[]) {

        Embarcacao novaEmbarcacao[], embarcacoes[] = e;
        int naviosDestruidos = 0, cont = 0;
        for (int i = 0; i < embarcacoes.length; i++) {
            int contador = 0;
            Quadrante posicoes[] = embarcacoes[i].getPosicao();
            for (int j = 0; j < posicoes.length; j++) {
                if ((this.quadrantes[posicoes[j].getX()][posicoes[j].getY()].getAtacado())) {
                    posicoes[j].setAtacado(true);
                    contador++;
                }
            }
            embarcacoes[i].setPosicao(posicoes);
            if (contador == embarcacoes[i].getTamanho()) {
                embarcacoes[i] = null;
                naviosDestruidos++;
            }
        }
        if (naviosDestruidos > 0) {
            novaEmbarcacao = new Embarcacao[embarcacoes.length - naviosDestruidos];
            for (int i = 0; i < embarcacoes.length; i++) {
                if (embarcacoes[i] != null) {
                    novaEmbarcacao[cont] = embarcacoes[i];
                    cont++;
                }
            }
        } else
            novaEmbarcacao = embarcacoes;
        return novaEmbarcacao;
    }

    public Embarcacao[] posicionarAleatoriamente(Embarcacao embarcacoes[]) {

        for (int i = 0; i < embarcacoes.length; i++) {

            while (true) {
                int contador = 0;
                Random numAleatorio = new Random();
                int xAleatorio = numAleatorio.nextInt(10), yAleatorio = numAleatorio.nextInt(10);
                if (xAleatorio >= 0 && xAleatorio <= 9 && yAleatorio < (9 - embarcacoes[i].getTamanho()) + 1) {
                    boolean livre = true;
                    for (int k = yAleatorio; k < (9 - embarcacoes[i].getTamanho()) + 1; k++)
                        if (quadrantes[xAleatorio][k].getPreenchidoPorNavio())
                            livre = false;

                    if (livre) {
                        System.out.println("x :" + xAleatorio);
                        Quadrante posicaoEmbarcacao[] = new Quadrante[embarcacoes[i].getTamanho()];

                        System.out.println(embarcacoes[i].getTamanho());
                        try {
                            for (int j = yAleatorio; j < yAleatorio + (embarcacoes[i].getTamanho()); j++) {
                                this.quadrantes[xAleatorio][j].setPreenchidoPorNavio(true);
                                System.out.println("j:" + j + " Contador: " + contador);
                                posicaoEmbarcacao[contador] = quadrantes[xAleatorio][j];
                                contador++;
                                System.out.println("y:" + j + " Contador: " + contador);

                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        System.out.println("Embarcacao: " + embarcacoes[i].getTipo());
                        System.out.println("linha - coluna: " + xAleatorio + " - " + yAleatorio);

                        embarcacoes[i].setPosicao(posicaoEmbarcacao);
                        break;
                    }
                }
            }
        }
        return embarcacoes;
    }

}