package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.Command;

public class CommandControllerPane extends GridPane {

    public CommandControllerPane(){
        CommandArrowPane upPane = new CommandArrowPane(Command.UP);
        CommandArrowPane leftPane = new CommandArrowPane(Command.LEFT);
        CommandArrowPane rightPane = new CommandArrowPane(Command.RIGHT);
        CommandArrowPane downPane = new CommandArrowPane(Command.DOWN);

        // TODO: Add the 4 CommandArrowPane to the grid.
        this.add(upPane, 1, 0);
        this.add(leftPane, 0, 1);
        this.add(rightPane, 2, 1);
        this.add(downPane, 1, 2);


        //No code change below this point.
        this.setAlignment(Pos.CENTER);

        setHgap(10);
        setVgap(10);

        setPrefHeight(200);
        setPrefWidth(200);
    }

}
