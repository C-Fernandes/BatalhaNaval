package br.com.imd.Controller;

import br.com.imd.App;
import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.EmbarcacaoENUM;
import br.com.imd.Model.Jogador;
import br.com.imd.Model.Quadrante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class MenuController implements Initializable {
    private static MenuController instancia;
    TabuleiroController tabuleiControllerJogador1, tabuleiControllerJogador2;
    JogadorController jogadores;
    boolean jogoIniciado;
    String telaMomento;

    boolean moverDestroyer = false;

    // FUNÇÃO MAIS IMPORTANTE, NÃO MEXER!
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Embarcacao embarcacoes[];
        if (!jogoIniciado && telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial") {
            if (jogadores.getJogadorDaVez() == 1) {
                embarcacoes = jogadores.getJogador1().getEmbarcacoes();
                embarcacoes = posicionarBarcosAleatoriamente(embarcacoes);
                jogadores.getJogador1().setEmbarcacoes(embarcacoes);
            } else {
                embarcacoes = jogadores.getJogador2().getEmbarcacoes();
                embarcacoes = posicionarBarcosAleatoriamente(embarcacoes);
                jogadores.getJogador2().setEmbarcacoes(embarcacoes);
            }
            for (int i = 0; i < embarcacoes.length; i++) {
                Quadrante posicao[] = embarcacoes[i].getPosicao();
                if (embarcacoes[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                    tabuleiroDoJogador.setColumnIndex(destroyer, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(destroyer, posicao[0].getX());
                }
                if (embarcacoes[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                    tabuleiroDoJogador.setColumnIndex(corveta, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(corveta, posicao[0].getX());
                }
                if (embarcacoes[i].getTipo() == EmbarcacaoENUM.SUBMARINO) {
                    tabuleiroDoJogador.setColumnIndex(submarino, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(submarino, posicao[0].getX());
                }
                if (embarcacoes[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                    tabuleiroDoJogador.setColumnIndex(corveta, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(corveta, posicao[0].getX());
                }
            }

        }
    }

    private MenuController() {
        this.tabuleiControllerJogador1 = new TabuleiroController();
        this.tabuleiControllerJogador2 = new TabuleiroController();
        this.jogadores = new JogadorController();
        this.jogoIniciado = false;
        this.telaMomento = "View/menu-inicial";
    }

    // Funções de controle:
    Embarcacao[] posicionarBarcosAleatoriamente(Embarcacao embarcacoes[]) {
        Embarcacao embarcacoesVetor[] = embarcacoes;
        for (int i = 0; i < embarcacoes.length; i++) {
            if (jogadores.getJogadorDaVez() == 1)
                embarcacoesVetor[i] = tabuleiControllerJogador1.pAleatoriamente(embarcacoes[i]);
            else
                embarcacoesVetor[i] = tabuleiControllerJogador2.pAleatoriamente(embarcacoes[i]);
        }
        return embarcacoesVetor;

    }

    // Funções Scenner Builder:
    @FXML
    void jogar(ActionEvent event) throws IOException {
        try {
            telaMomento = "View/cadastroUsuario";
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

        System.out.println(jogadores.getJogador1().getNome());
        try {
            telaMomento = "View/setupJogador1";
            App.setRoot("View/setupJogador1");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void setupJogador2(ActionEvent event) throws IOException {
        jogadores.setJogadorDaVez(2);
        System.out.println(jogadores.getJogador2().getNome());
        telaMomento = "View/setupJogador2";
        App.setRoot("View/setupJogador2");
    }

    @FXML
    void jogador1Jogar(ActionEvent event) throws IOException {
        try {
            jogadores.setJogadorDaVez(1);
            posicionarEmbarcacoes(jogadores.getJogador1());
            jogoIniciado = true;
            telaMomento = "View/Jogador1Jogar";
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
        if (moverDestroyer)
            moverDestroyer(e);
    }

    @FXML
    void atacarJogador1(ActionEvent e) throws IOException {

        System.out.println(jogadores.getJogador1());
    }

    boolean moverEmbarcacao(Embarcacao embarcacao, int linha, int coluna) {
        Quadrante tabuleiroJogador[][] = tabuleiControllerJogador1.getQuadrantes();
        if (embarcacao.getEhVertical()) {
            if (linha + (embarcacao.getTamanho() - 1) < 10) {

                System.out.println(linha + 4);
                for (int i = linha; i < linha + 4; i++)
                    if (tabuleiroJogador[i][coluna].getPreenchidoPorNavio())
                        return false;
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Coluna:  " + (coluna + 4));
            if (coluna + (embarcacao.getTamanho() - 1) < 10) {
                System.out.println("Entrou aqui");

                for (int i = coluna; i < coluna + 4; i++)

                    if (tabuleiroJogador[linha][i].getPreenchidoPorNavio()) {
                        System.out.println("Parou no if");
                        System.out.println("Linha " + linha + " coluna " + i);
                        System.out.println(tabuleiroJogador[linha][i].getPreenchidoPorNavio());
                        return false;
                    }
                return true;
            } else {
                return false;
            }
        }
    }

    @FXML
    void moverDestroyer(ActionEvent event) throws IOException {

        try {
            destroyer.setOnMouseClicked(ee -> {
                if (ee.getButton() == MouseButton.SECONDARY) {
                }
                // rotacionarDestroyer();
            });
            destroyer.getOnMouseClicked();
            System.out.println("Mover Destroyer antess: " + moverDestroyer);
            if (moverDestroyer == false) {
                moverDestroyer = true;
                System.out.println("Mover Destroyer no if: " + moverDestroyer);
            } else {
                moverDestroyer = false;
                boolean podeMover = true;
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();
                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                System.out.println(coluna);
                System.out.println(linha);
                if (jogadores.getJogadorDaVez() == 1) {
                    podeMover = moverEmbarcacao(jogadores.getJogador1().getDestroyer(), linha, coluna);
                }
                System.out.println("pode mover: " + podeMover);

                if (podeMover) {
                    // Falta alterar no jogador
                    tabuleiroDoJogador.setColumnIndex(destroyer, coluna);
                    tabuleiroAtacado.setRowIndex(destroyer, linha); //
                }

            }
            System.out.println("Mover Destroyer depois: " + moverDestroyer);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
