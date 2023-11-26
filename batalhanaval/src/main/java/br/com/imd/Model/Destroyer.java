package br.com.imd.Model;

public class Destroyer extends Embarcacao {

    
    public Destroyer() {
        setTamanho(5);
        setTipo(EmbarcacaoENUM.DESTROYER);
        setDestruido(false);
    }
}

