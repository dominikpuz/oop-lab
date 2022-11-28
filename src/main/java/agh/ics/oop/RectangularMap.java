package agh.ics.oop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        super();
        maxWidth = width;
        maxHeight = height;
        minWidth = 0;
        minHeight = 0;
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


}
