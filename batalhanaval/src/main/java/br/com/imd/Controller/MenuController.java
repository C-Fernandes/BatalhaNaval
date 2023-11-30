package br.com.imd.Controller;

import br.com.imd.App;
import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.EmbarcacaoENUM;
import br.com.imd.Model.Jogador;
import br.com.imd.Model.Quadrante;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuController {
    private static MenuController instancia;
    TabuleiroController tabuleiControllerJogador1, tabuleiControllerJogador2;
    JogadorController jogadores;
    boolean jogoIniciado;

    public static MenuController getInstancia() {
        if (instancia == null) {
            instancia = new MenuController();
        }
        return instancia;
    }

    // Atributos FXML:
    @FXML
    private Button corveta;
    @FXML
    private Button submarino;
    @FXML
    private Button fragata;
    @FXML
    private Button destroyer;
    @FXML
    private TextField jogador1;
    @FXML
    private TextField jogador2;
    @FXML
    private GridPane tabuleiroDoJogador;
    @FXML
    private GridPane tabuleiroAtacado;

    private MenuController() {
        this.tabuleiControllerJogador1 = new TabuleiroController();
        this.tabuleiControllerJogador2 = new TabuleiroController();
        this.jogadores = new JogadorController();
        this.jogoIniciado = false;
    }

    // Funções de controle:
    Embarcacao[] posicionarBarcosAleatoriamente(Embarcacao embarcacoes[]) {
        Embarcacao embarcacoesVetor[] = embarcacoes;
        for (int i = 0; i < embarcacoes.length; i++)
            embarcacoesVetor[i] = tabuleiControllerJogador1.pAleatoriamente(embarcacoes[i]);

        return embarcacoesVetor;

    }

    void posicionarEmbarcacoes(Jogador jogador) {
        Embarcacao embarcacoes[] = jogador.getEmbarcacoes();
        System.out.println("Posicionar embarcacoes");
        System.out.println(this.jogadores.getJogador1().getNome());
        if (!jogoIniciado)
            embarcacoes = posicionarBarcosAleatoriamente(embarcacoes);
        for (int i = 0; i < embarcacoes.length; i++) {
            embarcacoes[i] = tabuleiControllerJogador1.pAleatoriamente(embarcacoes[i]);
            Quadrante posicao[] = embarcacoes[i].getPosicao();
            if (embarcacoes[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
            }
        }

        jogadores.getJogador1().setEmbarcacoes(embarcacoes);
    }

    // Funções Scenner Builder:
    @FXML
    void jogar(ActionEvent event) throws IOException {
        try {
            App.setRoot("View/cadastroUsuario");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void cadastrarUsuarios(ActionEvent event) throws IOException {

        JogadorController j = new JogadorController();
        j.setJogadorDaVez(1);
        Jogador jogador = new Jogador();
        jogador = j.getJogador1();
        jogador.setNome(jogador1.getText());
        j.setJogador1(jogador);
        jogador = j.getJogador2();
        jogador.setNome(jogador2.getText());
        j.setJogador2(jogador);
        this.jogadores = j;
        posicionarEmbarcacoes(j.getJogador1());

        System.out.println(jogadores.getJogador1().getNome());
        try {

            App.setRoot("View/setupJogador1");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void setupJogador2(ActionEvent event) throws IOException {
        jogadores.setJogadorDaVez(2);
        System.out.println(jogadores.getJogador2().getNome());
        App.setRoot("View/setupJogador2");
    }

    @FXML
    void jogador1Jogar(ActionEvent event) throws IOException {
        try {
            jogadores.setJogadorDaVez(1);
            posicionarEmbarcacoes(jogadores.getJogador1());
            jogoIniciado = true;
            App.setRoot("View/Jogador1Jogar");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void moverFragata(ActionEvent event) throws IOException {
    }

    @FXML
    void moverSubmarino(ActionEvent event) throws IOException {
    }

    @FXML
    void moverCorveta(ActionEvent event) throws IOException {
    }

    @FXML
    void clicado(ActionEvent e) throws IOException {

    }

    @FXML
    void atacarJogador1(ActionEvent e) throws IOException {

        System.out.println(jogadores.getJogador1());
    }

    boolean moverDestroyer;

    @FXML
    void moverDestroyer(ActionEvent event) throws IOException {
        System.out.println("Jogador da vez: " + jogadores.getJogadorDaVez());
        try {
            destroyer.setOnMouseClicked(ee -> {
                if (ee.getButton() == MouseButton.SECONDARY) {
                }
                // rotacionarDestroyer();
            });
            destroyer.getOnMouseClicked();

            if (moverDestroyer == false) {
                moverDestroyer = true;

            } else {
                boolean podeMover = true;
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();
                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                if (jogadores.getJogadorDaVez() == 1) {
                    Quadrante tabuleiroJogador[][] = tabuleiControllerJogador1.getQuadrantes();

                    System.out.println("Aqui");

                    System.out.println(this.jogadores.getJogador1().getNome());
                    System.out.println("Passou daqui");
                    if (jogadores.getJogador1().getDestroyer().getEhVertical()) {
                        for (int i = linha; i < linha + 4; i++)
                            if (tabuleiroJogador[i][coluna].getPreenchidoPorNavio())
                                podeMover = false;
                    } else
                        for (int i = coluna; i < coluna + 4; i++)
                            if (tabuleiroJogador[linha][i].getPreenchidoPorNavio())
                                podeMover = false;

                }
                if (podeMover) {
                    tabuleiroDoJogador.setColumnIndex(destroyer, coluna);
                    tabuleiroAtacado.setRowIndex(destroyer, linha); //

                    moverDestroyer = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
