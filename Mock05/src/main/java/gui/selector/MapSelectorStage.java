package gui.selector;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MapSelectorStage extends Stage {
    private static MapSelectorStage instance;

    private MapSelectorStage() {
        super();
        this.setResizable(false);
        this.setTitle("Map Selector - Nokotan Pathfinder");
        this.getIcons().add(new Image(ClassLoader.getSystemResource("images/deer.png").toString()));
        Scene scene = new Scene(MapSelectorPane.getInstance());
        this.setScene(scene);
    }

    public static MapSelectorStage getInstance() {
        if (instance == null) {
            instance = new MapSelectorStage();
        }
        return instance;
    }
}
