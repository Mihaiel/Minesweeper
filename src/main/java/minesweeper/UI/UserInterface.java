package minesweeper.UI;

import minesweeper.Sounds.SoundManager;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * The UserInterface class is responsible for creating and managing
 * the graphical user interface (GUI) components of the Minesweeper game.
 * It defines various game windows (e.g., Main Menu, Game Window, Settings, Credits)
 * and handles their layout, style, and user interactions.
 */

public class UserInterface {

    public VBox createMainMenu(SwitchScenes switchScenes)
    {
        // Main VBox for the layout
        VBox layout = new VBox(20); // Space between sections
        layout.setPrefSize(1280, 720);
        layout.setAlignment(Pos.TOP_CENTER); // Center elements at the top

        // Add header
        VBox header = createHeader("Minesweeper", true);
        layout.getChildren().add(header);

        // Button area (HBox)
        HBox mainButtons = new HBox(35); // Space of 35px between buttons
        mainButtons.setAlignment(Pos.CENTER); // Center buttons horizontally

        Button startButton = new Button("Start");
        Button settingsButton = new Button("Settings");
        Button creditsButton = new Button("Credits");
        Button exitButton = new Button("Exit");

        // Style buttons
        startButton.getStyleClass().add("ui-button");
        settingsButton.getStyleClass().add("ui-button");
        creditsButton.getStyleClass().add("ui-button");
        exitButton.getStyleClass().addAll("ui-button", "red");

        // Add buttons to HBox
        mainButtons.getChildren().addAll(startButton, settingsButton, creditsButton, exitButton);

        // Define actions for buttons
        startButton.setOnAction(e -> switchScenes.openGameWindow());
        settingsButton.setOnAction(e -> switchScenes.openSettingsWindow());
        creditsButton.setOnAction(e -> switchScenes.openCreditsWindow());
        exitButton.setOnAction(e -> System.exit(0));

        // Add button area to the main VBox
        layout.getChildren().add(mainButtons);

        return layout;
    }

    public BorderPane createGameWindow(SwitchScenes switchScenes) {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(1280, 720);

        // Add header
        VBox header = createHeader("Minesweeper", true);
        layout.setTop(header);

        // Create a VBox for content
        VBox centerContent = new VBox(20); // Space of 20px between elements
        centerContent.setAlignment(Pos.TOP_CENTER); // Centers all elements horizontally, but not vertically

        // Here we add an additional HBox to control spacing between the image and the buttons
        HBox spacingBox = new HBox();
        spacingBox.setPrefHeight(0); // Set spacing here (e.g., 100px)

        // First HBox for difficulty buttons
        HBox gameButtonsTop = new HBox(35); // Space between buttons
        gameButtonsTop.setAlignment(Pos.CENTER); // Center buttons horizontally
        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button difficultButton = new Button("Difficult");
        Button halfHalfButton = new Button("50/50 \uD83D\uDCB8");

        easyButton.getStyleClass().add("ui-button");
        mediumButton.getStyleClass().addAll("ui-button", "yellow");
        difficultButton.getStyleClass().addAll("ui-button", "red");
        halfHalfButton.getStyleClass().addAll("ui-button", "green");

        // Add buttons to HBox
        gameButtonsTop.getChildren().addAll(easyButton, mediumButton, difficultButton, halfHalfButton);

        // Second HBox for the "Back" button
        HBox gameButtonsBottom = new HBox(); // No space required
        gameButtonsBottom.setAlignment(Pos.CENTER); // Center button horizontally
        Button backButton = new Button("←Back");
        backButton.getStyleClass().add("ui-button");
        gameButtonsBottom.getChildren().addAll(backButton);

        // Add all HBoxes to the VBox below the header
        centerContent.getChildren().addAll(spacingBox, gameButtonsTop, gameButtonsBottom);

        layout.setCenter(centerContent);

        // Define actions for buttons
        backButton.setOnAction(e -> switchScenes.setupMainMenu());
        easyButton.setOnAction(e -> {
            SoundManager.stopTitleScreenMusic();  // Stoppt die Titelbildschirmmusik
            switchScenes.getMain().createBoard("Easy");
        });

        mediumButton.setOnAction(e -> {
            SoundManager.stopTitleScreenMusic();  // Stoppt die Titelbildschirmmusik
            switchScenes.getMain().createBoard("Medium");
        });

        difficultButton.setOnAction(e -> {
            SoundManager.stopTitleScreenMusic();  // Stoppt die Titelbildschirmmusik
            switchScenes.getMain().createBoard("Difficult");
        });

        halfHalfButton.setOnAction(e -> {
            SoundManager.stopTitleScreenMusic();  // Stoppt die Titelbildschirmmusik
            switchScenes.getMain().createBoard("50/50");
        });

        return layout;
    }

