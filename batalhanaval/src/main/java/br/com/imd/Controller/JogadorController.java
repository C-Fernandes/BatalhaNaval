package br.com.imd.Controller;

import br.com.imd.Model.EmbarcacaoENUM;
import br.com.imd.Model.Jogador;
import java.util.Objects;

public class JogadorController {
    private Jogador jogador1;
    private Jogador jogador2;
    private int jogadorDaVez = 1;

    /**
     * @param j1
     * @param j2
     */
    void criarJogadores(String j1, String j2) {
        this.jogadorDaVez = 1;
        if (j1.equals(""))
            this.jogador1 = new Jogador("Jogador 1");
        else
            this.jogador1 = new Jogador(j1);
        if (j2.equals(""))
            this.jogador2 = new Jogador("Jogador 2");
        else
            this.jogador2 = new Jogador(j2);

        jogador1.posicionarEmbarcacoesAleatoriamente();
        jogador2.posicionarEmbarcacoesAleatoriamente();
    }

    /**
     * @return
     */
    public Boolean verificarVencedor() {
        if (jogadorDaVez == 1) {
            jogador2.atualizarEmbarcacoes();
            if (jogador2.getEmbarcacoes().length == 0)
                return true;
        } else {
            jogador1.atualizarEmbarcacoes();
            if (jogador1.getEmbarcacoes().length == 0)
                return true;
        }
        return false;
    }

    /**
     * @param jogador1
     * @param jogador2
     */
    public JogadorController(String jogador1, String jogador2) {
        this.jogador1 = new Jogador(jogador1);
        this.jogador2 = new Jogador(jogador2);
    }

    public JogadorController() {
    }

    /**
     * @param navio
     * @return
     */
    public int rotacionarEmbarcacao(EmbarcacaoENUM navio) {
        if (jogadorDaVez == 1)
            return jogador1.rotacionarEmbarcacao(navio);
        else
            return jogador2.rotacionarEmbarcacao(navio);
    }

    /**
     * @param navio
     * @param linha
     * @param coluna
     * @return
     */
    public Boolean moverEmbarcacoes(EmbarcacaoENUM navio, int linha, int coluna) {
        if (jogadorDaVez == 1)
            return jogador1.moverEmbarcacoes(navio, linha, coluna);
        else
            return jogador2.moverEmbarcacoes(navio, linha, coluna);

    }

    /**
     * @return
     */
    public Jogador getJogador1() {
        return this.jogador1;
    }

    /**
     * @param jogador1
     */
    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    /**
     * @return
     */
    public Jogador getJogador2() {
        return this.jogador2;
    }

    /**
     * @param jogador2
     */
    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    /**
     * @return int
     */
    public int getJogadorDaVez() {
        return this.jogadorDaVez;
    }

    /**
     * @param jogadorDaVez
     */
    public void setJogadorDaVez(int jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

    /**
     * 
     */
    public void iniciarNovoJogo() {
        jogador1 = null;
        jogador2 = null;
    }

}