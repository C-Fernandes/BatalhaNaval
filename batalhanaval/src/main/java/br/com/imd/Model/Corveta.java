package br.com.imd.Model;

public class Corveta extends Embarcacao{

    public Corveta(){
        setTamanho(2);
        setTipo(EmbarcacaoENUM.CORVETA);
        setDestruido(false);
    }
}