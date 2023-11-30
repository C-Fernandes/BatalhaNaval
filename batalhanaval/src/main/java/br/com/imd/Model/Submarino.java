package br.com.imd.Model;

public class Submarino extends Embarcacao {

    public Submarino() {
        setTamanho(3);
        setTipo(EmbarcacaoENUM.SUBMARINO);
        setDestruido(false);
        setEhVertical(false);
    }
}