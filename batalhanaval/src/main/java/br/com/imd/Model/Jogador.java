package br.com.imd.Model;

import br.com.imd.Controller.TabuleiroController;

public class Jogador {
    private String nome;
    private int pontuacao;
    private boolean mostrarNavios = false;
    private Embarcacao[] embarcacoes = { new Destroyer(), new Corveta(), new Fragata(), new Submarino() };
    TabuleiroController tabuleiro = new TabuleiroController();

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;

    }

    public Jogador() {
        this.pontuacao = 0;
    }

    public void atualizarEmbarcacoes() {
        this.embarcacoes = tabuleiro.verificarNavioDestruido(embarcacoes);
    }

    public Boolean moverEmbarcacoes(EmbarcacaoENUM navio, int linha, int coluna) {
        Quadrante quadrantes[][] = tabuleiro.getQuadrantes();
        Embarcacao[] embarcacos = this.embarcacoes;
        Quadrante posicoes[];
        Boolean podeMover = true;
        int contador = 0;

        for (int i = 0; i < embarcacos.length; i++) {
            if (navio == embarcacos[i].getTipo()) {
                posicoes = embarcacos[i].getPosicao();
                for (int t = 0; t < posicoes.length; t++)
                    quadrantes[posicoes[t].getX()][posicoes[t].getY()].setPreenchidoPorNavio(false);

                if (!embarcacos[i].getEhVertical()) {
                    if (coluna + embarcacos[i].getTamanho() <= 10) {
                        for (int j = coluna; j < coluna + embarcacos[i].getTamanho(); j++) {
                            if (quadrantes[linha][j].getPreenchidoPorNavio())
                                podeMover = false;
                        }
                        if (podeMover) {
                            for (int j = coluna; j < coluna + embarcacos[i].getTamanho(); j++) {
                                posicoes[contador] = quadrantes[linha][j];
                                contador++;
                                quadrantes[linha][j].setPreenchidoPorNavio(true);
                            }
                            tabuleiro.setQuadrantes(quadrantes);
                            embarcacos[i].setPosicao(posicoes);
                            this.embarcacoes = embarcacos;
                            return true;
                        }
                    } else
                        return false;
                } else {
                    if (linha + embarcacos[i].getTamanho() <= 10) {
                        for (int j = linha; j < linha + embarcacos[i].getTamanho(); j++) {
                            if (quadrantes[j][coluna].getPreenchidoPorNavio())
                                return false;
                        }
                        if (podeMover) {
                            for (int j = linha; j < linha + embarcacos[i].getTamanho(); j++) {
                                posicoes[contador] = quadrantes[j][coluna];
                                contador++;
                                quadrantes[j][coluna].setPreenchidoPorNavio(true);
                            }
                            tabuleiro.setQuadrantes(quadrantes);
                            embarcacos[i].setPosicao(posicoes);
                            this.embarcacoes = embarcacos;
                            return true;
                        }
                    } else
                        return false;
                }

            }
        }
        return false;

    }

    public void rotacionarEmbarcacao(EmbarcacaoENUM navio) {

        if (navio == EmbarcacaoENUM.DESTROYER) {
        }
        if (navio == EmbarcacaoENUM.FRAGATA) {
        }
        if (navio == EmbarcacaoENUM.CORVETA) {
        }
        if (navio == EmbarcacaoENUM.SUBMARINO) {
        }

    }

    public void posicionarEmbarcacoesAleatoriamente() {
        this.embarcacoes = tabuleiro.posicionarAleatoriamente(this.embarcacoes);
    }

    public Embarcacao getDestroyer() {
        return this.embarcacoes[0];
    }

    public Embarcacao getCorveta() {
        return this.embarcacoes[1];
    }

    public Embarcacao getFragata() {
        return this.embarcacoes[2];
    }

    public Embarcacao getSubmarino() {
        return this.embarcacoes[3];
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Embarcacao[] getEmbarcacoes() {
        return this.embarcacoes;
    }

    public void setEmbarcacoes(Embarcacao[] embarcacoes) {
        this.embarcacoes = embarcacoes;
    }

    public TabuleiroController getTabuleiro() {
        return this.tabuleiro;
    }

    public void setTabuleiro(TabuleiroController tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean isMostrarNavios() {
        return this.mostrarNavios;
    }

    public boolean getMostrarNavios() {
        return this.mostrarNavios;
    }

    public void setMostrarNavios() {
        if (mostrarNavios)
            this.mostrarNavios = false;
        else
            this.mostrarNavios = true;
    }

}