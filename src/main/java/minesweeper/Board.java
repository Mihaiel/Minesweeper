package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Board{
    private short COLS, ROWS, BOMBS;
    private short FLAGS, SCORE = 0;
    private String DIFFICULTY;
    private int[][] gameBoard = new int[ROWS][COLS];
    private final GridPane gridPane;

    public Board()
    {
        this.gridPane = new GridPane();
        this.gridPane.getStyleClass().add("grid-pane"); // Add CSS class for the GridPane
    }

    // Simple version of setting the difficulty (only custom difficulty needs more parameters)
    public void setDifficulty(String difficulty)
    {
        setDifficulty(difficulty, (byte)0, (byte)0);
    }

    // Set the difficulty of the game
    public void setDifficulty(String difficulty, byte custom_rows, byte custom_cols)
    {
        this.DIFFICULTY = difficulty;

        switch (difficulty)
        {
            case "Easy":
                this.ROWS = 8;
                this.COLS = 8;
                this.BOMBS = 10;
                break;

            case "Medium":
                this.ROWS = 16;
                this.COLS = 16;
                this.BOMBS = 40;
                break;

            case "Hard":
                this.ROWS = 16;
                this.COLS = 32;
                this.BOMBS = 99;
                break;

            case "50/50":
                this.ROWS = 1;
                this.COLS = 1;
                this.BOMBS = 1;
                break;

            case "Custom":
                // Error Handling - custom rows and cells must be between 1 and 32
                if(custom_cols < 1 || custom_cols > 32)
                    throw new IllegalArgumentException("Custom cols must be between 1 and 32");
                if (custom_rows < 1 || custom_rows > 32)
                    throw new IllegalArgumentException("Custom rows must be between 1 and 32");

                this.ROWS = custom_rows;
                this.COLS = custom_cols;
                this.BOMBS = (short) (custom_cols/2 * custom_rows/2);
                break;

            default:
                throw new IllegalArgumentException("Difficulty must be either 0, 1, 2 or 3");
        }

        this.gameBoard = new int[ROWS][COLS];
    }

    public void generateGrid()
    {
        this.gridPane.getChildren().clear(); // Clear the grid for re-rendering

        // Generating a mask layer of bombs
        int[][] bombGameBoard = new int[this.ROWS][this.COLS];
        int generated_bombs = 0;

        for(int i = 0; i < this.BOMBS; i++)
        {
            // Generate a random number between 0 and 32 - Math.random generates random numbers between 0.0 and 1.0
            int random_row = (int) (Math.random() * this.ROWS);
            int random_col = (int) (Math.random() * this.COLS);

            while(bombGameBoard[random_row][random_col] == 1)
            {
                random_row = (int) (Math.random() * this.ROWS);
                random_col = (int) (Math.random() * this.COLS);
            }

            if(bombGameBoard[random_row][random_col] == 0)
                generated_bombs++;

            // Set the bomb layer of the gameboard to 1 - meaning the random cells have bombs in them
            bombGameBoard[random_row][random_col] = 1;
        }
        System.out.println("Generated bombs: " + generated_bombs);


        // Generating the actual grid layer of the game
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLS; col++) {
                Button cell = new Button();

                // If the mask layer of bombs has a 0 (no bombs) - safe cell
                if(bombGameBoard[row][col] == 0)
                {
                    cell.getStyleClass().add("cell-button");
                    this.gameBoard[row][col] = 0;
                }

                // If the mask layer of bombs has a 1 (bomb) - bomb cell
                else if(bombGameBoard[row][col] == 1)
                {
                    cell.getStyleClass().add("bomb");
                    this.gameBoard[row][col] = 1;
                }

                int finalRow = row;
                int finalCol = col;

                // Add an event handler for cell interaction
                cell.setOnAction(event -> {
                    System.out.println("Clicked cell at: " + finalRow + " " + finalCol);
                });

                this.gridPane.add(cell, col, row); // Add button to GridPane
            }
        }

    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    // Get the game info
    public void getInfo()
    {
        System.out.println("Difficulty: " + this.DIFFICULTY);
        System.out.println("Total Blocks: " + (this.ROWS * this.COLS));
        System.out.println("Rows: " + this.ROWS);
        System.out.println("Columns: " + this.COLS);
        System.out.println("Bombs: " + this.BOMBS);
    }

}
