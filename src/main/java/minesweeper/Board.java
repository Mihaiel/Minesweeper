package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper.UI.SwitchScenes;

public class Board {
    private int rows, cols, bombs;
    private String difficulty;
    private Cell[][] gameBoard;
    private final GridPane gridPane;

    /**
     * new GripPane will be generated and style added from CSS class
     */
    public Board() {
        this.gridPane = new GridPane();
        this.gridPane.getStyleClass().add("grid-pane");
    }

    /**
     * Setter for the difficulty
     **/
    public void setDifficulty(String difficulty) {
        setDifficulty(difficulty, 0, 0);
    }

    /**
     *Setter if also custom design is used
     *sets the difficulty levels, with the option of custom rows and columns
     */
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
                this.cols = 2;
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

    /**
     * Generates the board using GenerateBoard also added click event handling
     */
    public void generateGrid() {
        this.gridPane.getChildren().clear();

        // Generate the board using GenerateBoard
        gameBoard = GenerateBoard.generateBoard(rows, cols, bombs);

        // Hide numbers initially by not setting text in the cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = gameBoard[row][col];
                this.gridPane.add(cell, col, row);

                // Add click event handling
                cell.setOnMouseClicked(event -> {
                    boolean isRightClick = event.getButton() == MouseButton.SECONDARY;
                    handleCellClick(GridPane.getRowIndex(cell), GridPane.getColumnIndex(cell), isRightClick);
                });
            }
        }
    }


    /**
     * Constructor for the GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * Click event handler for every cell
     * Determines what gets executed with left and right click
     * The actual reveal() code lies int the cell code itself
     */
    public void handleCellClick(int row, int col, boolean isRightClick) {
        Cell cell = gameBoard[row][col];

        // Right click
        if (isRightClick) {
            if(cell.isFlagged()) {
                cell.toggleFlag();
            }

            else if(!cell.isFlagged() && !cell.isRevealed())
            {
                cell.toggleFlag();
            }
        }

        // Left Click
        else {
            if (cell.isFlagged()) {
                System.out.println("Cannot reveal a flagged cell.");
                return;
            }

            if (cell.isBomb()) {
                cell.reveal();
                System.out.println("Game Over!");
                // Optionally, reveal all bombs and end the game
            }
            else
            {
                revealCell(row, col); // Reveal this cell and potentially adjacent cells
            }
        }
    }

    /**
     * Reveals the cell at (row, col). If the cell is empty (neighbor bombs == 0),
     * recursively reveals adjacent cells.
     */
    private void revealCell(int row, int col) {
        Cell cell = gameBoard[row][col];

        // Base case: don't reveal already revealed or flagged cells
        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        cell.reveal(); // Reveal the current cell

        if (cell.getNeighborBombs() == 0) {
            // Recursively reveal adjacent cells
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int neighborRow = row + i;
                    int neighborCol = col + j;

                    // Skip the current cell itself and out-of-bounds indices
                    if ((i != 0 || j != 0) && isInBounds(neighborRow, neighborCol)) {
                        revealCell(neighborRow, neighborCol);
                    }
                }
            }
        }
    }

    /**
     * Helper method to check if a row and column are within the game board bounds.
     */
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}