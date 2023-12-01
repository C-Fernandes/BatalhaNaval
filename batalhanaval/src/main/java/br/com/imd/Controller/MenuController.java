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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MenuController implements Initializable {
    private static MenuController instancia;
    JogadorController jogadores;
    String telaMomento;
    Jogador vencedor;

    boolean moverDestroyer = false;

    // FUNÇÃO MAIS IMPORTANTE, NÃO MEXER!
    public static MenuController getInstancia() {
        if (instancia == null) {
            instancia = new MenuController();
        }
        return instancia;
    }

    private MenuController() {
        this.jogadores = new JogadorController();
        this.telaMomento = "View/menu-inicial";
        vencedor = null;
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
    @FXML
    private Text jogadorVencedor;
    @FXML
    private Label j1;
    @FXML
    private Label j2;
    @FXML
    private Label ataque;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (vencedor != null)
            jogadorVencedor.setText(vencedor.getNome());
        else {
            Embarcacao embarcacoesDoJogador[] = new Embarcacao[4];
            Quadrante tInimigo[][], tJogador[][];
            Jogador jogador, oponente;
            if (telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial") {
                if (jogadores.getJogadorDaVez() == 1) {
                    jogador = jogadores.getJogador1();
                    oponente = jogadores.getJogador2();
                } else {
                    jogador = jogadores.getJogador2();
                    oponente = jogadores.getJogador1();
                }
                embarcacoesDoJogador = jogador.getEmbarcacoes();
                System.out.println("Qtd embarcacoes: " + embarcacoesDoJogador.length);
                tInimigo = oponente.getTabuleiro().getQuadrantes();
                tJogador = jogador.getTabuleiro().getQuadrantes();

                if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {
                    j1.setText(jogadores.getJogador1().getNome());
                    j2.setText(jogadores.getJogador2().getNome());
                    ataque.setText(jogador.getNome() + ", ataque!");
                    jogador.atualizarEmbarcacoes();
                    embarcacoesDoJogador = jogador.getEmbarcacoes();

                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (tJogador[i][j].isAtacado()) {int index = i *  tabuleiroDoJogador.getRowCount()+ j;
                                tabuleiroDoJogador.getChildren().get(index).setStyle("-fx-background-color: purple");
                                if (tJogador[i][j].isPreenchidoPorNavio())
                                    tabuleiroDoJogador.getChildren().get(index).setStyle("-fx-background-color: red");
                            }
                            if (tInimigo[i][j].isAtacado()) {int index = i *  tabuleiroAtacado.getRowCount()+ j;
                                tabuleiroAtacado.getChildren().get(index).setStyle("-fx-background-color: purple");
                                if (tInimigo[i][j].isPreenchidoPorNavio())
                                    tabuleiroAtacado.getChildren().get(index).setStyle("-fx-background-color: red");
                            }
                        }
                    }
                }
                Boolean destroyerB = false, corvetaB = false, submarinoB = false, fragataB = false;
                for (int i = 0; i < embarcacoesDoJogador.length; i++) {

                    Quadrante posicao[] = embarcacoesDoJogador[i].getPosicao();
                    if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                        tabuleiroDoJogador.setColumnIndex(destroyer, posicao[0].getY());
                        tabuleiroDoJogador.setRowIndex(destroyer, posicao[0].getX());
                        destroyerB = true;

                    }
                    if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                        tabuleiroDoJogador.setColumnIndex(corveta, posicao[0].getY());
                        tabuleiroDoJogador.setRowIndex(corveta, posicao[0].getX());
                        corvetaB = true;

                    }
                    if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.SUBMARINO) {
                        tabuleiroDoJogador.setColumnIndex(submarino, posicao[0].getY());
                        tabuleiroDoJogador.setRowIndex(submarino, posicao[0].getX());
                        submarinoB = true;

                    }
                    if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.FRAGATA) {
                        tabuleiroDoJogador.setColumnIndex(fragata, posicao[0].getY());
                        tabuleiroDoJogador.setRowIndex(fragata, posicao[0].getX());
                        fragataB = true;
                    }
                }
                if (!destroyerB)
                    tabuleiroDoJogador.getChildren().remove(destroyer);
                if (!corvetaB)
                    tabuleiroDoJogador.getChildren().remove(corveta);
                if (!fragataB)
                    tabuleiroDoJogador.getChildren().remove(fragata);
                if (!submarinoB)
                    tabuleiroDoJogador.getChildren().remove(submarino);

            }
        }

    }

    // Funções de controle:
    void criarJogadores() {
        jogadores.setJogadorDaVez(1);
        jogadores.getJogador1().setNome(jogador1.getText());
        jogadores.getJogador2().setNome(jogador2.getText());
        jogadores.getJogador1().posicionarEmbarcacoesAleatoriamente();
        jogadores.getJogador2().posicionarEmbarcacoesAleatoriamente();
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
        criarJogadores();
        telaMomento = "View/setupJogador1";
        App.setRoot("View/setupJogador1");
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
    void iniciarJogo(ActionEvent event) throws IOException {
        jogadores.setJogadorDaVez(1);
        try {
            telaMomento = "View/Jogador1Jogar";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            e.printStackTrace();
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

    boolean moverEmbarcacao(Embarcacao embarcacao, int linha, int coluna) {
        Jogador jogadorDaVez;
        if (jogadores.getJogadorDaVez() == 1)
            jogadorDaVez = jogadores.getJogador1();
        else
            jogadorDaVez = jogadores.getJogador2();

        Quadrante tabuleiroJogador[][] = jogadorDaVez.getTabuleiro().getQuadrantes();
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
        if (jogadores.getJogadorDaVez() == 1) {
            jogadores.getJogador1().getTabuleiro().setQuadrantes(novoTabuleiro);
            jogadores.getJogador1().setEmbarcacoes(embarcacoes);
        } else {
            jogadores.getJogador2().getTabuleiro().setQuadrantes(novoTabuleiro);
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

        if (jogadores.getJogadorDaVez() == 1)
            quadrantes = jogadores.getJogador2().getTabuleiro().getQuadrantes();
        else
            quadrantes = jogadores.getJogador1().getTabuleiro().getQuadrantes();

        Quadrante qAlvo = new Quadrante(linha, coluna, quadrantes[linha][coluna].getAtacado(),
                quadrantes[linha][coluna].getPreenchidoPorNavio());
        int index = linha * tabuleiroAtacado.getRowCount() + coluna;
        if (!qAlvo.isAtacado()) {
            quadrantes[linha][coluna].setAtacado(true);
            if (qAlvo.isPreenchidoPorNavio()) {
                tabuleiroAtacado.getChildren().get(index).setStyle("-fx-background-color: red");
            } else {
                if (jogadores.getJogadorDaVez() == 1) {
                    jogadores.setJogadorDaVez(2);
                    telaMomento = "View/Jogador2Jogar";
                    jogadores.getJogador2().getTabuleiro().setQuadrantes(quadrantes);
                } else {
                    jogadores.setJogadorDaVez(1);
                    telaMomento = "View/Jogador1Jogar";
                    jogadores.getJogador1().getTabuleiro().setQuadrantes(quadrantes);
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

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
