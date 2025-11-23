package logic;

import java.util.List;

public class Map {
    private String name;
    private int width;
    private int height;
    private int maxCommandCount;
    private List<List<Integer>> tiles;
    private int spawnRow;
    private int spawnCol;
    private int difficulty; // 1 to 5

    public Map(String name, int width, int height, int maxCommandCount, List<List<Integer>> tiles, int spawnRow, int spawnCol, int difficulty) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.maxCommandCount = maxCommandCount;
        this.tiles = tiles;
        this.spawnRow = spawnRow;
        this.spawnCol = spawnCol;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxCommandCount() {
        return maxCommandCount;
    }

    public void setMaxCommandCount(int maxCommandCount) {
        this.maxCommandCount = maxCommandCount;
    }

    public List<List<Integer>> getTiles() {
        return tiles;
    }

    public void setTiles(List<List<Integer>> tiles) {
        this.tiles = tiles;
    }

    public int getSpawnRow() {
        return spawnRow;
    }

    public void setSpawnRow(int spawnRow) {
        this.spawnRow = spawnRow;
    }

    public int getSpawnCol() {
        return spawnCol;
    }

    public void setSpawnCol(int spawnCol) {
        this.spawnCol = spawnCol;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
