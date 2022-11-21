package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals;
    protected int maxWidth;
    protected int maxHeight;

    protected int minWidth;
    protected int minHeight;



    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getMinBounds(), getMaxBounds());
    }

    abstract Vector2d getMinBounds();
    abstract Vector2d getMaxBounds();

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x > maxWidth || position.y > maxHeight || position.x < minWidth || position.y < minHeight) {
            return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition()) && canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
}
