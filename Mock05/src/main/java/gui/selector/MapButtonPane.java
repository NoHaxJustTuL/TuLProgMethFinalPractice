package gui.selector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameState;
import logic.GameSystem;
import logic.Map;

public class MapButtonPane extends VBox {
    private Map map;

    public MapButtonPane(Map map) {
        super();
        try {
            this.map = map;
            this.setPrefWidth(350);
            this.setMaxWidth(350);
            this.setPrefHeight(80);
            this.setPadding(new Insets(10));
            this.setBackground(new Background(new BackgroundFill(Color.web("#CDE8E5"), new CornerRadii(10), null)));
            this.setOnMouseEntered(e -> this.setBackground(new Background(new BackgroundFill(Color.web("#7AB2B2"), new CornerRadii(10), null))));
            this.setOnMouseExited(e -> this.setBackground(new Background(new BackgroundFill(Color.web("#CDE8E5"), new CornerRadii(10), null))));
            this.setSpacing(8);
            this.setAlignment(Pos.CENTER_LEFT);
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (GameSystem.getInstance().getGameState() == GameState.RUNNING){
                        GameSystem.getInstance().stopPlayingThread();
                    }
                    handleOnClick();
                }
            });

            this.initializeDetail();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void initializeDetail() {
        Text titleText = new Text(this.map.getName());
        titleText.setFont(Font.font("Tohama", FontWeight.BOLD,16));

        Text difficultyText = new Text();
        difficultyText.setFont(Font.font("Tohama", FontWeight.LIGHT,14));
        if (this.map.getDifficulty() == 1) difficultyText.setText("Difficulty: Very Easy");
        else if (this.map.getDifficulty() == 2) difficultyText.setText("Difficulty: Easy");
        else if (this.map.getDifficulty() == 3) difficultyText.setText("Difficulty: Normal");
        else if (this.map.getDifficulty() == 4) difficultyText.setText("Difficulty: Hard");
        else if (this.map.getDifficulty() == 5) difficultyText.setText("Difficulty: Very Hard Like Ajarn Nattee's Exam");
        else difficultyText.setText("Difficulty: Ask Someone");

        this.getChildren().addAll(titleText, difficultyText);
    }

    private void handleOnClick() {
        MapSelectorStage.getInstance().close();
        GameSystem.getInstance().initializeMap(this.map);
    }
}
