package minesweeper;

import minesweeper.custom.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create a StackPane to center the gridPane
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        // Create the stage
        stage.setTitle("Minesweeper - FH Campus Wien");
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.resizableProperty().setValue(Boolean.FALSE);

        // Load CSS for styling
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        // App icon
        Image icon = new Image(getClass().getResource("/art/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        // Setup the board
        Board board = new Board();
        board.setDifficulty("Easy");
        board.getInfo();

        // Generate the board and add it to the root StackPane
        GridPane gridPane = board.getGridPane();
        board.generateGrid();
        root.getChildren().add(gridPane);

        // Show the stage
        stage.show();
    }

}
