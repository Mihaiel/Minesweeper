package minesweeper.UI;

import javafx.scene.layout.StackPane;
import minesweeper.Main;

public class SwitchScenes
{
    private final StackPane root;
    private final UserInterface ui;
    private final Main main;

    public SwitchScenes(StackPane root, UserInterface ui, Main main)
    {
        this.root = root;
        this.ui = ui;
        this.main = main;  // Main-Objekt initialisieren
    }

    public Main getMain() {
        return main;
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

    public void openStatisticsWindow()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createStatisticsWindow(this));
    }

    public void setupMainMenu()
    {
        root.getChildren().clear();
        root.getChildren().addAll(ui.createMainMenu(this));
    }
}
