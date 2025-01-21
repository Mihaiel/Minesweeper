package minesweeper;

import minesweeper.Sounds.SoundManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
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

        // Initializes the user interface and SwitchScenes
        UserInterface ui = new UserInterface();
        SwitchScenes switchScenes = new SwitchScenes(root, ui, this);

        // Plays the title screen background music.
        SoundManager.playTitleScreenMusic();

        // Sets up and displays the main menu of the application.
        switchScenes.setupMainMenu();

        // Create a new Scene with dimensions and Load Css Style
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setResizable(false);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);

        Image icon = new Image(getClass().getResource("/art/icon_new.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    // Comments in english
    public void createBoard(String difficulty)
    {
        System.out.println("Creating board with difficulty: " + difficulty);

        // Create Board, set difficulty and generate
        Board board = new Board();
        board.setDifficulty(difficulty);
        board.generateGrid();

        // Retrieves the Gridpane, Clears the current of the root pane and Adds the Pane
        GridPane gridPane = board.getGridPane();
        root.getChildren().clear();
        root.getChildren().add(gridPane);
    }
}
