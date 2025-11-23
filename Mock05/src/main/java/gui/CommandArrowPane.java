package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.Command;
import logic.GameSystem;

public class CommandArrowPane extends StackPane {

    public CommandArrowPane(Command command) {
        ArrowPane imagePane = new ArrowPane(command);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,new CornerRadii(30),null)));
        this.getChildren().addAll(imagePane);

        // TODO: Complete the code here
        this.setOnMouseClicked((MouseEvent event) -> {
            GameSystem.getInstance().addCommand(command);
        });
    }
}
