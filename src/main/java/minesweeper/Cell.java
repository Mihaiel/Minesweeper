package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.Objects;

public class Cell extends Button
{
    private boolean isBomb;
    private boolean isFlag;
    private boolean isRevealed;

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

    public void setFlagged(boolean flagged)
    {
        this.isFlag = flagged;

        if(flagged)
        {
            ImageView icon = new ImageView(Objects.requireNonNull(getClass().getResource("/art/flag.png")).toExternalForm());
            this.setGraphic(icon);
        }

        else
        {
            this.setGraphic(null);
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
        }
        else
        {
            ImageView Grass = new ImageView(Objects.requireNonNull(getClass().getResource("/art/Grass.png")).toExternalForm());
            Grass.setFitWidth(30);
            Grass.setFitHeight(30);
            Grass.setPreserveRatio(true);
            this.setGraphic(Grass);
        }
    }
}
