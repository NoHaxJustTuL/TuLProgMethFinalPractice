package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Command;
import logic.GameSystem;

import java.util.Deque;
import java.util.List;

public class CommandPane extends VBox {
    private static CommandPane instance;
    private Text counterText;
    private HBox commandContainer;

    private CommandPane() {
        super();
        this.setPrefHeight(150);
        this.setPrefWidth(900);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        initializeText();
        initializeCommandContainer();
    }

    private void initializeText() {
        this.counterText = new Text("Commands (0/0)");
        this.counterText.setFont(Font.font("Tohama", FontWeight.LIGHT,16));

        this.getChildren().add(this.counterText);
    }

    private void initializeCommandContainer() {
        this.commandContainer = new HBox();
        this.commandContainer.setPrefWidth(880);
        this.commandContainer.setPrefHeight(60);
        this.commandContainer.setBackground(new Background(new BackgroundFill(Color.web("#CDE8E5"), new CornerRadii(10), null)));

        this.getChildren().add(this.commandContainer);
    }

    public void updateCommandPane() {
        this.commandContainer.getChildren().clear();
        Deque<Command> currentCommands = GameSystem.getInstance().getCurrentCommands();

        for (Command command : currentCommands) {
            this.commandContainer.getChildren().add(new ArrowPane(command));
        }

        this.counterText.setText("Commands (" + currentCommands.size() + "/" + GameSystem.getInstance().getCurrentMap().getMaxCommandCount() + ")");
    }

    public static CommandPane getInstance() {
        if (instance == null) {
            instance = new CommandPane();
        }
        return instance;
    }
}
