package logic;

import gui.GamePane;

public class Player {

    private int currentRow;

    private int currentColumn;
    private int spawnRow;
    private int spawnColumn;

    public Player(int spawnRow, int spawnColumn){
        this.currentRow = spawnRow;
        this.currentColumn = spawnColumn;
        this.spawnRow = spawnRow;
        this.spawnColumn = spawnColumn;
    }

    public void executeMove(Command command) throws InterruptedException{

        int[] nextMove = getNextMove(command);
        int nextRow = nextMove[0];
        int nextColumn = nextMove[1];
        while(isValidMove(nextRow,nextColumn)){
            // Move
            updateNextState(nextRow,nextColumn);
            // get NextMove
            Thread.sleep(100);
            nextMove = getNextMove(command);
            nextRow = nextMove[0];
            nextColumn = nextMove[1];
        }

    }

    private void updateNextState(int nextRow,int nextColumn){
        int previousRow = this.currentRow;
        int previousColumn = this.currentColumn;
        setCurrentRow(nextRow);
        setCurrentColumn(nextColumn);
        GamePane.getInstance().getCellPane(previousRow,previousColumn).setImage();
        GamePane.getInstance().getCellPane(currentRow,currentColumn).setImage();
    }

    public void resetLocation(){
        updateNextState(spawnRow,spawnColumn);
    }

    private int[] getNextMove(Command command){
        int[] nextMove = new int[2];

        nextMove[0] = currentRow;
        nextMove[1] = currentColumn;
        switch(command){
            case UP: nextMove[0] -= 1; break;
            case DOWN: nextMove[0] += 1; break;
            case LEFT: nextMove[1] -= 1; break;
            case RIGHT: nextMove[1] += 1; break;
            default: throw new RuntimeException("Invalid command is found expected [UP,DOWN,LEFT,RIGHT] but got " + command + " instead.");
        }
        return nextMove;
    }

    private boolean isOutOfBound(int nextRow,int nextColumn){
        return nextRow < 0 || nextColumn < 0 ||
                nextRow >= GameSystem.getInstance().getCurrentHeight() ||
                nextColumn >= GameSystem.getInstance().getCurrentWidth();
    }

    private boolean isValidMove(int nextRow,int nextColumn){
        if (isOutOfBound(nextRow,nextColumn)) return false;
        Tile nextTile = GameSystem.getInstance().getTileState(nextRow,nextColumn);
        return switch (nextTile) {
            case EMPTY -> false;
            case WALL -> false;
            default -> true;
        };

    }
    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }



    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }
}
