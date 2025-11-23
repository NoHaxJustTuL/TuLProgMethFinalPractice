package gui;

import javafx.scene.layout.GridPane;
import logic.GameSystem;
import logic.Map;

import java.util.ArrayList;
import java.util.List;

public class GamePane extends GridPane {
    private static final int GAME_SIZE = 620;
    private static GamePane instance;
    private List<List<CellPane>> gridCellPane;
    private double tileSize;

    private GamePane() {
        super();
        this.setPrefWidth(GAME_SIZE);
        this.setPrefHeight(GAME_SIZE);
    }

    public static GamePane getInstance() {
        if (instance == null) {
            instance = new GamePane();
        }
        return instance;
    }

    public void initTiles() {
        this.getChildren().clear();
        Map map = GameSystem.getInstance().getCurrentMap();
        this.tileSize = GAME_SIZE / (Math.max(map.getWidth(), map.getHeight()) * 1.0);
        this.gridCellPane = new ArrayList<List<CellPane>>();

        // TODO: Complete the remaining code for this method
        for (int i = 0; i < map.getHeight(); i++) {
            gridCellPane.add(new ArrayList<>());
            for (int j = 0; j < map.getWidth(); j++){
                CellPane cell = new CellPane(tileSize, tileSize, i, j);
                gridCellPane.get(i).add(cell);
                this.add(cell, j, i);
            }
        }


    }

    public CellPane getCellPane(int row,int col){
        return this.gridCellPane.get(row).get(col);
    }


}
