package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Board {
    private int rows, cols, bombs;
    private int flags = 0;
    private String difficulty;
    private Cell[][] gameBoard;
    private final GridPane gridPane;

    public Board() {
        this.gridPane = new GridPane();
        this.gridPane.getStyleClass().add("grid-pane"); // Add CSS class for the GridPane
    }

    public void setDifficulty(String difficulty) {
        setDifficulty(difficulty, 0, 0);
    }

    public void setDifficulty(String difficulty, int customRows, int customCols) {
        this.difficulty = difficulty;

        switch (difficulty) {
            case "Easy":
                this.rows = 8;
                this.cols = 8;
                this.bombs = 10;
                break;

            case "Medium":
                this.rows = 16;
                this.cols = 16;
                this.bombs = 40;
                break;

            case "Difficult":
                this.rows = 16;
                this.cols = 32;
                this.bombs = 99;
                break;

            case "50/50":
                this.rows = 1;
                this.cols = 1;
                this.bombs = 1;
                break;

            case "Custom":
                if (customCols < 1 || customCols > 32 || customRows < 1 || customRows > 32) {
                    throw new IllegalArgumentException("Custom rows and columns must be between 1 and 32");
                }
                this.rows = customRows;
                this.cols = customCols;
                this.bombs = (customCols / 2) * (customRows / 2);
                break;

            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }
    }

    public void generateGrid() {
        this.gridPane.getChildren().clear();

        // Generate the board using GenerateBoard
        gameBoard = GenerateBoard.generateBoard(rows, cols, bombs);

        // Hide numbers initially by not setting text in the cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = gameBoard[row][col];
                this.gridPane.add(cell, col, row);
            }
        }
    }


    public GridPane getGridPane() {
        return this.gridPane;
    }

    public void getInfo() {
        System.out.println("Difficulty: " + this.difficulty);
        System.out.println("Rows: " + this.rows);
        System.out.println("Columns: " + this.cols);
        System.out.println("Bombs: " + this.bombs);
    }
}