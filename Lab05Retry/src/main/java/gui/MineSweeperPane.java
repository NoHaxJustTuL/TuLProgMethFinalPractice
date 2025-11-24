package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MineSweeperPane extends GridPane {
    private ArrayList<MineSweeperSquare> allCells;

    public MineSweeperPane(){
        allCells = new ArrayList<>();
        setHgap(8);
        setVgap(8);
        setPadding(new Insets(8));
        setPrefWidth(500);
        setAlignment(Pos.CENTER);
        BorderStroke borderStroke = new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);
        this.setBorder(border);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 5; y++){
                MineSweeperSquare square = new MineSweeperSquare(x, y);
                allCells.add(square);
                this.add(square, x, y);
            }
        }
    }

    public ArrayList<MineSweeperSquare> getAllCells(){
        return allCells;
    }
}
