package gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.GameSystem;
import logic.Tile;

public class CellPane extends Pane {
    private static final Image WALL_IMG = new Image(ClassLoader.getSystemResource("images/wall.png").toString());
    private static final Image DEER_IMG = new Image(ClassLoader.getSystemResource("images/deer.png").toString());
    private static final Image SENBEI_IMG = new Image(ClassLoader.getSystemResource("images/shika_senbei.png").toString());
    private int row;
    private int col;

    public CellPane(double width, double height, int row, int col) {
        super();
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.row = row;
        this.col = col;
        setImage();
    }

    public void setImage() {
        Tile tileState = this.getState();

        if (tileState == Tile.EMPTY) return;

        BackgroundFill backgroundFill;
        if ((this.row + this.col) % 2 == 0) {
            backgroundFill = new BackgroundFill(Color.web("#B5C956"), null, null);
        }
        else {
            backgroundFill = new BackgroundFill(Color.web("#9FB04A"), null, null);
        }

        if (tileState == Tile.GROUND) { // Ground
            this.setBackground(new Background(backgroundFill));
        }
        else if (tileState == Tile.WALL) {   // Wall
            this.setBackground(new Background(new BackgroundImage(WALL_IMG, null ,null ,null, new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, false, false))));
        }
        else if (tileState == Tile.DEER) {      // Deer
            this.setBackground(new Background(new BackgroundFill[]{backgroundFill}, new BackgroundImage[]{new BackgroundImage(DEER_IMG, null ,null ,null, new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, false, false))}));
        }
        else if (tileState == Tile.SHIKASENBEI) {      // Shika Senbei
            this.setBackground(new Background(new BackgroundFill[]{backgroundFill}, new BackgroundImage[]{new BackgroundImage(SENBEI_IMG, null ,null ,null, new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, false, false))}));
        }
    }

    public Tile getState() {
        return GameSystem.getInstance().getTileState(this.row, this.col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
