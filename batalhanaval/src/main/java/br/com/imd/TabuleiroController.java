package br.com.imd;

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

public class TabuleiroController {

    private Quadrante[][] quadrantes;

    private boolean moverDestroyer = false;
    String jogadorDaVez;
    @FXML
    Button destroyer;
    @FXML
    Button corveta;
    @FXML
    Button submarino;
    @FXML
    Button fragata;
    @FXML
    GridPane tabuleiroDoJogador;
    @FXML
    GridPane tabuleiroAtacado;

    public TabuleiroController() {
        this.quadrantes = new Quadrante[10][10];
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

    public void pAleatoriamente(Embarcacao embarcacao) {
        int contador = 0;
        while (true) {
            int xAleatorio = (int) Math.random() * 9 + 0, yAleatorio = (int) Math.random() * 9 + 0;
            if (xAleatorio >= 0 && xAleatorio <= (9 - embarcacao.getTamanho()) + 1) {
                Quadrante posicaoEmbarcacao[] = new Quadrante[embarcacao.getTamanho()];

                for (int i = yAleatorio; i < yAleatorio + embarcacao.getTamanho(); i++) {
                    posicaoEmbarcacao[contador] = quadrantes[xAleatorio][i];
                    contador++;
                }
                embarcacao.setPosicao(posicaoEmbarcacao);
                break;
            }
        }

    }

    @FXML
    void atacar(ActionEvent event) {
        TabuleiroController t = new TabuleiroController();
        try {
            Node source = (Node) event.getSource();
            int linha = 0, coluna = 0;

            if (tabuleiroAtacado.getRowIndex(source) != null)
                linha = tabuleiroAtacado.getRowIndex(source);
            if (tabuleiroAtacado.getColumnIndex(source) != null)
                coluna = tabuleiroAtacado.getColumnIndex(source);
            System.out.println(coluna + " - " + linha);
            Quadrante qAlvo = t.quadrantes[linha][coluna];
            if (!qAlvo.isAtacado()) {
                Button botao = (Button) tabuleiroAtacado.getChildren();
                botao.setStyle("-fx-background-color: red");
                tabuleiroAtacado.add(botao, linha, coluna);
                t.quadrantes[linha][coluna].setAtacado(true);
                if (qAlvo.isPreenchidoPorNavio()) {

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void clicado(ActionEvent e) throws IOException {

        if (moverDestroyer)
            moverDestroyer(e);

    }

    void rotacionarDestroyer() {

        System.out.println("rotacionar");

        if (destroyer.getRotate() == 0)
            destroyer.setRotate(90);

        else
            destroyer.setRotate(0);
    }

    @FXML
    void moverDestroyer(ActionEvent event) throws IOException {
        destroyer.setOnMouseClicked(ee -> {
            if (ee.getButton() == MouseButton.SECONDARY)
                rotacionarDestroyer();
        });
        destroyer.getOnMouseClicked();

        if (moverDestroyer == false) {
            moverDestroyer = true;

        } else {

            int linha = 0, coluna = 0;
            Node source = (Node) event.getSource();
            if (tabuleiroDoJogador.getRowIndex(source) != null) {
                linha = tabuleiroDoJogador.getRowIndex(source);
                System.out.println("Linha: " + tabuleiroDoJogador.getRowIndex(destroyer));
                System.out.println("coluna: " + tabuleiroDoJogador.getColumnIndex(destroyer));

            }
            if (tabuleiroDoJogador.getColumnIndex(source) != null) {
                coluna = tabuleiroDoJogador.getColumnIndex(source);
                System.out.println("Linha: " + tabuleiroDoJogador.getRowIndex(destroyer));
                System.out.println("coluna: " + tabuleiroDoJogador.getColumnIndex(destroyer));
            }
            System.out.println(linha + " - " + coluna);
            tabuleiroDoJogador.setColumnIndex(destroyer, coluna);
            tabuleiroAtacado.setRowIndex(destroyer, linha); //
            moverDestroyer = false;

        }

    }
}