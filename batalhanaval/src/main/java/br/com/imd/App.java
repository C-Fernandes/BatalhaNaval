package br.com.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import br.com.imd.Controller.MenuController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MenuController menuController;

    @Override
    public void start(Stage stage) throws IOException {

        try {
            ImageView imageView = new ImageView();
            // Image gif = new Image("/src/main/java/br/com/imd/Controller/gifnavio.gif");
            // imageView.setImage(gif);

            this.menuController = MenuController.getInstancia();
            scene = new Scene(loadFXML("View/menu-inicial"), 1000, 650);
            stage.setTitle("Batalha Naval");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Problema aqui");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(menuController);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}