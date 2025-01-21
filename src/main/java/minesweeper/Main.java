package minesweeper;

import minesweeper.Sounds.SoundManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import minesweeper.UI.SwitchScenes;
import minesweeper.UI.UserInterface;

public class Main extends Application {
    private Stage stage;
    private StackPane root;
    private String currentDifficulty; // Stores the currently selected difficulty

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        root = new StackPane();
        root.setStyle("-fx-background-color: #F4F4F4;");

        // Initialize UserInterface and SwitchScenes
        UserInterface ui = new UserInterface();
        SwitchScenes switchScenes = new SwitchScenes(root, ui, this);

        // Play title screen music
        SoundManager.playTitleScreenMusic();

        // Setup main menu
        switchScenes.setupMainMenu();

        // Create and set the scene
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setResizable(false);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);

        // Set application icon and title
        Image icon = new Image(getClass().getResource("/art/interface/icon_new.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();

        // Add KeyEvent handler for F5 to regenerate the board
        scene.setOnKeyPressed(event -> handleKeyPress(event));
    }

    /**
     * Creates and displays the Minesweeper board based on the selected difficulty.
     *
     * @param difficulty The difficulty level for the board (e.g., "Easy", "Medium", "Hard").
     */
    public void createBoard(String difficulty) {
        System.out.println("Creating board with difficulty: " + difficulty);
        this.currentDifficulty = difficulty; // Store the selected difficulty

        // Generate and set up the board
        Board board = new Board();
        board.setDifficulty(difficulty);
        board.generateGrid();

        // Display the board in the root pane
        GridPane gridPane = board.getGridPane();
        root.getChildren().clear();
        root.getChildren().add(gridPane);
    }

    /**
     * Handles the F5 key press to regenerate the board with the current difficulty.
     *
     * @param event The KeyEvent triggered by a key press.
     */
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.D && currentDifficulty != null) {
            System.out.println("Regenerating board with difficulty: " + currentDifficulty);
            createBoard(currentDifficulty);
        }
    }
}
