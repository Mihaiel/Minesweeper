package minesweeper;

import javafx.scene.image.ImageView;

import java.awt.*;

public class Cell extends Button
{

    private boolean isBomb;
    private boolean isFlag;
    private boolean isRevealed;

    public Cell(boolean isBomb, boolean isFlag, boolean isRevealed)
    {
        this.isFlag = isFlag;
        this.isRevealed = isRevealed;
        this.isBomb=isBomb;
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

    public void setFlagged(boolean flagged, Button cell)
    {
        this.isFlag = flagged;

        if(flagged == true)
        {
            ImageView icon = new ImageView(getClass().getResource("/art/flag.png").toExternalForm());
            this.setGraphic(icon);
        }

        if(flagged == false)
        {
            cell.setGraphic(null);
        }
    }
}
