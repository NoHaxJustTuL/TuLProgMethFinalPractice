package gui;

import gui.selector.MapSelectorPane;
import gui.selector.MapSelectorStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameState;
import logic.GameSystem;

public class ControlPane extends VBox {
    private static ControlPane instance;
    private Button selectMapButton;

    private CommandControllerPane commandArrowPane;
    private Button playButton;
    private Button resetButton;
    private Text notificationText;

    private ControlPane() {
        super();
        this.setPrefWidth(300);
        this.setPrefHeight(600);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(10, 0, 10, 0));

        initializeSelectMapButton();
        initializeCommandArrowPane();
        initializePlayButton();
        initializeNotificationText();
        initializeResetButton();

        this.getChildren().addAll(notificationText,selectMapButton, commandArrowPane,playButton,resetButton);
    }

    private void initializeNotificationText(){
        Text text = new Text("");
        text.setFont(Font.font("Tohama",FontWeight.BOLD,16));;
        text.setVisible(false);
        this.notificationText = text;
    }

    public void showNotification(String text,Color colorText){
        notificationText.setText(text);
        notificationText.setFill(colorText);
        notificationText.setVisible(true);
    }

    public void hideNotification(){
        notificationText.setVisible(false);
    }

    private void initializePlayButton(){
        Button btn = new Button("Play");
        btn.setFont(Font.font("Tohama", FontWeight.BOLD,16));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(150);
        btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null)));
        btn.setOnMouseEntered(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
        btn.setOnMouseExited(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null))));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameSystem.getInstance().playGame();
            }
        });
        this.playButton = btn;
    }

    private void initializeSelectMapButton() {
        Button btn = new Button("Select Map");
        btn.setFont(Font.font("Tohama", FontWeight.BOLD,16));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(150);
        btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null)));
        btn.setOnMouseEntered(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
        btn.setOnMouseExited(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null))));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MapSelectorStage.getInstance().show();
            }
        });
        this.selectMapButton = btn;
    }

    private void initializeResetButton(){
        Button btn = new Button("Reset Command");
        btn.setFont(Font.font("Tohama", FontWeight.BOLD,16));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(150);
        btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null)));
        btn.setOnMouseEntered(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
        btn.setOnMouseExited(e -> btn.setBackground(new Background(new BackgroundFill(Color.web("#4D869C"), new CornerRadii(10), null))));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameSystem.getInstance().clearCurrentCommands();
            }
        });
        this.resetButton = btn;
    }

    private void initializeCommandArrowPane(){
        this.commandArrowPane = new CommandControllerPane();
    }

    public static ControlPane getInstance() {
        if (instance == null){
            instance = new ControlPane();
        }
        return instance;
    }
}
