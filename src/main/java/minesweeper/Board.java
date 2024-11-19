package minesweeper;

public class Board{
    private short COLS, ROWS, BOMBS, FLAGS, SCORE;
    private String DIFFICULTY;
    private int[][] gameBoard = new int[ROWS][COLS];

    public Board()
    {

    }

    public void setDifficulty(String difficulty)
    {
        setDifficulty(difficulty, (byte)0, (byte)0);
    }

    public void setDifficulty(String difficulty, byte custom_rows, byte custom_cols)
    {
        this.DIFFICULTY = difficulty;

        switch (difficulty)
        {
            case "Easy":
            {
                this.ROWS = 8;
                this.COLS = 8;
                this.BOMBS = 10;
                break;
            }
            case "Medium":
            {
                this.ROWS = 16;
                this.COLS = 16;
                this.BOMBS = 40;
                break;
            }
            case "Hard":
            {
                this.ROWS = 32;
                this.COLS = 16;
                this.BOMBS = 99;
                break;
            }
            case "50/50":
            {
                this.ROWS = 1;
                this.COLS = 1;
                this.BOMBS = 1;
                break;
            }
            case "Custom":
            {
                // Error Handling - custom rows and cells must be between 1 and 32
                if(custom_cols < 1 || custom_cols > 32)
                    throw new IllegalArgumentException("Custom cols must be between 1 and 32");
                if (custom_rows < 1 || custom_rows > 32)
                    throw new IllegalArgumentException("Custom rows must be between 1 and 32");

                this.ROWS = custom_rows;
                this.COLS = custom_cols;
                this.BOMBS = (short) (custom_cols/2 * custom_rows/2);
                break;
            }
            default:
                throw new IllegalArgumentException("Difficulty must be either 0, 1, 2 or 3");
        }

        this.gameBoard = new int[ROWS][COLS];
        reset();
    }

    public void reset()
    {
        this.FLAGS = 0;
        this.SCORE = 0;
    }

    public void getInfo()
    {
        System.out.println("Difficulty: " + this.DIFFICULTY);
        System.out.println("Rows: " + this.ROWS);
        System.out.println("Columns: " + this.COLS);
        System.out.println("Bombs: " + this.BOMBS);
    }

}
