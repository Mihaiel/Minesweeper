package minesweeper.UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UserInterface {

    public StackPane createMainMenu(SwitchScenes switchScenes) {
        StackPane layout = new StackPane();

        // Title Label
        Label title = new Label("Minesweeper");
        title.setStyle("-fx-font-size: 100px; -fx-text-fill: blue; -fx-font-weight: bold;");
        title.setTranslateX(0);
        title.setTranslateY(-290);

        // Buttons erstellen
        Button startButton = new Button("Start");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");
        Button creditsButton = new Button("Credits");
        Button statisticsButton = new Button("Statistics");

        // Layout für die Buttons
        VBox mainButtons = new VBox(50);
        mainButtons.getChildren().add(startButton);
        mainButtons.setTranslateX(560);
        mainButtons.setTranslateY(450);
        mainButtons.setStyle("-fx-font-size: 48px;");

        // Untere Buttons Layout
        HBox bottomButtons = new HBox(175);
        bottomButtons.getChildren().addAll(settingsButton, creditsButton, statisticsButton, exitButton);
        bottomButtons.setTranslateY(620);
        bottomButtons.setTranslateX(100);
        bottomButtons.setStyle("-fx-font-size: 30px;");

        layout.getChildren().addAll(title, mainButtons, bottomButtons);

        // Button-Aktionen definieren
        startButton.setOnAction(e -> switchScenes.openGameWindow());
        settingsButton.setOnAction(e -> switchScenes.openSettingsWindow());
        creditsButton.setOnAction(e -> switchScenes.openCreditsWindow());
        statisticsButton.setOnAction(e -> switchScenes.openStatisticsWindow());
        exitButton.setOnAction(e -> System.exit(0));

        return layout;
    }

    public VBox createGameWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20);

        // Label für die Schwierigkeit-Auswahl
        Label gameLabel = new Label("Choose Difficulty");
        gameLabel.setStyle("-fx-font-size: 40px;");

        // Buttons für die Schwierigkeits-Auswahl
        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button difficultButton = new Button("Difficult");
        Button halfHalfButton = new Button("50/50");
        Button backButton = new Button("Back to Main");

        // Setzen der Styles für die Buttons
        easyButton.setStyle("-fx-font-size: 30px;");
        mediumButton.setStyle("-fx-font-size: 30px;");
        difficultButton.setStyle("-fx-font-size: 30px;");
        halfHalfButton.setStyle("-fx-font-size: 30px;");
        backButton.setStyle("-fx-font-size: 15px;");

        // Layout für die Buttons
        layout.getChildren().addAll(gameLabel, easyButton, mediumButton, difficultButton, halfHalfButton, backButton);

        // Aktion für den Zurück-Button
        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        // Aktionen für die Schwierigkeits-Buttons
        easyButton.setOnAction(e -> switchScenes.getMain().createBoard("Easy"));
        mediumButton.setOnAction(e -> switchScenes.getMain().createBoard("Medium"));
        difficultButton.setOnAction(e -> switchScenes.getMain().createBoard("Difficult"));
        halfHalfButton.setOnAction(e -> switchScenes.getMain().createBoard("50/50"));

        return layout;
    }

    public VBox createSettingsWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20);

        Label settingsLabel = new Label("Settings");
        settingsLabel.setStyle("-fx-font-size: 50px;");

        Label volumeLabel = new Label("Musik Lautstärke:");
        volumeLabel.setStyle("-fx-font-size: 40px;");
        Slider volumeSlider = new Slider(0, 100, 50);

        Label masterVolumeLabel = new Label("Master Volume:");
        masterVolumeLabel.setStyle("-fx-font-size: 40px;");
        Slider masterVolumeSlider = new Slider(0, 100, 50);

        Button backButton = new Button("Back to Main");
        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setTranslateX(610);  // Positionierung des Buttons
        backButton.setTranslateY(200);  // Button unter dem Slider

        volumeSlider.setShowTickMarks(true);
        masterVolumeSlider.setShowTickMarks(true);

        layout.getChildren().addAll(settingsLabel, volumeLabel, volumeSlider, masterVolumeLabel, masterVolumeSlider, backButton);

        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        return layout;
    }

    public VBox createCreditsWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20);

        Label creditsLabel = new Label("Credits");
        creditsLabel.setStyle("-fx-font-size: 50px;");

        Button backButton = new Button("Back to Main");
        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setTranslateX(610);  // Positionierung des Buttons
        backButton.setTranslateY(200);  // Button unter dem Slider

        layout.getChildren().addAll(creditsLabel, backButton);

        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        return layout;
    }

    public VBox createStatisticsWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20);

        Label statisticsLabel = new Label("Statistics");
        statisticsLabel.setStyle("-fx-font-size: 50px;");

        Button backButton = new Button("Back to Main");
        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setTranslateX(610);  // Positionierung des Buttons
        backButton.setTranslateY(200);  // Button unter dem Slider

        layout.getChildren().addAll(statisticsLabel, backButton);

        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        return layout;
    }
}
