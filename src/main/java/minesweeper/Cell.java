package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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

    public int getNeighborBombs() {
        return this.neighborBombs;
    }

    public void setNeighborBombs(int neighborBombs) {
        this.neighborBombs = neighborBombs;
    }

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
        }
        else
        {
            this.getStyleClass().add("revealed");
            int neighborBombs = getNeighborBombs();

            if (neighborBombs > 0)
            {
                setText(String.valueOf(neighborBombs));
            }
            else
            {
                setText("");
            }
            System.out.println("Cell reveal() -  " + neighborBombs + " neighbor bombs.");
        }
    }
}
