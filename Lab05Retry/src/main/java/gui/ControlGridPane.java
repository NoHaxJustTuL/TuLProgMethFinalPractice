package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControlGridPane extends VBox {
    private final String miningImageURL;
    private ControlPane controlPane;

    public ControlGridPane (ControlPane controlPane){
        this.controlPane = controlPane;
        miningImageURL = ClassLoader.getSystemResource("bitcoin.png").toString();

        ImageView imageView = new ImageView(new Image(miningImageURL));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(150);
        borderPane.setPrefHeight(200);
        borderPane.setCenter(imageView);

        this.getChildren().addAll(borderPane, this.controlPane);
    }

}
