package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private int numberOfGrassFields;

    public GrassField(int numberOfGrassFields) {
        super();
        this.numberOfGrassFields = numberOfGrassFields;
        maxWidth = Integer.MAX_VALUE;
        maxHeight = Integer.MAX_VALUE;
        minWidth = Integer.MIN_VALUE;
        minHeight = Integer.MIN_VALUE;
    }

    public void initializeGrass() {
        Random rand = new Random();
        for (int i = 0; i < numberOfGrassFields; i++) {
            placeGrass();
        }
    }

    public void placeGrass() {
        Random rand = new Random();
        while (true) {
            Vector2d position = new Vector2d(rand.nextInt((int) Math.sqrt(numberOfGrassFields * 10)), rand.nextInt((int) Math.sqrt(numberOfGrassFields * 10)));
            if (!isOccupied(position)) {
                objects.put(position, new Grass(position));
                break;
            }
        }
    }

    public void removeGrass(Grass grass) { objects.remove(grass.getPosition()); }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && !(objects.get(position) instanceof Animal);
    }

    @Override
    public Vector2d getMinBounds() {
        Vector2d minBounds = new Vector2d(maxWidth, maxHeight);
        for (Vector2d position :
                objects.keySet()) {
            minBounds = minBounds.lowerLeft(position);
        }
        return minBounds;
    }

    @Override
    public Vector2d getMaxBounds() {
        Vector2d maxBounds = new Vector2d(minWidth, minHeight);
        for (Vector2d position :
                objects.keySet()) {
            maxBounds = maxBounds.upperRight(position);
        }
        return maxBounds;
    }
}
