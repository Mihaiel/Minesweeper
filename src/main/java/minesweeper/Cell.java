package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper.Sounds.SoundManager;

import java.util.Objects;

public class Cell extends Button
{
    private boolean isBomb;
    private boolean isFlag;
    private boolean isRevealed;
    private int neighborBombs;

    public Cell(boolean isBomb, boolean isFlag, boolean isRevealed)
    {
        this.isFlag = isFlag;
        this.isRevealed = isRevealed;
        this.isBomb = isBomb;

        this.getStyleClass().add("cell-button");

        if(isBomb)
            this.getStyleClass().add("bomb");
    }


    // Getters
    public boolean isBomb()
    {
        return this.isBomb;
    }

    public boolean isFlagged()
    {
        return this.isFlag;
    }

    public boolean isRevealed()
    {
        return this.isRevealed;
    }

    // Setter
    public int getNeighborBombs() {
        return this.neighborBombs;
    }

    public void setNeighborBombs(int neighborBombs) {
        this.neighborBombs = neighborBombs;
    }

    // Toggle Flag
    public void toggleFlag(){
        this.isFlag = !this.isFlag;

        if(this.isFlag)
        {
            ImageView icon = new ImageView(Objects.requireNonNull(getClass().getResource("/art/flag.png")).toExternalForm());
            this.setGraphic(icon);
            System.out.println("Cell toggleFlag() - Flag placed.");
        }
        else
        {
            this.setGraphic(null);
            System.out.println("Cell toggleFlag() - Flag removed.");
        }
    }

    // Reveal Function
    public void reveal()
    {
        this.isRevealed = true;

        if(isBomb)
        {
            ImageView Bomb = new ImageView(Objects.requireNonNull(getClass().getResource("/art/MineV2.png")).toExternalForm());
            Bomb.setFitWidth(30);
            Bomb.setFitHeight(30);
            Bomb.setPreserveRatio(true);
            this.setGraphic(Bomb);
            System.out.println("Cell reveal() - Bomb revealed.");
            SoundManager.playMineOpen();
        }
        else
        {
            int neighborBombs = getNeighborBombs();
            setStyle(neighborBombs);

            if (neighborBombs > 0)
            {
                setText(String.valueOf(neighborBombs));
                SoundManager.playCellOpen();
            }
            else
            {
                setText("");
                SoundManager.playWideOpen();
            }
            System.out.println("Cell reveal() -  " + neighborBombs + " neighbor bombs.");
        }
    }

    public void setStyle(int number)
    {
        this.getStyleClass().add("revealed");

        switch(number) {
            case 0:
                break;

            case 1:
                this.getStyleClass().add("revealed-1");
                break;

            case 2:
                this.getStyleClass().add("revealed-2");
                break;

            case 3:
                this.getStyleClass().add("revealed-3");
                break;

            case 4:
                this.getStyleClass().add("revealed-4");
                break;

            case 5,6:
                this.getStyleClass().add("revealed-5-6");
                break;

            case 7,8:
                this.getStyleClass().add("revealed-7-8");
                break;

            default:
                throw new IllegalArgumentException("Class Cell - Invalid number style. ");
        }
    }
}
