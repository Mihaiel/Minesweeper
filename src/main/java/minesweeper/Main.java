package minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import minesweeper.UI.SwitchScenes;
import minesweeper.UI.UserInterface;

public class Main extends Application
{
    private Stage stage;
    private StackPane root;

    @Override
    public void start(Stage primaryStage)
    {
        this.stage = primaryStage;
        root = new StackPane();
        root.setStyle("-fx-background-color: #F4F4F4;");

        // Comments in english
        UserInterface ui = new UserInterface();
        SwitchScenes switchScenes = new SwitchScenes(root, ui, this);


        // Comments in english
        switchScenes.setupMainMenu();

        // Comments in english
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setResizable(false);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);

        // Comments in english
        Image icon = new Image(getClass().getResource("/art/icon_new.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    // Comments in english
    public void createBoard(String difficulty)
    {
        System.out.println("Creating board with difficulty: " + difficulty);

        // Comments in english
        Board board = new Board();
        Cell[][] cellBoard = board.getGameBoard();
        board.setDifficulty(difficulty);
        board.generateGrid();


        // Comments in english
        GridPane gridPane = board.getGridPane();
        root.getChildren().clear();
        root.getChildren().add(gridPane);
    }
}
