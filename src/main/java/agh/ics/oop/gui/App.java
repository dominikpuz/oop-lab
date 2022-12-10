package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application {

    private GridPane grid;
    private AbstractWorldMap map;
    Scene scene;
    private final int gridSize = 50;
    private final int moveDelay = 500;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() {
        Button button = new Button("Start");
        TextField textField = new TextField();
        Label instructions = new Label("Enter directions separated by spaces e.g. 'f f b l r' :");
        HBox menu = new HBox(instructions, textField, button);
        HBox.setMargin(instructions, new Insets(5));
        HBox.setMargin(textField, new Insets(5));
        HBox.setMargin(button, new Insets(5));
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        ScrollPane sp = new ScrollPane(grid);
        VBox container = new VBox(menu, sp);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        scene = new Scene(container, 700, 600);
//        Parameters parameters = getParameters();
//        List<String> args = parameters.getRaw();
//        List<MoveDirection> directions = new OptionsParser().parse(args.toArray(new String[0]));
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        map = new GrassField(10);
        SimulationEngine engine = new SimulationEngine(map, positions, this);
        button.setOnAction(e -> {
            try {
                Thread engineThread = new Thread(engine);
                String newMovesString = textField.getText();
                List<MoveDirection> newMoves = new OptionsParser().parse(newMovesString.split(" "));
                engine.setMoves(newMoves);
                engineThread.start();
                textField.clear();
            } catch (IllegalArgumentException exception) {
                System.out.print(exception);
                System.exit(0);
            }
        });
    }

    public void updateMap() {
        Platform.runLater(() -> {
            grid.getChildren().clear();

            Vector2d minBound = map.getMinBounds();
            Vector2d maxBound = map.getMaxBounds();
            int numOfRows = maxBound.getY() - minBound.getY() + 1;
            int numOfCols = maxBound.getX() - minBound.getX() + 1;

            Label label = new Label("y\\x");
            label.setMinWidth(gridSize);
            label.setMinHeight(gridSize);
            label.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
            label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setConstraints(label, 0, 0);
            grid.getChildren().add(label);

            for (int i = 1; i <= numOfCols; i++) {
                Label field = new Label(Integer.toString(minBound.getX() + i - 1));
                field.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                field.setAlignment(Pos.CENTER);
                field.setMinWidth(gridSize);
                field.setMinHeight(gridSize);
                GridPane.setConstraints(field, i, 0);
                grid.getChildren().add(field);
            }
            for (int i = 1; i <= numOfRows; i++) {
                Label field = new Label(Integer.toString(maxBound.getY() - i + 1));
                field.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                field.setAlignment(Pos.CENTER);
                field.setMinWidth(gridSize);
                field.setMinHeight(gridSize);
                GridPane.setConstraints(field, 0, i);
                grid.getChildren().add(field);
            }
            for (int i = 1; i <= numOfCols; i++) {
                for (int j = 1; j <= numOfRows; j++) {
                    if (map.objectAt(new Vector2d(minBound.getX() + i - 1, maxBound.getY() - j + 1)) == null) {
                        VBox field = new VBox();
                        field.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                        field.setAlignment(Pos.CENTER);
                        field.setMinWidth(gridSize);
                        field.setMinHeight(gridSize);
                        GridPane.setConstraints(field, i, j);
                        grid.getChildren().add(field);
                    } else {
                        IMapElement object = (IMapElement) map.objectAt(new Vector2d(minBound.getX() + i - 1, maxBound.getY() - j + 1));
                        GuiElementBox field;
                        try {
                            field = new GuiElementBox(object);
                            field.getBox().setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                            field.getBox().setMinWidth(gridSize);
                            field.getBox().setMinHeight(gridSize);
                            GridPane.setConstraints(field.getBox(), i, j);
                            grid.getChildren().add(field.getBox());
                        } catch (FileNotFoundException e) {
                            Label errorLabel = new Label("ERROR");
                            VBox errorField = new VBox(errorLabel);
                            errorField.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                            errorField.setAlignment(Pos.CENTER);
                            errorField.setMinWidth(gridSize);
                            errorField.setMinHeight(gridSize);
                            GridPane.setConstraints(errorField, i, j);
                            grid.getChildren().add(errorField);
                        }
                    }
                }
            }
        });
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
