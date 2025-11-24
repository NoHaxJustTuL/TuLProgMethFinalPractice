package gui;

import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.SquareMark;
import logic.SquareState;
import javafx.geometry.Insets;


public class MineSweeperSquare extends Pane{
	private boolean isDrawn;
    private Color baseColor;
    private int xPosition;
    private int yPosition;
    private final String oURL;
    private final String oneURL;
    private final String mineURL;
    private final String flagURL;

	private void draw(Image image, Color backgroundColor) {
        BackgroundFill bgFill = new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY);
        BackgroundFill[] bgFillA = {bgFill};
        BackgroundSize bgSize = new BackgroundSize(100, 100, false, false, false, false);
        BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
        BackgroundImage[] bgImgA = {bgImg};
        this.setBackground(new Background(bgFillA, bgImgA));
        setDrawn(true);
    }

    public MineSweeperSquare(int x, int y){
        oURL = ClassLoader.getSystemResource("o.png").toString();
        oneURL = ClassLoader.getSystemResource("one.png").toString();
        mineURL = ClassLoader.getSystemResource("mine.png").toString();
        flagURL = ClassLoader.getSystemResource("flag.png").toString();
        setxPosition(x);
        setyPosition(y);
        setPrefSize(100, 100);
        setMinSize(100, 100);
        setBaseColor(Color.MOCCASIN);
        initializeCellColor();
        this.setOnMouseClicked(event -> OnClickHandler());
    }

    private void OnClickHandler(){
        if (GameLogic.getInstance().isGameEnd()) return;

        boolean isSecure = GameLogic.getInstance().isSecureMode();
        SquareState state = GameLogic.getInstance().getBoardState()[xPosition][yPosition];

        if (!isSecure && state != SquareState.REVEALED){
            SquareMark mark = GameLogic.getInstance().getBoardMark()[xPosition][yPosition];
            String imagePath = null;
            Color colorToFill = null;
            switch (mark) {
                case ONE:
                    imagePath = oneURL;
                    colorToFill = Color.ORANGE;
                    break;
                case NOTHING:
                    imagePath = oURL;
                    colorToFill = Color.YELLOW;
                    break;
                case MINE:
                    imagePath = mineURL;
                    colorToFill = Color.RED;
                    break;
                default:
                    break;
            }
            draw(new Image(imagePath), colorToFill);
            GameLogic.getInstance().updateState(xPosition, yPosition, SquareState.REVEALED);
        }else if (isSecure && !isDrawn()){
            String imagePath = flagURL;
            draw(new Image(imagePath), Color.GREEN);
            GameLogic.getInstance().updateState(xPosition, yPosition, SquareState.SECURED);
        }
    }


    public void initializeCellColor(){
        Background background = new Background(new BackgroundFill(getBaseColor(), CornerRadii.EMPTY, Insets.EMPTY));
        setBackground(background);
        setDrawn(false);
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public String getOneURL() {
        return oneURL;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getoURL() {
        return oURL;
    }

    public String getMineURL() {
        return mineURL;
    }

    public String getFlagURL() {
        return flagURL;
    }
}
