package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, IMapElement> objects;
    protected int maxWidth;
    protected int maxHeight;

    protected int minWidth;
    protected int minHeight;

    public AbstractWorldMap() {
        this.objects = new HashMap<>();
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getMinBounds(), getMaxBounds());
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
        if (position.x > maxWidth || position.y > maxHeight || position.x < minWidth || position.y < minHeight) {
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
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
}
