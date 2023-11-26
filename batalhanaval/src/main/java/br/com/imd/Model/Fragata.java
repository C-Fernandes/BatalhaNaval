package br.com.imd.Model;

public class Fragata extends Embarcacao{
    public Fragata(){
        setTamanho(4);
        setTipo(EmbarcacaoENUM.FRAGATA);
        setDestruido(false);
    }
}