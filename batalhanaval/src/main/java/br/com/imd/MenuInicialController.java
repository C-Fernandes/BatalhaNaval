package br.com.imd;

import java.io.IOException;

import br.com.imd.Controller.JogadorController;
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

    TabuleiroController tabuleiControllerJogador1 = new TabuleiroController();
    TabuleiroController tabuleiControllerJogador2 = new TabuleiroController();

    JogadorController jogador1 = new JogadorController();

}
