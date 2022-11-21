package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        maxWidth = width;
        maxHeight = height;
        minWidth = 0;
        minHeight = 0;
        animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && !isOccupied(position);
    }

    @Override
    public Vector2d getMinBounds() {
        return new Vector2d(minWidth, minHeight);
    }

    @Override
    public Vector2d getMaxBounds() {
        return new Vector2d(maxWidth, maxHeight);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal :
                animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

}
