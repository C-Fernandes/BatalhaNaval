package br.com.imd;

import java.io.IOException;

import br.com.imd.Controller.JogadorController;
import br.com.imd.Model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    void iniciarJogo(ActionEvent event) throws IOException {
        try {
            App.setRoot("View/cadastroUsuario");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

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
            App.setRoot("View/Jogador2Jogar");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    TabuleiroController tabuleiControllerJogador1 = new TabuleiroController();
    TabuleiroController tabuleiControllerJogador2 = new TabuleiroController();

    JogadorController jogadores = new JogadorController();

}
