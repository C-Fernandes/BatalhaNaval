package br.com.imd;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class MenuInicialController {

    @FXML
    private GridPane tabuleiroDoJogador;
    @FXML
    private GridPane tabuleiroAtacado;
    @FXML
    private Button destroyer;
    private boolean moverDestroyer = false;

    @FXML
    void iniciarJogo(ActionEvent event) throws IOException {
        App.setRoot("Jogador2Jogar");
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
        try {

            if (moverDestroyer == false) {
                moverDestroyer = true;

            } else {
                try {
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
                } catch (Exception e) {
                    System.out.println(e + " Aqui ");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Na funcao");
            System.out.println(e);
        }
    }

}
