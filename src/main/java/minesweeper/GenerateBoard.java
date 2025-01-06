package minesweeper;

import java.util.Random;

public class GenerateBoard {

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

    // Go through the whole board and calculate the neighbors numbers
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


    // Calculate each individual cells number
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
