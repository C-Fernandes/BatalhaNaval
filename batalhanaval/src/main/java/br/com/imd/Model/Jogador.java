package br.com.imd.Model;

public class Jogador {
    private String nome;
    private int pontuacao;
    private Embarcacao[] embarcacoes;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.embarcacoes = new Embarcacao[] { new Destroyer(), new Corveta(), new Fragata(), new Submarino() };

    }
    //Prototipo de função para posicionar navios
    /*
     * public Quadrante[][] posicionarNavios(int[] x, int[] y, int tipo) {
     * // definir quadrantes ocupados pelas embarcações
     * boolean verificacaoX = true, verificacaoY = true;
     * if (x.length - 1 < 0 || x.length - 1 > 9)
     * return null;
     * if (y.length - 1 < 0 || y.length - 1 > 9) {
     * return null;
     * } else {
     * if (x.length > 1) {
     * int posicaoX = x[0];
     * for (int i = 1; i < x.length; i++) {
     * if (posicaoX - x[i] == -1 || posicaoX - x[i] == 1)
     * posicaoX = x[i];
     * else {
     * verificacaoX = false;
     * return null;
     * }
     * 
     * }
     * }
     * if (y.length > 1) {
     * int posicaoY = x[0];
     * for (int i = 1; i < x.length; i++) {
     * if (posicaoY - y[i] == -1 || posicaoY - y[i] == 1)
     * posicaoY = y[i];
     * else {
     * verificacaoX = false;
     * return null;
     * }
     * }
     * }
     * // Não existe barco 1x1
     * else return null;
     * }
     * }
     */

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