    public VBox createSettingsWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20); // Space between elements

        // Header with title "Settings"
        VBox header = createHeader("Settings", false);
        layout.getChildren().add(header);

        // HBox for music volume slider
        HBox volumeBox = new HBox(10); // Space between slider and percentage label
        volumeBox.setAlignment(Pos.CENTER); // Center HBox

        Label volumeLabel = new Label("Music Volume:");
        volumeLabel.setStyle("-fx-font-size: 40px;");
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickMarks(true); // Show tick marks
        volumeSlider.setBlockIncrement(1);

        // Label for slider percentage value
        Label volumePercent = new Label("50%");
        volumePercent.setStyle("-fx-font-size: 40px; -fx-text-fill: #4F82A7;");

        // Update percentage value when slider is moved
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumePercent.setText(String.format("%.0f%%", newValue.doubleValue()));
        });

        // Add elements to HBox
        volumeBox.getChildren().addAll(volumeLabel, volumeSlider, volumePercent);

        // HBox for master volume slider
        HBox masterVolumeBox = new HBox(10);
        masterVolumeBox.setAlignment(Pos.CENTER);

        Label masterVolumeLabel = new Label("Master Volume:");
        masterVolumeLabel.setStyle("-fx-font-size: 40px;");
        Slider masterVolumeSlider = new Slider(0, 100, 50);
        masterVolumeSlider.setShowTickMarks(true);
        masterVolumeSlider.setBlockIncrement(1);

        // Label for master volume slider percentage value
        Label masterVolumePercent = new Label("50%");
        masterVolumePercent.setStyle("-fx-font-size: 40px; -fx-text-fill: #4F82A7;");

        // Update percentage value when master slider is moved
        masterVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            masterVolumePercent.setText(String.format("%.0f%%", newValue.doubleValue()));
        });

        // Add elements to HBox
        masterVolumeBox.getChildren().addAll(masterVolumeLabel, masterVolumeSlider, masterVolumePercent);

        // HBox for "Fullscreen" checkbox
        HBox fullscreenBox = new HBox(10);
        fullscreenBox.setAlignment(Pos.CENTER); // Center HBox

        // Label for the checkbox
        Label fullscreenLabel = new Label("Fullscreen");
        fullscreenLabel.setStyle("-fx-font-size: 40px;");

        // The actual checkbox
        CheckBox fullscreenCheckBox = new CheckBox();
        fullscreenCheckBox.setStyle("-fx-font-size: 40px;");

        // Add the checkbox and label to the HBox
        fullscreenBox.getChildren().addAll(fullscreenLabel, fullscreenCheckBox);

        HBox spacingBox = new HBox();
        spacingBox.setPrefHeight(300); // Set spacing (e.g., 100px)

        // HBox for "Back" button
        HBox gameButtonsBottom = new HBox();
        gameButtonsBottom.setAlignment(Pos.CENTER);

        Button backButton = new Button("←Back");
        backButton.getStyleClass().add("ui-button");
        gameButtonsBottom.getChildren().addAll(spacingBox, backButton);

        // Actions for the Back button
        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        // Add all elements to layout
        layout.getChildren().addAll(volumeBox, masterVolumeBox, fullscreenBox, gameButtonsBottom);

        return layout;
    }

    public VBox createCreditsWindow(SwitchScenes switchScenes) {
        VBox layout = new VBox(20); // Space between elements in the VBox

        // Add header and text
        VBox header = createHeader("Credits", false);
        layout.getChildren().add(header);

        // HBox for the first header
        HBox header1 = new HBox();
        Label title1 = new Label("Developed by Team Binäre Bären");
        title1.getStyleClass().add("header");
        header1.getChildren().add(title1);
        header1.setPadding(new Insets(0, 0, 0, 40));  // Move the text 100px to the right

        // HBox for the second header
        HBox header2 = new HBox();
        Label title2 = new Label("Mihaiel Birta, Rami Azab, Sarah Hikal, Orsolya Nemere, Zine Ayaz");
        title2.getStyleClass().add("info");
        header2.getChildren().add(title2);
        header2.setPadding(new Insets(0, 0, 0, 40));  // Move the text 100px to the right

        // HBox for the third header (multi-line)
        HBox header3 = new HBox();
        Label title3 = new Label("as our Programming-Teamwork Project 24/25 at the\n" + "University of Applied Sciences FH Campus Wien - CSaDC\n" + "Semester 1");
        title3.getStyleClass().add("normal");
        header3.getChildren().add(title3);
        header3.setPadding(new Insets(0, 0, 0, 40));  // Move the text 100px to the right

        // HBox for the fourth header
        VBox header4 = new VBox();

        // Create a GitHub Hyperlink
        Hyperlink hyperlink_0 = new Hyperlink("GitHub Repository");

        // Set an action to open the link
        hyperlink_0.setOnAction(event -> {
            try {
                URI uri = new URI("https://github.com/Mihaiel/Minesweeper");
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Create a Documentation HyperLink
        Hyperlink hyperlink_1 = new Hyperlink("Documentation");

        // Set an action to open the link
        hyperlink_1.setOnAction(event -> {
            try {
                URI uri = new URI("https://mihaiel.com/");
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hyperlink_0.getStyleClass().add("link");
        hyperlink_1.getStyleClass().add("link");
        header4.getChildren().addAll(hyperlink_0, hyperlink_1);
        header4.setPadding(new Insets(0, 0, 0, 40));  // Move the text 100px to the right

        HBox spacingBox = new HBox();
        spacingBox.setPrefHeight(50);  // Space of 50px between the text block and button

        HBox gameButtonsBottom = new HBox();  // Space can be omitted
        gameButtonsBottom.setAlignment(Pos.CENTER);  // Center button horizontally
        Button backButton = new Button("←Back");
        backButton.getStyleClass().add("ui-button");
        gameButtonsBottom.getChildren().addAll(backButton);

        backButton.setOnAction(e -> switchScenes.setupMainMenu());

        layout.getChildren().addAll(header1, header2, header3, header4, spacingBox, gameButtonsBottom);

        return layout;
    }

    public VBox createHeader(String titleText, boolean showImage) {
        // VBox für den Header
        VBox header = new VBox(5);  // Setze den Abstand hier auf 5 oder 0, um den Abstand zu verkleinern
        header.setAlignment(Pos.CENTER); // Zentriert alles innerhalb des Headers

        // Titel (der Text wird durch den Parameter titleText ersetzt)
        Label title = new Label(titleText);
        title.getStyleClass().add("title");

        // Bild (wird nur angezeigt, wenn showImage true ist)

        ImageView tilePic = null;
        if (showImage)
        {
            tilePic = new ImageView(Objects.requireNonNull(getClass().getResource("/art/interface/title_picture.png")).toExternalForm());
            tilePic.setFitWidth(800);
            tilePic.setPreserveRatio(true);
        }

        // Elemente hinzufügen
        header.getChildren().add(title);
        if (tilePic != null)
        {
            header.getChildren().add(tilePic);
        }

        return header;
    }

    /**
    Method for the end-message, when the player looses or wins. Not implemented
     **/
    public static HBox createGameOverText(String message) {
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.TOP_CENTER);
        Label text = new Label(message);
        text.getStyleClass().add("game-over-text");
        labelBox.getChildren().add(text);
        return labelBox;
    }



}