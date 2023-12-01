package br.com.imd.Model;

import br.com.imd.Controller.TabuleiroController;

public class Jogador {
    private String nome;
    private int pontuacao;
    private Embarcacao[] embarcacoes = { new Destroyer(), new Corveta(), new Fragata(), new Submarino() };
    TabuleiroController tabuleiro = new TabuleiroController();

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;

    }

    public Jogador() {
        this.pontuacao = 0;
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

}