import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class App  extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primeiroEstagio) throws Exception {
        StackPane raiz = new StackPane();
        Label label = new Label();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("tela_inicial.fxml"));

        Parent root = fxml.load();
        Scene telaInicial = new Scene(root);

        primeiroEstagio.setTitle("Batalha Naval");
        primeiroEstagio.setScene(telaInicial);;
        primeiroEstagio.show();

        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
