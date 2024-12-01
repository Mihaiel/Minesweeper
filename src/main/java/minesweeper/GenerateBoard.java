package minesweeper;

import java.util.Random;

public class GenerateBoard {

    public static Cell [][] generateBoard(int rows, int cols, int bombCount ) {


        int totalCells = rows * cols;

        if (bombCount > totalCells) {
            throw new IllegalArgumentException("Too many bombs! There are only " + totalCells + " Cells.");
        }


        Cell[][] board = new Cell [rows][cols];


        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                board [x][y] = new Cell(false, false,false);
            }
        }

        placeBombs(rows, cols, board, bombCount);

        calculateNeighbors(board, rows, cols);

        return board;

    }
    
    private static void placeBombs(int rows, int cols, Cell[][] board, int bombCount) {
        
        Random rand = new Random();
        int placedBombs = 0;

        while (placedBombs < bombCount) {

            int x = rand.nextInt(cols);
            int y = rand.nextInt(rows);

            if (!board[y][x].isBomb()) {
                board[y][x] = new Cell (true, false, false);
                placedBombs++;
            }

        }
        
    }
    
    private static void calculateNeighbors(Cell[][] board, int rows, int cols) {

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                if (!board[x][y].isBomb()){
                    int neighborBombs = countNeighborBombs(board, x, y, rows, cols);
                    board[x][y].setText(String.valueOf(neighborBombs));
                }
            }
        }
    }

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
