package minesweeper.UI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.Cell;
import minesweeper.Main;

import java.util.Objects;

/**
 * The SwitchScenes class handles the navigation and transition
 * between different scenes (windows) in the Minesweeper game.
 * It acts as a bridge between the root StackPane, the UserInterface,
 * and the Main class, allowing for smooth switching and game flow control.
 */

public class SwitchScenes
{
    private final StackPane root;
    private final UserInterface ui;
    private final Main main;
    private Cell[][] board;

    //Constructor for initializing SwitchScenes with required dependencies
    public SwitchScenes(StackPane root, UserInterface ui, Main main)
    {
        this.root = root;
        this.ui = ui;
        this.main = main;  // Installing Main-Objekt
    }
    //Returns the Main instance to access game logic
    public Main getMain() {
        return main;
    }

    //Sets the current state of the game board
    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    //Opens the game window by clearing the root layout and adding the game scene created by the UserInterface
    public void openGameWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createGameWindow(this));
    }

    //Opens the Settings window by clearing the root layout and adding the game scene created by the UserInterface
    public void openSettingsWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createSettingsWindow(this));
    }

    //Opens the Credit window by clearing the root layout and adding the game scene created by the UserInterface
    public void openCreditsWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createCreditsWindow(this));
    }

    //Sets up the main menu by clearing the root layout and adding the main menu scene created by the UserInterface
    public void setupMainMenu()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createMainMenu(this));
    }
}
