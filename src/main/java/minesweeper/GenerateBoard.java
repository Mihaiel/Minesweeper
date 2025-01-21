package minesweeper;

import java.util.Random;

public class GenerateBoard {

    /**
     * Generates a Minesweeper board with specified dimensions and bomb count.
     *
     * @param rows      Number of rows in the board.
     * @param cols      Number of columns in the board.
     * @param bombCount Number of bombs to place on the board.
     * @return A 2D array representing the generated Minesweeper board.
     * @throws IllegalArgumentException If the bomb count exceeds the number of cells.
     */
    public static Cell [][] generateBoard(int rows, int cols, int bombCount) {

        // Total cell count
        int totalCells = rows * cols;

        // Error Exception - Too many bombs than cells
        if (bombCount > totalCells) {
            throw new IllegalArgumentException("Too many bombs! There are only " + totalCells + " Cells.");
        }

        // Create a board
        Cell[][] board = new Cell [rows][cols];

        // Initialize the board as empty cells
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                board [y][x] = new Cell(false, false,false);
            }
        }

        // Randomly place bombs and calculate neighbor numbers
        placeBombs(rows, cols, board, bombCount);
        calculateNeighbors(board, rows, cols);

        return board;

    }

    /**
     * Places bombs randomly on the Minesweeper board.
     *
     * @param rows      Number of rows in the board.
     * @param cols      Number of columns in the board.
     * @param board     The Minesweeper board.
     * @param bombCount Number of bombs to place.
     */
    private static void placeBombs(int rows, int cols, Cell[][] board, int bombCount) {
        // Starting Argument
        Random rand = new Random();
        int placedBombs = 0;

        // Go through the amount of bombs needed to be placed and randomly place them
        while (placedBombs < bombCount) {
            int x = rand.nextInt(cols);
            int y = rand.nextInt(rows);

            // If the chosen cell is not a bomb, place a bomb else generate again
            if (!board[y][x].isBomb()) {
                board[y][x] = new Cell (true, false, false);
                placedBombs++;
            }
        }
    }

    /**
     * Calculates the number of bombs surrounding each cell in the Minesweeper board.
     *
     * @param board The Minesweeper board.
     * @param rows  Number of rows in the board.
     * @param cols  Number of columns in the board.
     */
    private static void calculateNeighbors(Cell[][] board, int rows, int cols) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!board[y][x].isBomb()) {
                    int neighborBombs = countNeighborBombs(board, x, y, rows, cols);
                    board[y][x].setNeighborBombs(neighborBombs); // Store the count in a field
                }
            }
        }
    }


    /**
     * Counts the number of bombs surrounding a specific cell.
     *
     * @param board The Minesweeper board.
     * @param x     The x-coordinate of the cell.
     * @param y     The y-coordinate of the cell.
     * @param rows  Number of rows in the board.
     * @param cols  Number of columns in the board.
     * @return The number of bombs surrounding the specified cell.
     */
    private static int countNeighborBombs(Cell[][] board, int x, int y, int rows, int cols) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;

                if (neighborX >= 0 && neighborX < cols && neighborY >= 0 && neighborY < rows) {
                    if (board [neighborY][neighborX].isBomb()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
