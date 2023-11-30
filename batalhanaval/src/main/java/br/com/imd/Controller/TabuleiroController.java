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

    public void setQuadrantes(Quadrante[][] quadrantes) {
        this.quadrantes = quadrantes;
    }

    public void iniciarTabuleiro() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                quadrantes[i][j] = new Quadrante(i, j);

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

    public Embarcacao pAleatoriamente(Embarcacao embarcacao) {
        int contador = 0;
        while (true) {

            Random numAleatorio = new Random();
            System.out.println(embarcacao.getTamanho());
            int xAleatorio = numAleatorio.nextInt(10), yAleatorio = numAleatorio.nextInt(10);
            System.out.println(xAleatorio + " - " + yAleatorio);
            System.out.println((9 - embarcacao.getTamanho()) + 1);
            if (xAleatorio >= 0 && xAleatorio <= 9 && yAleatorio <= (9 - embarcacao.getTamanho()) + 1
                    && (!quadrantes[xAleatorio][xAleatorio].getPreenchidoPorNavio())) {
                System.out.println("Entrou no if");
                Quadrante posicaoEmbarcacao[] = new Quadrante[embarcacao.getTamanho()];
                System.out.println(yAleatorio + (embarcacao.getTamanho() - 1));
                for (int i = yAleatorio; i < yAleatorio + (embarcacao.getTamanho() - 1); i++) {
                    this.quadrantes[xAleatorio][i].setPreenchidoPorNavio(true);
                    posicaoEmbarcacao[contador] = quadrantes[xAleatorio][i];

                    contador++;
                }
                embarcacao.setPosicao(posicaoEmbarcacao);

                return embarcacao;
            }

        }

    }

    void atacarAdversario(Node source, GridPane tabuleiroAtacado) {
        try {
            int linha = 0, coluna = 0;

            if (tabuleiroAtacado.getRowIndex(source) != null)
                linha = tabuleiroAtacado.getRowIndex(source);
            if (tabuleiroAtacado.getColumnIndex(source) != null)
                coluna = tabuleiroAtacado.getColumnIndex(source);
            System.out.println(coluna + " - " + linha);
            Quadrante qAlvo = new Quadrante(linha, coluna, quadrantes[linha][coluna].getAtacado(),
                    quadrantes[linha][coluna].getPreenchidoPorNavio());

            if (!qAlvo.isAtacado()) {
                System.out.println("Entrou no não atacado");
                Button botao = new Button();
                try {
                    botao = (Button) tabuleiroAtacado.getChildren();
                } catch (Exception e) {
                    System.out.println("É aqui");
                    System.out.println(e);
                }

                botao.setStyle("-fx-background-color: Red");
                botao.setPrefSize(42, 42);
                tabuleiroAtacado.add(botao, coluna, linha);

                quadrantes[linha][coluna].setAtacado(true);

                if (qAlvo.isPreenchidoPorNavio()) {

                }
            }
            System.out.println("Já foi atacado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}