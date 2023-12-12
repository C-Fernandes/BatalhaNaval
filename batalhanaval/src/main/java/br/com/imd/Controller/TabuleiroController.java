package br.com.imd.Controller;

import br.com.imd.Model.Embarcacao;
import br.com.imd.Model.Quadrante;
import java.util.Random;

public class TabuleiroController {

    /**
     *
     */
    private Quadrante[][] quadrantes;

    /**
     * 
     */
    public TabuleiroController() {
        this.quadrantes = new Quadrante[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                quadrantes[i][j] = new Quadrante(i, j);

    }

    /**
     * @return
     */
    public Quadrante[][] getQuadrantes() {
        return this.quadrantes;
    }

    /**
     * @param quadrante
     */
    public void setQuadrantes(Quadrante quadrante) {
        this.quadrantes[quadrante.getLinha()][quadrante.getColuna()] = quadrante;
    }

    /**
     * @param quadrantes
     */
    public void setQuadrantes(Quadrante[][] quadrantes) {
        this.quadrantes = quadrantes;
    }

    /**
     * @param e
     * @return
     */
    public Embarcacao[] verificarNavioDestruido(Embarcacao e[]) {

        Embarcacao novaEmbarcacao[], embarcacoes[] = e;
        int naviosDestruidos = 0, cont = 0;
        for (int i = 0; i < embarcacoes.length; i++) {
            int contador = 0;
            Quadrante posicoes[] = embarcacoes[i].getPosicao();
            for (int j = 0; j < posicoes.length; j++) {
                if ((this.quadrantes[posicoes[j].getLinha()][posicoes[j].getColuna()].getAtacado())) {
                    posicoes[j].setAtacado(true);
                    contador++;
                }
            }
            embarcacoes[i].setPosicao(posicoes);
            if (contador == embarcacoes[i].getTamanho()) {
                embarcacoes[i] = null;
                naviosDestruidos++;
            }
        }
        if (naviosDestruidos > 0) {
            novaEmbarcacao = new Embarcacao[embarcacoes.length - naviosDestruidos];
            for (int i = 0; i < embarcacoes.length; i++) {
                if (embarcacoes[i] != null) {
                    novaEmbarcacao[cont] = embarcacoes[i];
                    cont++;
                }
            }
        } else
            novaEmbarcacao = embarcacoes;
        return novaEmbarcacao;
    }

    /**
     * @param embarcacoes
     * @return
     */
    public Embarcacao[] posicionarAleatoriamente(Embarcacao embarcacoes[]) {

        for (int i = 0; i < embarcacoes.length; i++) {

            while (true) {
                int contador = 0;
                Random numAleatorio = new Random();
                int linhaAleatoria = numAleatorio.nextInt(10), colunaAleatoria = numAleatorio.nextInt(10);
                if (linhaAleatoria >= 0 && linhaAleatoria <= 9
                        && colunaAleatoria < (9 - embarcacoes[i].getTamanho()) + 1) {
                    boolean livre = true;
                    for (int k = colunaAleatoria; k < (9 - embarcacoes[i].getTamanho()) + 1; k++)
                        if (quadrantes[linhaAleatoria][k].getPreenchidoPorNavio())
                            livre = false;

                    if (livre) {
                        Quadrante posicaoEmbarcacao[] = new Quadrante[embarcacoes[i].getTamanho()];
                        try {
                            for (int j = colunaAleatoria; j < colunaAleatoria + (embarcacoes[i].getTamanho()); j++) {
                                this.quadrantes[linhaAleatoria][j].setOcupadoPor(embarcacoes[i].getTipo());
                                this.quadrantes[linhaAleatoria][j].setPreenchidoPorNavio(true);
                                posicaoEmbarcacao[contador] = quadrantes[linhaAleatoria][j];
                                contador++;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        embarcacoes[i].setPosicao(posicaoEmbarcacao);
                        break;
                    }
                }
            }
        }
        return embarcacoes;
    }

}