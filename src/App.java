import java.beans.EventHandler;

import TratadorEvento.TratadorEvento;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primeiroEstagio) throws Exception {
        StackPane raiz = new StackPane();

        raiz.getChildren().add(imagemFundo(0, 0,
                "https://img.freepik.com/vetores-premium/navios-de-batalha-da-marinha-barco-de-seguranca-de-combate-maritimo-e-conjunto-de-armas-de-navio-de-guerra_102902-363.jpg"));
        Scene scene = new Scene(raiz, 400, 150);

        raiz.getChildren().add(textoTamanhoRegulado(15,
                30, "Teste de figuras."));

        Button botao = new Button("Botão de teste, tratador de eventos");
        botao.setOnAction(new TratadorEvento());
        raiz.getChildren().add(botao);
        raiz.getChildren().add(retangulo(100, 40, 5, 90, Color.BLACK));
        primeiroEstagio.setScene(scene);
        primeiroEstagio.show();

    }

    ImageView imagemFundo(float x, float y, String url) {
        Image fundo = new Image(
                url);
        ImageView visualizadorImage = new ImageView(fundo);
        if (x != 0)
            visualizadorImage.setTranslateX(x);
        if (y != 0)
            visualizadorImage.setTranslateY(y);
        return visualizadorImage;
    }

    Rectangle retangulo(float altura, float largura, float x, float y, Paint color) {
        Rectangle retangulo = new Rectangle(altura, largura);
        retangulo.setTranslateX(x);
        retangulo.setTranslateY(y);
        retangulo.setFill(color);
        return retangulo;
    }

    Text textoTamanhoRegulado(float x, float y, String frase) {
        Text texto = new Text(frase);
        texto.setTranslateX(x);
        texto.setTranslateY(y);
        texto.setFill(Color.RED);

        return texto;

    }

}
