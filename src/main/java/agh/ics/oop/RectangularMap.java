package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    private List<Animal> map;
    private final int maxWidth;
    private final int maxHeight;

    private final int minWidth = 0;
    private final int minHeight = 0;


    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public RectangularMap(int width, int height) {
        maxWidth = width;
        maxHeight = height;
        map = new ArrayList<>();
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(minWidth, minHeight), new Vector2d(maxWidth, maxHeight));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x > maxWidth || position.y > maxHeight || position.x < minWidth || position.y < minHeight) {
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition()) && canMoveTo(animal.getPosition())) {
            map.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal :
                map) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal :
                map) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }
}
