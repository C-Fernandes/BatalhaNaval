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
        System.out.println(jogoIniciado);
        Embarcacao embarcacoesDoJogador[] = new Embarcacao[4];
        Quadrante tabuleiroInimigo[][] = new Quadrante[10][10];
        if (telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial") {
            if (jogadores.getJogadorDaVez() == 1) {
                System.out.println("Entrou jogador 1");
                embarcacoesDoJogador = jogadores.getJogador1().getEmbarcacoes();
                tabuleiroInimigo = tabuleiControllerJogador2.getQuadrantes();
                if (!jogoIniciado) {
                    embarcacoesDoJogador = posicionarBarcosAleatoriamente(embarcacoesDoJogador);
                    jogadores.getJogador1().setEmbarcacoes(embarcacoesDoJogador);
                    System.out.println("\n\n\n\n\nPassou aqui");
                }
            }
            if (jogadores.getJogadorDaVez() == 2) {
                System.out.println("Entrou jogador 2");
                embarcacoesDoJogador = jogadores.getJogador2().getEmbarcacoes();
                tabuleiroInimigo = tabuleiControllerJogador1.getQuadrantes();
                if (!jogoIniciado) {
                    embarcacoesDoJogador = posicionarBarcosAleatoriamente(embarcacoesDoJogador);
                    jogadores.getJogador2().setEmbarcacoes(embarcacoesDoJogador);
                }

            }
            System.out.println("\nJogador da vez:" + jogadores.getJogadorDaVez());
            for (int j = 0; j < 4; j++) {
                System.out.println(embarcacoesDoJogador[j].getTipo());
                Quadrante q[] = embarcacoesDoJogador[j].getPosicao();
                System.out.println("linha - coluna: " + q[0].getX() + " - " + q[0].getY());

            }
            if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {

                Button botao = new Button();
                botao.setPrefSize(42, 42);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (tabuleiroInimigo[i][j].isPreenchidoPorNavio()) {

                        } else {

                        }
                    }
                }
            }

            for (int i = 0; i < embarcacoesDoJogador.length; i++) {

                Quadrante posicao[] = embarcacoesDoJogador[i].getPosicao();

                if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                    tabuleiroDoJogador.setColumnIndex(destroyer, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(destroyer, posicao[0].getX());
                    System.out.println(posicao[0].getY());
                    System.out.println(posicao[0].getX());

                }
                if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                    tabuleiroDoJogador.setColumnIndex(corveta, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(corveta, posicao[0].getX());

                }
                if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.SUBMARINO) {
                    tabuleiroDoJogador.setColumnIndex(submarino, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(submarino, posicao[0].getX());

                }
                if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.FRAGATA) {
                    tabuleiroDoJogador.setColumnIndex(fragata, posicao[0].getY());
                    tabuleiroDoJogador.setRowIndex(fragata, posicao[0].getX());

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
        System.out.println("Posicionar aleatorio ");
        System.out.println("tamanho embarcacoes:" + embarcacoes.length);
        Embarcacao embarcacoesVetor[] = new Embarcacao[4];
        for (int i = 0; i < embarcacoes.length; i++) {
            System.out.println(jogadores.getJogadorDaVez());
            System.out.println("p aleatoriamente");
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
            App.setRoot(telaMomento);
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
        try {

            jogadores.setJogadorDaVez(2);
            System.out.println(jogadores.getJogador2().getNome());
            telaMomento = "View/setupJogador2";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void jogador1Jogar(ActionEvent event) throws IOException {

        jogadores.setJogadorDaVez(1);
        jogoIniciado = true;
        telaMomento = "View/Jogador1Jogar";
        App.setRoot(telaMomento);

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

    void atualizarEmbarcacoes(Embarcacao embarcacao) {
        Embarcacao embarcacoes[];
        if (jogadores.getJogadorDaVez() == 1)
            embarcacoes = jogadores.getJogador1().getEmbarcacoes();
        else
            embarcacoes = jogadores.getJogador2().getEmbarcacoes();

        for (int i = 0; i < embarcacoes.length; i++) {
            if (embarcacoes[i].getTipo() == EmbarcacaoENUM.DESTROYER
                    && embarcacao.getTipo() == EmbarcacaoENUM.DESTROYER) {
                embarcacoes[i] = embarcacao;
            }
        }
        Quadrante novoTabuleiro[][] = new Quadrante[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                novoTabuleiro[i][j] = new Quadrante(i, j);
            }
        }
        Quadrante posicao[];
        for (int i = 0; i < 4; i++) {
            posicao = embarcacoes[i].getPosicao();
            System.out.println("Posicao tamanho - " + posicao.length);

            for (int j = 0; j < posicao.length; j++) {
                System.out.println("J: " + j);
                System.out.println(posicao[j].getX());
                System.out.println(posicao[j].getY());
                novoTabuleiro[posicao[j].getX()][posicao[j].getY()].setPreenchidoPorNavio(true);
            }

        }
        Jogador j;
        if (jogadores.getJogadorDaVez() == 1) {
            tabuleiControllerJogador1.setQuadrantes(novoTabuleiro);

            j = jogadores.getJogador1();
            j.setEmbarcacoes(embarcacoes);
            jogadores.setJogador1(j);

        } else {
            tabuleiControllerJogador2.setQuadrantes(novoTabuleiro);
            jogadores.getJogador2().setEmbarcacoes(embarcacoes);
        }
    }

    @FXML
    void atacar(ActionEvent event) throws IOException {

        int linha = 0, coluna = 0;
        Node source = (Node) event.getSource();

        if (tabuleiroAtacado.getRowIndex(source) != null)
            linha = tabuleiroAtacado.getRowIndex(source);
        if (tabuleiroAtacado.getColumnIndex(source) != null)
            coluna = tabuleiroAtacado.getColumnIndex(source);

        Quadrante quadrantes[][];

        if (jogadores.getJogadorDaVez() == 1) {
            quadrantes = tabuleiControllerJogador2.getQuadrantes();
        } else {
            quadrantes = tabuleiControllerJogador1.getQuadrantes();
        }
        Quadrante qAlvo = new Quadrante(linha, coluna, quadrantes[linha][coluna].getAtacado(),
                quadrantes[linha][coluna].getPreenchidoPorNavio());
        System.out.println("linha alvo: " + qAlvo.getX());
        System.out.println("coluna alvo: " + qAlvo.getY());
        System.out.println(qAlvo.isPreenchidoPorNavio());
        System.out.println(!qAlvo.isAtacado());

        if (!qAlvo.isAtacado()) {
            System.out.println("Entrou no não atacado");
            Button botao = new Button();
            botao.setStyle("-fx-background-color: #add8e6");
            botao.setPrefSize(42, 42);

            quadrantes[linha][coluna].setAtacado(true);

            if (qAlvo.isPreenchidoPorNavio()) {
                botao.setStyle("-fx-background-color: red");
                tabuleiroAtacado.add(botao, coluna, linha);
                // verificar qual navio foi atacado
            } else {
                tabuleiroAtacado.add(botao, coluna, linha);
                if (jogadores.getJogadorDaVez() == 1) {
                    jogadores.setJogadorDaVez(2);
                    telaMomento = "View/Jogador2Jogar";
                    tabuleiControllerJogador2.setQuadrantes(qAlvo);
                } else {
                    tabuleiControllerJogador1.setQuadrantes(qAlvo);
                    jogadores.setJogadorDaVez(1);
                    telaMomento = "View/Jogador1Jogar";

                }
                App.setRoot(telaMomento);

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
                Embarcacao embarcacao;
                if (jogadores.getJogadorDaVez() == 1) {
                    podeMover = moverEmbarcacao(jogadores.getJogador1().getDestroyer(), linha, coluna);
                    embarcacao = jogadores.getJogador1().getDestroyer();
                } else {
                    podeMover = moverEmbarcacao(jogadores.getJogador2().getDestroyer(), linha, coluna);
                    embarcacao = jogadores.getJogador2().getDestroyer();
                }
                System.out.println("pode mover: " + podeMover);

                if (podeMover) {
                    atualizarEmbarcacoes(embarcacao);

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
