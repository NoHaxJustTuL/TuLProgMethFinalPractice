package logic;

import gui.CommandPane;
import gui.ControlPane;
import gui.GamePane;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    private static GameSystem instance;
    private Map currentMap;
    private GameState gameState = GameState.STOPPING;
    private List<List<Integer>> currentTiles;
    private Thread playingThread;


    private Deque<Command> currentCommands;
    private Player player;

    private GameSystem() {
        this.currentCommands = new ArrayDeque<>();

    }

    public void initializeMap(Map map) {
        this.currentMap = map;
        this.currentTiles = map.getTiles();
        this.player = new Player(map.getSpawnRow(), map.getSpawnCol());
        this.gameState = GameState.PLAYING;

        GamePane.getInstance().initTiles();
        ControlPane.getInstance().hideNotification();
        clearCurrentCommands();
    }

    public static GameSystem getInstance() {
        if (instance == null) {
            instance = new GameSystem();
        }
        return instance;
    }

    public Map getCurrentMap() {
        return this.currentMap;
    }

    public GameState getGameState() {
        return this.gameState;
    }


    public int getCurrentWidth(){
        return currentMap.getWidth();
    }

    public int getCurrentHeight(){
        return currentMap.getHeight();
    }

    public void playGame(){
        if (gameState != GameState.PLAYING) return;
        ControlPane.getInstance().hideNotification();
        playingThread = new Thread(() -> {
                gameState = GameState.RUNNING;
                Deque<Command> savedCommand = new ArrayDeque<>();
                try {
                    while(!currentCommands.isEmpty()){
                        Command currentCommand = currentCommands.getFirst();
                        player.executeMove(currentCommand);
                        savedCommand.add(currentCommand);
                        currentCommands.removeFirst();
                        Platform.runLater(() -> CommandPane.getInstance().updateCommandPane());
                    }
                    while(!savedCommand.isEmpty()){
                        currentCommands.add(savedCommand.pop());
                    }
                    Platform.runLater(() -> CommandPane.getInstance().updateCommandPane());
                    if (isWin()){
                        gameState = GameState.STOPPING;
                        ControlPane.getInstance().showNotification("You Win!", Color.GREEN);
                    } else{
                        gameState = GameState.PLAYING;
                        ControlPane.getInstance().showNotification("Git Gud", Color.RED);
                        resetPlayer();
                    }
                } catch (InterruptedException e){
                    gameState = GameState.PLAYING;
                    System.out.println("Interupted");
                } finally {
                    playingThread = null;
                }
            });
        playingThread.start();

    }

    public void stopPlayingThread(){
        playingThread.interrupt();
    }

    private void resetPlayer(){
        player.resetLocation();
    }

    public boolean isWin(){
        return getTile(player.getCurrentRow(), player.getCurrentColumn()) == Tile.SHIKASENBEI;
    }

    public Tile getTileState(int row, int col) {
        if ((row == player.getCurrentRow()) && (col == player.getCurrentColumn())) {
            return Tile.DEER;
        }
        return getTile(row,col);

    }

    public Tile getTile(int row,int col){
        int objectID = this.currentTiles.get(row).get(col);
        switch (objectID) {
            case -1:
                return Tile.EMPTY;
            case 0:
                return Tile.GROUND;
            case 1:
                return Tile.WALL;
            case 9:
                return Tile.SHIKASENBEI;
            default:
                throw new RuntimeException("Invalid objectID Expected [-1,0,1,9] but got " + objectID + "instead.");
        }
    }
    public Deque<Command> getCurrentCommands() {
        return this.currentCommands;
    }

    public void addCommand(Command command) {
        if (GameState.PLAYING != gameState) return;
        if (this.currentCommands.size() >= this.currentMap.getMaxCommandCount()) {
            ControlPane.getInstance().showNotification("Maximum commands reached.",Color.RED);
            return;
        }
        this.currentCommands.add(command);
        ControlPane.getInstance().hideNotification();
        CommandPane.getInstance().updateCommandPane();
    }

    public void clearCurrentCommands() {
        if (GameState.PLAYING != gameState) return;
        this.currentCommands.clear();
        ControlPane.getInstance().hideNotification();
        CommandPane.getInstance().updateCommandPane();
    }



}
