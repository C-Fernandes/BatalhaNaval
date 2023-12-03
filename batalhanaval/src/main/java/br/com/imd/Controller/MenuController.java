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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private ImageView corveta;
    @FXML
    private ImageView submarino;
    @FXML
    private ImageView fragata;
    @FXML
    private ImageView destroyer;
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

    @FXML
    private CheckBox vNavios;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (vencedor != null)
            jogadorVencedor.setText(vencedor.getNome());
        else {

            inicializarImgs();
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
                tJogador = jogador.getTabuleiro().getQuadrantes();
                tInimigo = oponente.getTabuleiro().getQuadrantes();

                if (telaMomento != "View/setupJogador2")
                    j1.setText(jogadores.getJogador1().getNome());
                if (telaMomento != "View/setupJogador1")
                    j2.setText(jogadores.getJogador2().getNome());
                if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {
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

    // Funções de controle:
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

    void inicializarImgs() {
        if (telaMomento != "View/cadastroUsuario" && telaMomento != "View/menu-inicial") {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int index = (i * 10) + j;
                    ImageView imageView;
                    imageView = (ImageView) tabuleiroDoJogador.getChildren().get(index);
                    Image img = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fundo-do-mar.png"));
                    imageView.setImage(img);
                    if (telaMomento != "View/setupJogador1" && telaMomento != "View/setupJogador2") {
                        imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);
                        imageView.setImage(img);

                    }
                }
            }
            corveta.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/corveta.png")));
            destroyer.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/destroyer.png")));
            fragata.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/fragata.png")));
            submarino.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/submarino.png")));
        }
    }

    void mostrarQuadrantesAtacados(Quadrante tJogador[][], Quadrante tInimigo[][]) {
        Image destruido = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/explosao.jpeg"));
        Image splash = new Image(getClass().getResourceAsStream("/br/com/imd/imgs/splash.png"));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int index = (i * 10) + j;
                if (tJogador[i][j].isAtacado()) {
                    ImageView imageView = (ImageView) tabuleiroDoJogador.getChildren().get(index);
                    imageView.setImage(splash);
                    if (tJogador[i][j].isPreenchidoPorNavio()) {
                        imageView.setImage(destruido);

                    }
                }
                if (tInimigo[i][j].isAtacado()) {
                    ImageView imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);
                    imageView.setImage(splash);
                    if (tInimigo[i][j].isPreenchidoPorNavio()) {
                        imageView.setImage(destruido);
                    }
                }
            }
        }
    }

    void mostrarNaviosJogador(Embarcacao embarcacoesDoJogador[]) {
        System.out.println("Entrou aqui");
        Boolean destroyerB = false, corvetaB = false, submarinoB = false, fragataB = false;
        for (int i = 0; i < embarcacoesDoJogador.length; i++) {

            Quadrante posicao[] = embarcacoesDoJogador[i].getPosicao();
            if (embarcacoesDoJogador[i].getTipo() == EmbarcacaoENUM.DESTROYER) {
                System.out.println("Passou aqui");
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

    void criarJogadores() {
        jogadores.setJogadorDaVez(1);
        jogadores.getJogador1().setNome(jogador1.getText());
        jogadores.getJogador2().setNome(jogador2.getText());
        jogadores.getJogador1().posicionarEmbarcacoesAleatoriamente();
        jogadores.getJogador2().posicionarEmbarcacoesAleatoriamente();
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

    // Funções Scenner Builder:

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
    void moverFragata(MouseEvent event) throws IOException {
    }

    @FXML
    void moverSubmarino(MouseEvent event) throws IOException {
    }

    @FXML
    void moverCorveta(MouseEvent event) throws IOException {
    }

    @FXML
    void clicado(MouseEvent e) throws IOException {
        if (moverDestroyer)
            moverDestroyer(e);
    }

    @FXML
    void atacar(MouseEvent event) throws IOException {
        try {

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
            int index = (linha * 10) + coluna;
            if (!quadrantes[linha][coluna].isAtacado()) {
                quadrantes[linha][coluna].setAtacado(true);
                System.out.println("mudou para atacado tabuleiro inimigo");
                if (quadrantes[linha][coluna].isPreenchidoPorNavio()) {
                    ImageView imageView = (ImageView) tabuleiroAtacado.getChildren().get(index);
                    imageView.setImage(new Image(getClass().getResourceAsStream("/br/com/imd/imgs/explosao.jpeg")));
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
        } catch (Exception e) {
            System.out.println("Aqui");
            e.printStackTrace();
        }
    }

    @FXML
    void moverDestroyer(MouseEvent event) throws IOException {

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
