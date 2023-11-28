package br.com.imd.Controller;

import br.com.imd.Model.Jogador;

public class JogadorController {
    private Jogador jogador1;
    private Jogador jogador2;

    public JogadorController(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    public JogadorController() {
    }

    public Jogador getJogador1() {
        return this.jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return this.jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

}