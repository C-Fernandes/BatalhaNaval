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
                if (!embarcacos[i].getEhVertical()) {
                    if (coluna + embarcacos[i].getTamanho() <= 10) {
                        for (int j = coluna; j < coluna + embarcacos[i].getTamanho(); j++) {
                            if (quadrantes[linha][j].getPreenchidoPorNavio()
                                    && quadrantes[linha][j].getOcupadoPor() != embarcacos[i].getTipo())
                                podeMover = false;

                        }
                        if (podeMover) {
                            for (int t = 0; t < posicoes.length; t++) {
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setPreenchidoPorNavio(false);
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setOcupadoPor(null);
                            }
                            for (int j = coluna; j < coluna + embarcacos[i].getTamanho(); j++) {
                                posicoes[contador] = quadrantes[linha][j];
                                contador++;
                                quadrantes[linha][j].setOcupadoPor(embarcacos[i].getTipo());
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
                            if (quadrantes[j][coluna].getPreenchidoPorNavio()
                                    && quadrantes[j][coluna].getOcupadoPor() != embarcacos[i].getTipo()) {
                                return false;
                            }
                        }
                        if (podeMover) {
                            for (int t = 0; t < posicoes.length; t++) {
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setPreenchidoPorNavio(false);
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setOcupadoPor(null);
                            }
                            for (int j = linha; j < linha + embarcacos[i].getTamanho(); j++) {
                                posicoes[contador] = quadrantes[j][coluna];
                                contador++;
                                quadrantes[j][coluna].setOcupadoPor(embarcacos[i].getTipo());
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

    public int rotacionarEmbarcacao(EmbarcacaoENUM navio) {
        int podeMover = 1;
        int contador = 0;
        Quadrante quadrantes[][] = tabuleiro.getQuadrantes();
        for (int i = 0; i < embarcacoes.length; i++) {
            if (navio == embarcacoes[i].getTipo()) {
                Quadrante posicoes[] = embarcacoes[i].getPosicao();
                if (embarcacoes[i].getEhVertical()) {
                    if (posicoes[0].getColuna() + embarcacoes[i].getTamanho() <= 10) {
                        for (int j = posicoes[0].getColuna() + 1; j < posicoes[0].getColuna()
                                + embarcacoes[i].getTamanho(); j++) {
                            if (quadrantes[posicoes[0].getLinha()][j].getPreenchidoPorNavio()) {
                                podeMover = 0;
                                return podeMover;
                            }
                        }
                        if (podeMover != 0) {
                            for (int t = 0; t < posicoes.length; t++) {
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setPreenchidoPorNavio(false);
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setOcupadoPor(null);
                            }
                            for (int j = posicoes[0].getColuna(); j < posicoes[0].getColuna()
                                    + embarcacoes[i].getTamanho(); j++) {
                                quadrantes[posicoes[0].getLinha()][j].setOcupadoPor(embarcacoes[i].getTipo());

                                quadrantes[posicoes[0].getLinha()][j].setPreenchidoPorNavio(true);
                                posicoes[contador] = quadrantes[posicoes[0].getLinha()][j];
                                contador++;
                            }
                            tabuleiro.setQuadrantes(quadrantes);
                            this.embarcacoes[i].setEhVertical(false);
                            this.embarcacoes[i].setPosicao(posicoes);
                            return podeMover = 1;
                        }

                    } else
                        return 0;

                } else {
                    if (posicoes[0].getLinha() + embarcacoes[i].getTamanho() <= 10) {
                        for (int j = posicoes[0].getLinha() + 1; j < posicoes[0].getLinha()
                                + embarcacoes[i].getTamanho(); j++) {
                            if (quadrantes[j][posicoes[0].getColuna()].getPreenchidoPorNavio()) {
                                return 0;
                            }
                        }
                        if (podeMover != 0) {
                            for (int t = 0; t < posicoes.length; t++) {
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setPreenchidoPorNavio(false);
                                quadrantes[posicoes[t].getLinha()][posicoes[t].getColuna()]
                                        .setOcupadoPor(null);
                            }
                            for (int j = posicoes[0].getLinha(); j < posicoes[0].getLinha()
                                    + embarcacoes[i].getTamanho(); j++) {
                                quadrantes[j][posicoes[0].getColuna()].setOcupadoPor(embarcacoes[i].getTipo());
                                quadrantes[j][posicoes[0].getColuna()].setPreenchidoPorNavio(true);
                                posicoes[contador] = quadrantes[j][posicoes[0].getColuna()];
                                contador++;
                            }
                            tabuleiro.setQuadrantes(quadrantes);
                            this.embarcacoes[i].setEhVertical(true);
                            this.embarcacoes[i].setPosicao(posicoes);
                            return 2;
                        }
                    } else {
                        return 0;
                    }
                }
            }
        }
        return 0;

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