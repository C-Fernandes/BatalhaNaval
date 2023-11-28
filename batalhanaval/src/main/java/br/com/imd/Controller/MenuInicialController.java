package br.com.imd.Controller;

import java.io.IOException;

import br.com.imd.App;
import br.com.imd.Model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class MenuInicialController {
    @FXML
    private TextField jogador1;
    @FXML
    private TextField jogador2;
    @FXML
    private GridPane tabuleiroDoJogador;
    @FXML
    private GridPane tabuleiroAtacado;
    @FXML
    private Button destroyer;

    private boolean moverDestroyer = false;

    @FXML
    Button corveta;
    @FXML
    Button submarino;
    @FXML
    Button fragata;

    @FXML
    void iniciarJogo(ActionEvent event) throws IOException {
        try {
            App.setRoot("View/Jogador2Jogar");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    @FXML
    void jogar(ActionEvent event) throws IOException {
        App.setRoot("View/cadastroUsuario");
    }

    @FXML
    void cadastrarUsuarios(ActionEvent event) throws IOException {
        try {
            jogadores.setJogador1(new Jogador(jogador1.getText()));
            jogadores.setJogador2(new Jogador(jogador2.getText()));

            System.out.println(jogadores.getJogador1().getNome());
            System.out.println(jogadores.getJogador2().getNome());
        } catch (Exception e) {
            System.out.println("Erro aqui");
        }
        try {
            App.setRoot("View/setupJogador1");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void atacarJogador1(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            tabuleiControllerJogador2.atacarAdversario(source, tabuleiroAtacado);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void atacarJogador2(ActionEvent event) {
        Node source = (Node) event.getSource();
        tabuleiControllerJogador1.atacarAdversario(source, tabuleiroAtacado);
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

    TabuleiroController tabuleiControllerJogador1 = new TabuleiroController();
    TabuleiroController tabuleiControllerJogador2 = new TabuleiroController();

    JogadorController jogadores = new JogadorController();

    @FXML
    void setupJogador1(ActionEvent event) throws IOException {
        App.setRoot("View/setupJogador2");
    }

}
