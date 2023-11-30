package br.com.imd.Model;

public class Jogador {
    private String nome;
    private int pontuacao;
    private Embarcacao[] embarcacoes = { new Destroyer(), new Corveta(), new Fragata(), new Submarino() };

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;

    }

    public Jogador() {
        this.pontuacao = 0;
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

}