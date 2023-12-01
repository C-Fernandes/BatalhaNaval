package br.com.imd.Controller;

import java.io.IOException;

import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.Jogador;
import br.com.imd.Model.Quadrante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class TabuleiroController {

    private Quadrante[][] quadrantes;
    private boolean moverDestroyer = false;

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

    public void verificarSeVenceu(Jogador a) {
        int contador = 0;
        Embarcacao embarcacoes[] = a.getEmbarcacoes();
        for (int i = 0; i < embarcacoes.length; i++)
            if (embarcacoes[i].isDestruido())
                contador++;

        if (contador == embarcacoes.length)
            System.out.println("Jogador " + a.getNome() + " venceu");
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
                        if (quadrantes[xAleatorio][yAleatorio].getPreenchidoPorNavio())
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

    void atacarAdversario(Node source, GridPane tabuleiroAtacado) {
    }

}