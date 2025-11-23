package gui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import logic.Command;

public class ArrowPane extends Pane {
    private static final Image UP_IMG = new Image(ClassLoader.getSystemResource("images/up.png").toString());
    private static final Image DOWN_IMG = new Image(ClassLoader.getSystemResource("images/down.png").toString());
    private static final Image LEFT_IMG = new Image(ClassLoader.getSystemResource("images/left.png").toString());
    private static final Image RIGHT_IMG = new Image(ClassLoader.getSystemResource("images/right.png").toString());
    private Command command;

    public ArrowPane(Command command) {
        super();
        this.command = command;
        this.setPrefHeight(60);
        this.setPrefWidth(60);
        setImage();
    }

    private void setImageBackground(Image image){
        this.setBackground(new Background(new BackgroundImage(image, null ,null ,null, new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, false, false))));
    }

    private void setImage() {
        switch (command){
            case LEFT -> setImageBackground(LEFT_IMG);
            case RIGHT -> setImageBackground(RIGHT_IMG);
            case UP -> setImageBackground(UP_IMG);
            case DOWN -> setImageBackground(DOWN_IMG);
        }

    }

    protected Command getCommand(){
        return  this.command;
    }
}
