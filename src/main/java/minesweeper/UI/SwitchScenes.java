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

public class SwitchScenes
{
    private final StackPane root;
    private final UserInterface ui;
    private final Main main;
    private Cell[][] board;

    public SwitchScenes(StackPane root, UserInterface ui, Main main)
    {
        this.root = root;
        this.ui = ui;
        this.main = main;  // Installing Main-Objekt
    }

    public Main getMain() {
        return main;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void openGameWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createGameWindow(this));
    }

    public void openSettingsWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createSettingsWindow(this));
    }

    public void openCreditsWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createCreditsWindow(this));
    }

    public void setupMainMenu()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createMainMenu(this));
    }

    public void checkGameOutcome(Cell cell) {
        if (!cell.isRevealed() && !cell.isFlagged()) {
            cell.reveal();
            if (cell.isBomb()) {
                showLosePopUp();
            } else if (Cell.checkWin(board)) {
                showWinPopUp();
            }
        }
    }

    private void showWinPopUp() {

        Stage winStage = new Stage();

        VBox winLayout = new VBox(10);
        winLayout.setPrefSize(500,300); // fix size
        winLayout.setStyle("-fx-background-color: #add8e6; " +
                "-fx-padding: 20; -fx-alignment: center;");

        Label winText = new Label("Congratulations, you won!");
        winText.setStyle("-fx-font-size: 24px; -fx-font-family: 'Lucida Console';" +
                "-fx-text-fill: blue; -fx-font-wight: bold;");

        ImageView winImage = new ImageView(Objects.requireNonNull(getClass().getResource(
                "src/main/resources/art/bearhappy.png")).toExternalForm());
        winImage.setFitWidth(100);
        winImage.setFitHeight(100);
        winImage.setPreserveRatio(true);

        winLayout.getChildren().addAll(winText, winImage);

        Scene winScene = new Scene(winLayout);
        winStage.setScene(winScene);
        winStage.setTitle("You won!"); //title of the popup
        winStage.show();
    }
    private void showLosePopUp() {

        Stage loseStage = new Stage();
        VBox loseLayout = new VBox(10);
        loseLayout.setPrefSize(500,300);
        loseLayout.setStyle("-fx-background-color: #add8e6; " +
                "-fx-padding: 20; -fx-alignment: center;");

        Label loseText = new Label("Sorry, you just got mineswept!");
        loseText.setStyle("-fx-font-size: 24px; -fx-font-family: 'Lucida Console';" +
                "-fx-text-fill: blue; -fx-font-wight: bold;");

        ImageView loseImage = new ImageView(Objects.requireNonNull(getClass().getResource(
                "src/main/resources/art/BearSad.png")).toExternalForm());
        loseImage.setFitWidth(100);
        loseImage.setFitHeight(100);
        loseImage.setPreserveRatio(true);

        loseLayout.getChildren().addAll(loseText, loseImage);

        Scene loseScene = new Scene(loseLayout);
        loseStage.setScene(loseScene);
        loseStage.setTitle("You lost!");
        loseStage.show();
    }
}
