package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        ScrollPane sp = new ScrollPane(grid);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        scene = new Scene(sp, 400, 400);
        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
        primaryStage.show();
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw ();
        IWorldMap map = new GrassField(10, grid);

        List<MoveDirection> directions = new OptionsParser().parse(args.toArray(new String[0]));
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

//    @Override
//    public void init() throws Exception {
//        super.init();
//
//
//    }
}
