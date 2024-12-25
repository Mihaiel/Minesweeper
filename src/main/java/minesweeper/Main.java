package minesweeper;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private StackPane root;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        root = new StackPane();
        root.setStyle("-fx-background-color: lightblue;");
        // Übergabe von "this" an SwitchScenes
        UserInterface ui = new UserInterface();
        SwitchScenes switchScenes = new SwitchScenes(root, ui, this);

        // MainMenu-Setup
        switchScenes.setupMainMenu();

        // Setze Szene und zeige das Fenster
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setResizable(false);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css); // CSS einbinden
        primaryStage.setScene(scene);

        // App-Icon setzen
        Image icon = new Image(getClass().getResource("/art/icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    // Methode zum Erstellen des Spielfelds und Hinzufügen zur Scene
    public void createBoard(String difficulty) {
        System.out.println("Creating board with difficulty: " + difficulty);

        // Spielfeld erstellen
        Board board = new Board();
        board.setDifficulty(difficulty); // Schwierigkeit setzen
        board.generateGrid(); // Spielfeld mit Zellen generieren

        // Holen der GridPane vom Board und Hinzufügen zur StackPane
        GridPane gridPane = board.getGridPane();
        root.getChildren().clear(); // Entferne vorherige Layouts
        root.getChildren().add(gridPane); // Neues Spielfeld hinzufügen
    }

    public static void main(String[] args) {
        launch(args);
    }
}
