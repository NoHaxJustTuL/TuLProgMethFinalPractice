package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class ControlPane extends VBox {
    private Text gameText;
    private Button newGameButton;
    private Button  secureModeButton;
    private MineSweeperPane mineSweeperPane;

    public ControlPane(MineSweeperPane mineSweeperPane){
        this.mineSweeperPane = mineSweeperPane;
        setAlignment(Pos.CENTER);
        setPrefWidth(300);
        setSpacing(20);
        initializeGameText();
        initializeSecureModeButton();
        initializeNewGameButton();
        this.getChildren().addAll(gameText, newGameButton, secureModeButton);
    }

    private void initializeGameText(){
        gameText = new Text();
        gameText.setText("Tiles left : " + GameLogic.getInstance().getTileCount());
        gameText.setFont(new Font(35));
    }

    public void updateGameText(String text){
        gameText.setText(text);
    }

    private void initializeNewGameButton(){
        newGameButton = new Button();
        newGameButton.setText("New Game");
        newGameButton.setPrefWidth(100);
        newGameButton.setOnAction(event -> newGameButtonHandler());
    }

    private void initializeSecureModeButton(){
        secureModeButton = new Button();
        secureModeButton.setText("Secure mode : OFF");
        secureModeButton.setPrefWidth(150);
        secureModeButton.setOnAction(event -> secureModeButtonHandler());
    }

    private void newGameButtonHandler(){
        GameLogic.getInstance().newGame();
        secureModeButton.setText("Secure mode : OFF");
        updateGameText("Tiles left : " + GameLogic.getInstance().getTileCount());
        for (MineSweeperSquare squre : mineSweeperPane.getAllCells()) squre.initializeCellColor();

    }

    private void secureModeButtonHandler(){
        GameLogic.getInstance().toggleSecureMode();
        boolean isSecureMode = GameLogic.getInstance().isSecureMode();
        if (isSecureMode){
            secureModeButton.setText("Secure mode : ON");
        }else{
            secureModeButton.setText("Secure mode : OFF");
        }
    }
}
