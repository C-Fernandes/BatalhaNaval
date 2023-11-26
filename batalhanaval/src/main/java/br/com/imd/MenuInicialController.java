package br.com.imd;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuInicialController {

    @FXML
    void iniciarJogo(ActionEvent event) throws IOException {
        App.setRoot("JOgador2Jogar");
    }

}
