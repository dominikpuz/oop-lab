package agh.ics.oop;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, IMapElement> objects;
    protected int maxWidth;
    protected int maxHeight;

    protected int minWidth;
    protected int minHeight;
    protected GridPane grid;

    protected int gridSize = 50;

    public AbstractWorldMap() {
        this.objects = new HashMap<>();
    }

    public AbstractWorldMap(GridPane grid) {
        this();
        this.grid = grid;
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getMinBounds(), getMaxBounds());
    }

    public void updateMap() {
        grid.getChildren().clear();

        Vector2d minBound = getMinBounds();
        Vector2d maxBound = getMaxBounds();
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
                if (objects.get(new Vector2d(minBound.getX() + i - 1, maxBound.getY() - j + 1)) == null) {
                    Label field = new Label("");
                    field.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                    field.setAlignment(Pos.CENTER);
                    field.setMinWidth(gridSize);
                    field.setMinHeight(gridSize);
                    GridPane.setConstraints(field, i, j);
                    grid.getChildren().add(field);
                } else {
                    Label field = new Label(objects.get(new Vector2d(minBound.getX() + i - 1, maxBound.getY() - j + 1)).toString());
                    field.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1");
                    field.setAlignment(Pos.CENTER);
                    field.setMinWidth(gridSize);
                    field.setMinHeight(gridSize);
                    GridPane.setConstraints(field, i, j);
                    grid.getChildren().add(field);
                }
            }
        }
    }

    abstract Vector2d getMinBounds();
    abstract Vector2d getMaxBounds();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = objects.get(oldPosition);
        objects.remove(oldPosition);
        objects.put(newPosition, element);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.getX() > maxWidth || position.getY() > maxHeight || position.getX() < minWidth || position.getY() < minHeight) {
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return objects.get(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition()) && canMoveTo(animal.getPosition())) {
            objects.put(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition() + " is not available to place animal");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
}
