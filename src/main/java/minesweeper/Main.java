package minesweeper;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root, Color.LIGHTSKYBLUE);

        Image icon = new Image(getClass().getResource("/art/icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Minesweeper - FH Campus Wien");
        stage.setScene(scene);
        stage.show();
    }
}
