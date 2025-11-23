package application;

import gui.CommandPane;
import gui.ControlPane;
import gui.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)  {
        VBox root = new VBox();
        HBox upperBox = new HBox();
        CommandPane commandPane = CommandPane.getInstance();
        root.getChildren().addAll(upperBox, commandPane);

        root.setPrefHeight(750);
        root.setPrefWidth(900);

        root.setBackground(new Background(new BackgroundFill(Color.web("#EEF7FF"), null, null)));

        GamePane gamePane = GamePane.getInstance();
        ControlPane controlPane = ControlPane.getInstance();
        upperBox.getChildren().addAll(gamePane,controlPane);
        
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Nokotan Pathfinder");
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("images/deer.png").toString()));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}