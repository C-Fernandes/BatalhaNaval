package br.com.imd.Controller;

import br.com.imd.App;
import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.EmbarcacaoENUM;
import br.com.imd.Model.Jogador;
import br.com.imd.Model.Quadrante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable {
    private static MenuController instancia;
    private JogadorController jogadores;
    private String telaMomento;
    private Jogador vencedor;
    private Boolean verificador = true, moverDestroyer = false, moverCorveta = false, moverFragata = false,
            moverSubmarino = false;

    // Padrão Singleton
    /**
     * @return
     */
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
    private ImageView corveta;
    @FXML
    private ImageView submarino;
    @FXML
    private ImageView fragata;
    @FXML
    private ImageView destroyer;
    @FXML
    private ImageView agua;
    @FXML
    private ImageView aguaAcertou;
    @FXML
    private ImageView aguaErrou;
    @FXML
    private ImageView encerramento;
    @FXML
    private ImageView barquinho;
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
    private Label paragrafo1;
    @FXML
    private Label paragrafo2;
    @FXML
    private Label ataque;
    @FXML
    private CheckBox vNavios;
    @FXML
    private Button sairBotao;
    @FXML
    private Label mensagem;

    /**
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inicializarImgs();
        if (vencedor != null) {
            jogadorVencedor.setText(vencedor.getNome() + " ganhou o jogo!");
            vencedor = null;
        } else {
            Quadrante tInimigo[][], tJogador[][];
            Jogador jogador, oponente;
            if (telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial"
                    && telaMomento != "View/comoJogar") {
                if (jogadores.getJogadorDaVez() == 1) {
                    jogador = jogadores.getJogador1();
                    oponente = jogadores.getJogador2();
                } else {
                    jogador = jogadores.getJogador2();
                    oponente = jogadores.getJogador1();
                }
                tJogador = jogador.getTabuleiro().getQuadrantes();
                tInimigo = oponente.getTabuleiro().getQuadrantes();
                mensagem.setOpacity(0);
                if (telaMomento != "View/setupJogador2")
                    j1.setText(jogadores.getJogador1().getNome());
                if (telaMomento != "View/setupJogador1")
                    j2.setText(jogadores.getJogador2().getNome());
                if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {
                    verificador = true;
                    jogador.atualizarEmbarcacoes();
                    vNavios.setSelected(jogador.getMostrarNavios());
                    ataque.setText(jogador.getNome() + ", ataque!");
                    esconderMostrarNavios(jogador.getMostrarNavios());
                }
                mostrarQuadrantesAtacados(tJogador, tInimigo);
                mostrarNaviosJogador(jogador.getEmbarcacoes());

            }
        }
    }

    /**
     * @param m
     */
    public void mostrarMensagemError(String m) {
        mensagem.setText(m);
        mensagem.setOpacity(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            mensagem.setOpacity(0);
        });
        pause.play();
    }

    // Funções de controle:
    /**
     * @param v
     */
    void esconderMostrarNavios(Boolean v) {
        int opacidade;
        if (v)
            opacidade = 1;
        else
            opacidade = 0;

        if (destroyer != null)
            destroyer.setOpacity(opacidade);
        if (corveta != null)
            corveta.setOpacity(opacidade);
        if (fragata != null)
            fragata.setOpacity(opacidade);
        if (fragata != null)
            submarino.setOpacity(opacidade);
    }

    /**
     * 
     */
    void inicializarImgs() {
        Boolean verificar = true;
        if (telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial"
                && telaMomento != "View/comoJogar" && !telaMomento.equals("View/telaEncerramento")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int index = (i * 10) + j;
                    ImageView imageView;
                    imageView = (ImageView) tabuleiroDoJogador.getChildren().get(index);
                    Image img = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fundo-do-mar.png"));
                    imageView.setImage(img);
                    if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {
                        verificar = false;
                        imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);
                        imageView.setImage(img);

                    }
                }
            }
            Embarcacao embarcacaoJogador[];
            if (jogadores.getJogadorDaVez() == 1) {
                embarcacaoJogador = jogadores.getJogador1().getEmbarcacoes();
            } else {
                embarcacaoJogador = jogadores.getJogador2().getEmbarcacoes();
            }

            double antigaAltura, antigaLargura;

            for (int i = 0; i < embarcacaoJogador.length; i++) {
                if (embarcacaoJogador[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                    if (embarcacaoJogador[i].isEhVertical()) {
                        antigaAltura = destroyer.getFitHeight();
                        antigaLargura = destroyer.getFitWidth();
                        destroyer.setFitWidth(antigaAltura);
                        destroyer.setFitHeight(antigaLargura);

                        destroyer.setImage(
                                new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyerVertical.png")));
                        if (!verificar) {
                            destroyer.setTranslateY(60);
                            destroyer.setTranslateX(2);
                        } else {
                            destroyer.setTranslateY(80);
                            destroyer.setTranslateX(4);

                        }
                    } else {
                        destroyer.setTranslateY(0);
                        destroyer.setTranslateX(0);
                        destroyer.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyer.png")));
                    }
                }
                if (embarcacaoJogador[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                    if (embarcacaoJogador[i].isEhVertical()) {
                        antigaAltura = corveta.getFitHeight();
                        antigaLargura = corveta.getFitWidth();
                        corveta.setFitWidth(antigaAltura);
                        corveta.setFitHeight(antigaLargura);

                        corveta.setImage(
                                new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corvetaVertical.png")));
                        if (!verificar) {
                            corveta.setTranslateY(15);
                            corveta.setTranslateX(3);
                        } else {
                            corveta.setTranslateY(20);
                            corveta.setTranslateX(5);
                        }
                    } else {
                        corveta.setTranslateY(0);
                        corveta.setTranslateX(0);
                        corveta.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corveta.png")));
                    }
                }
                if (embarcacaoJogador[i].getTipo() == EmbarcacaoENUM.FRAGATA) {
                    if (embarcacaoJogador[i].isEhVertical()) {
                        antigaAltura = fragata.getFitHeight();
                        antigaLargura = fragata.getFitWidth();
                        fragata.setFitWidth(antigaAltura);
                        fragata.setFitHeight(antigaLargura);

                        fragata.setImage(
                                new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragataVertical.png")));
                        if (!verificar) {
                            fragata.setTranslateY(45);
                            fragata.setTranslateX(2);
                        } else {
                            fragata.setTranslateY(60);
                            fragata.setTranslateX(4);

                        }
                    } else {
                        fragata.setTranslateY(0);
                        fragata.setTranslateX(0);
                        fragata.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragata.png")));
                    }
                }
                if (embarcacaoJogador[i].getTipo() == EmbarcacaoENUM.SUBMARINO) {
                    if (embarcacaoJogador[i].isEhVertical()) {
                        antigaAltura = submarino.getFitHeight();
                        antigaLargura = submarino.getFitWidth();
                        submarino.setFitWidth(antigaAltura);
                        submarino.setFitHeight(antigaLargura);
                        submarino.setImage(
                                new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarinoVertical.png")));

                        if (!verificar) {
                            submarino.setTranslateY(30);
                            submarino.setTranslateX(3);
                        } else {
                            submarino.setTranslateY(40);
                            submarino.setTranslateX(5);

                        }
                    } else {
                        submarino.setTranslateY(0);
                        submarino.setTranslateX(0);
                        submarino.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarino.png")));
                    }
                }
            }
        }
        if (telaMomento.equals("View/telaEncerramento"))
            encerramento.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/encerramento.png")));
        if (telaMomento.equals("View/menu-inicial"))
            barquinho.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/navio.jpg")));
        if (telaMomento.equals("View/comoJogar")) {
            paragrafo1.setText("Batalha naval é um jogo onde dois jogadores dispoem de\n" +
                    "um tabuleiro 10x10 com 4 embarcações a serem posicionadas\n" +
                    "de modo estratégico. São elas:\n" +
                    "CORVETA: Uma embarcação pequena, com tamanho 1x2;\n" +
                    "SUBMARINO: Uma embarcação média, com tamanho 1x3;\n" +
                    "FRAGATA: Uma embarcação grande, com tamanho 1x4; e\n" +
                    "DESTROYER: Uma embarcação enorme, com tamanho 1x5.\n" +
                    "Durante o jogo eles serão adversários, e têm como\n" +
                    "objetivo destruirem todas as embarcações do oponente,\n" +
                    "sem saberem onde elas estarão posicionadas.");
            paragrafo2.setText("Durante cada turno do jogo, os jogadores devem lançar\n" +
                    "bombas sobre os quadrantes do adversário, até que não\n" +
                    "restem mais embarcações no tabuleiro. Se acertarem a\n" +
                    "jogada, terão uma nova tentativa no turno. Se errarem,\n" +
                    "O turno passa para o oponente. E assim se segue, até que\n" +
                    "haja um vencedor na partida.");
            corveta.setImage(
                    new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corvetaVertical.png")));
            submarino.setImage(
                    new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarinoVertical.png")));
            fragata.setImage(
                    new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragataVertical.png")));
            destroyer.setImage(
                    new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyerVertical.png")));
            agua.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fundo-do-mar.png")));
            aguaAcertou.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/explosao.jpeg")));
            aguaErrou.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/splash.png")));
        }
    }

    /**
     * @param tJogador
     * @param tInimigo
     */
    void mostrarQuadrantesAtacados(Quadrante tJogador[][], Quadrante tInimigo[][]) {
        Image destruido = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/explosao.jpeg"));
        Image splash = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/splash.png"));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int index = (i * 10) + j;
                if (tJogador[i][j].isAtacado()) {
                    ImageView imageView = (ImageView) tabuleiroDoJogador.getChildren().get(index);
                    imageView.setImage(splash);
                    if (tJogador[i][j].isPreenchidoPorNavio())
                        imageView.setImage(destruido);
                }
                if (tInimigo[i][j].isAtacado()) {
                    ImageView imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);
                    imageView.setImage(splash);
                    if (tInimigo[i][j].isPreenchidoPorNavio())
                        imageView.setImage(destruido);

                }
            }
        }
    }

    /**
     * @param embarcacoesDoJogador
     */
    void mostrarNaviosJogador(Embarcacao embarcacoesDoJogador[]) {
        Boolean destroyerB = false, corvetaB = false, submarinoB = false, fragataB = false;
        for (int i = 0; i < embarcacoesDoJogador.length; i++) {

            Quadrante posicao[] = embarcacoesDoJogador[i].getPosicao();
            if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                tabuleiroDoJogador.setColumnIndex(destroyer, posicao[0].getColuna());
                tabuleiroDoJogador.setRowIndex(destroyer, posicao[0].getLinha());
                destroyerB = true;
            }
            if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.CORVETA) {
                tabuleiroDoJogador.setColumnIndex(corveta, posicao[0].getColuna());
                tabuleiroDoJogador.setRowIndex(corveta, posicao[0].getLinha());
                corvetaB = true;
            }
            if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.SUBMARINO) {
                tabuleiroDoJogador.setColumnIndex(submarino, posicao[0].getColuna());
                tabuleiroDoJogador.setRowIndex(submarino, posicao[0].getLinha());
                submarinoB = true;
            }
            if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.FRAGATA) {
                tabuleiroDoJogador.setColumnIndex(fragata, posicao[0].getColuna());
                tabuleiroDoJogador.setRowIndex(fragata, posicao[0].getLinha());
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

    /**
     * @param embarcacao
     * @param linha
     * @param coluna
     * @return
     */
    boolean moverEmbarcacao(Embarcacao embarcacao, int linha, int coluna) {
        Jogador jogadorDaVez;
        if (jogadores.getJogadorDaVez() == 1)
            jogadorDaVez = jogadores.getJogador1();
        else
            jogadorDaVez = jogadores.getJogador2();

        Quadrante tabuleiroJogador[][] = jogadorDaVez.getTabuleiro().getQuadrantes();
        if (embarcacao.getEhVertical()) {
            if (linha + (embarcacao.getTamanho() - 1) < 10) {

                for (int i = linha; i < linha + 4; i++)
                    if (tabuleiroJogador[i][coluna].getPreenchidoPorNavio())
                        return false;
                return true;
            } else {
                return false;
            }
        } else {
            if (coluna + (embarcacao.getTamanho() - 1) < 10) {

                for (int i = coluna; i < coluna + 4; i++)

                    if (tabuleiroJogador[linha][i].getPreenchidoPorNavio()) {
                        return false;
                    }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param embarcacao
     */
    public void rotacionar(EmbarcacaoENUM embarcacao) {
        double antigaAltura, antigaLargura;
        int podeRotacionar = jogadores.rotacionarEmbarcacao(embarcacao);
        if (podeRotacionar != 0) {
            if (embarcacao == EmbarcacaoENUM.DESTROYER) {
                antigaAltura = destroyer.getFitHeight();
                antigaLargura = destroyer.getFitWidth();
                destroyer.setFitWidth(antigaAltura);
                destroyer.setFitHeight(antigaLargura);
                if (podeRotacionar == 1) {
                    destroyer.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyer.png")));
                    destroyer.setTranslateY(0);
                    destroyer.setTranslateX(0);
                }
                if (podeRotacionar == 2) {
                    destroyer.setImage(
                            new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyerVertical.png")));

                    destroyer.setTranslateY(80);
                    destroyer.setTranslateX(4);
                }
            }
            if (embarcacao == EmbarcacaoENUM.CORVETA) {
                antigaAltura = corveta.getFitHeight();
                antigaLargura = corveta.getFitWidth();
                corveta.setFitWidth(antigaAltura);
                corveta.setFitHeight(antigaLargura);
                if (podeRotacionar == 1) {
                    corveta.setTranslateY(0);
                    corveta.setTranslateX(0);
                    corveta.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corveta.png")));
                }
                if (podeRotacionar == 2) {
                    corveta.setTranslateY(20);
                    corveta.setTranslateX(5);

                    corveta.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corvetaVertical.png")));
                }
            }
            if (embarcacao == EmbarcacaoENUM.FRAGATA) {
                antigaAltura = fragata.getFitHeight();
                antigaLargura = fragata.getFitWidth();
                fragata.setFitWidth(antigaAltura);
                fragata.setFitHeight(antigaLargura);
                if (podeRotacionar == 1) {
                    fragata.setTranslateY(0);
                    fragata.setTranslateX(0);
                    fragata.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragata.png")));
                }
                if (podeRotacionar == 2) {
                    fragata.setTranslateY(60);
                    fragata.setTranslateX(4);
                    fragata.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragataVertical.png")));
                }
            }

            if (embarcacao == EmbarcacaoENUM.SUBMARINO) {
                antigaAltura = submarino.getFitHeight();
                antigaLargura = submarino.getFitWidth();
                submarino.setFitWidth(antigaAltura);
                submarino.setFitHeight(antigaLargura);
                if (podeRotacionar == 1) {
                    submarino.setTranslateY(0);
                    submarino.setTranslateX(0);
                    submarino.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarino.png")));
                }
                if (podeRotacionar == 2) {
                    submarino.setTranslateY(40);
                    submarino.setTranslateX(5);
                    submarino.setImage(
                            new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarinoVertical.png")));

                }
            }
        } else {
            mostrarMensagemError("Não é possivel rotacionar\na embarcação, posição inválida");
        }
    }

    // Funções Scenner Builder:
    /**
     * @throws IOException
     */
    @FXML
    public void iniciarNovoJogo() throws IOException {
        jogadores.iniciarNovoJogo();
        telaMomento = "View/menu-inicial";
        App.setRoot(telaMomento);
    }

    /**
     * 
     */
    @FXML
    void visualizar() {
        Jogador jogadorDaVez = new Jogador();
        if (jogadores.getJogadorDaVez() == 1) {
            jogadores.getJogador1().setMostrarNavios();
            jogadorDaVez = jogadores.getJogador1();
        }
        if (jogadores.getJogadorDaVez() == 2) {
            jogadores.getJogador2().setMostrarNavios();
            jogadorDaVez = jogadores.getJogador2();
        }
        esconderMostrarNavios(jogadorDaVez.getMostrarNavios());
    }

    /**
     * @throws IOException
     */
    @FXML
    void jogar() throws IOException {
        try {
            telaMomento = "View/cadastroUsuario";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela para cadastro de usuario." + e.getMessage());
        }
    }

    /**
     * @throws IOException
     */
    @FXML
    void comoJogar() throws IOException {
        try {
            telaMomento = "View/comoJogar";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());

        }
    }

    /**
     * @throws IOException
     */
    @FXML
    void sairDoJogo() throws IOException {
        try {
            Stage stage = (Stage) sairBotao.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());
        }
    }

    /**
     * @throws IOException
     */
    @FXML
    void voltarMenuInicial() throws IOException {
        try {
            telaMomento = "View/menu-inicial";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());
        }
    }

    /**
     * @throws IOException
     */
    @FXML
    void cadastrarUsuarios() throws IOException {
        jogadores.criarJogadores(jogador1.getText(), jogador2.getText());
        try {
            telaMomento = "View/setupJogador1";
            App.setRoot("View/setupJogador1");
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());
        }

    }

    /**
     * @throws IOException
     */
    @FXML
    void setupJogador2() throws IOException {
        jogadores.setJogadorDaVez(2);
        try {
            telaMomento = "View/setupJogador2";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());
        }
    }

    /**
     * @throws IOException
     */
    @FXML
    void iniciarJogo() throws IOException {
        jogadores.setJogadorDaVez(1);
        try {
            telaMomento = "View/Jogador1Jogar";
            App.setRoot(telaMomento);
        } catch (Exception e) {
            System.out.println("Erro ao trocar de tela" + e.getMessage());
        }

    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void moverFragata(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.SECONDARY) {
            rotacionar(EmbarcacaoENUM.FRAGATA);
        } else {
            if (!moverFragata) {
                moverFragata = true;
            } else {
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();

                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                moverFragata = jogadores.moverEmbarcacoes(EmbarcacaoENUM.FRAGATA, linha, coluna);
                if (moverFragata) {
                    tabuleiroDoJogador.setColumnIndex(fragata, coluna);
                    tabuleiroDoJogador.setRowIndex(fragata, linha); //
                } else {
                    mostrarMensagemError("Não é possivel mover\no fragata para está posição");
                }
                moverFragata = false;
            }
        }
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void moverSubmarino(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.SECONDARY) {
            rotacionar(EmbarcacaoENUM.SUBMARINO);
        } else {
            if (!moverSubmarino) {
                moverSubmarino = true;
            } else {
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();

                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                moverSubmarino = jogadores.moverEmbarcacoes(EmbarcacaoENUM.SUBMARINO, linha, coluna);
                if (moverSubmarino) {
                    tabuleiroDoJogador.setColumnIndex(submarino, coluna);
                    tabuleiroDoJogador.setRowIndex(submarino, linha); //
                } else {
                    mostrarMensagemError("Não é possivel mover\no submarino para está posição");
                }
                moverSubmarino = false;
            }
        }
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void moverCorveta(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.SECONDARY) {
            rotacionar(EmbarcacaoENUM.CORVETA);
        } else {
            if (!moverCorveta) {
                moverCorveta = true;
            } else {
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();

                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                moverCorveta = jogadores.moverEmbarcacoes(EmbarcacaoENUM.CORVETA, linha, coluna);
                if (moverCorveta) {
                    tabuleiroDoJogador.setColumnIndex(corveta, coluna);
                    tabuleiroDoJogador.setRowIndex(corveta, linha); //
                } else {
                    mostrarMensagemError("Não é possivel mover\no corveta para está posição");
                }
                moverCorveta = false;
            }
        }

    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void moverDestroyer(MouseEvent event) throws IOException {

        if (event.getButton() == MouseButton.SECONDARY) {
            rotacionar(EmbarcacaoENUM.DESTROYER);
        } else {
            if (!moverDestroyer) {
                moverDestroyer = true;
            } else {
                int linha = 0, coluna = 0;
                Node source = (Node) event.getSource();

                if (tabuleiroDoJogador.getRowIndex(source) != null)
                    linha = tabuleiroDoJogador.getRowIndex(source);

                if (tabuleiroDoJogador.getColumnIndex(source) != null)
                    coluna = tabuleiroDoJogador.getColumnIndex(source);

                moverDestroyer = jogadores.moverEmbarcacoes(EmbarcacaoENUM.DESTROYER, linha, coluna);

                if (moverDestroyer) {
                    tabuleiroDoJogador.setColumnIndex(destroyer, coluna);
                    tabuleiroAtacado.setRowIndex(destroyer, linha); //
                } else {
                    mostrarMensagemError("Não é possivel mover\no destroyer para está posição");
                }
                moverDestroyer = false;

            }
        }

    }

    /**
     * @param e
     * @throws IOException
     */
    @FXML
    void clicado(MouseEvent e) throws IOException {
        if (moverCorveta)
            moverCorveta(e);
        if (moverDestroyer)
            moverDestroyer(e);
        if (moverFragata)
            moverFragata(e);
        if (moverSubmarino)
            moverSubmarino(e);
    }

    /**
     * @param event
     */
    @FXML
    void atacar(MouseEvent event) {
        if (verificador) {
            int linha = 0, coluna = 0;
            Node source = (Node) event.getSource();
            Quadrante quadrantes[][];
            Jogador jDaVez;
            if (tabuleiroAtacado.getRowIndex(source) != null)
                linha = tabuleiroAtacado.getRowIndex(source);
            if (tabuleiroAtacado.getColumnIndex(source) != null)
                coluna = tabuleiroAtacado.getColumnIndex(source);

            if (jogadores.getJogadorDaVez() == 1) {
                quadrantes = jogadores.getJogador2().getTabuleiro().getQuadrantes();
                jDaVez = jogadores.getJogador1();
            } else {
                quadrantes = jogadores.getJogador1().getTabuleiro().getQuadrantes();
                jDaVez = jogadores.getJogador2();
            }

            int index = (linha * 10) + coluna;

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                try {
                    App.setRoot(telaMomento);
                } catch (IOException ee) {
                    System.out.println("Erro ao trocar de tela" + ee.getMessage());
                }
            });

            if (!quadrantes[linha][coluna].isAtacado()) {
                quadrantes[linha][coluna].setAtacado(true);
                ImageView imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);

                if (quadrantes[linha][coluna].isPreenchidoPorNavio()) {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/explosao.jpeg")));
                    mensagem.setOpacity(1);
                    mensagem.setText("Você acertou o navio inimigo,\ncontinue atacando!");

                    if (jogadores.verificarVencedor()) {
                        telaMomento = "View/telaEncerramento";
                        vencedor = jDaVez;
                        mensagem.setText("Parabéns,\nvocê venceu!");
                        verificador = false;
                        pause.play();
                    }

                } else {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/splash.png")));
                    mensagem.setOpacity(1);
                    mensagem.setText("Você acertou a água,\nvez do próximo jogador");
                    if (jogadores.getJogadorDaVez() == 1) {
                        jogadores.setJogadorDaVez(2);
                        telaMomento = "View/Jogador2Jogar";
                        jogadores.getJogador2().getTabuleiro().setQuadrantes(quadrantes);
                    } else {
                        jogadores.setJogadorDaVez(1);
                        telaMomento = "View/Jogador1Jogar";
                        jogadores.getJogador1().getTabuleiro().setQuadrantes(quadrantes);
                    }
                    verificador = false;
                    pause.play();
                }
            }
        }
    }
}
