package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private int numberOfGrassFields;

    private MapBoundary mapBoundary;

    public GrassField(int numberOfGrassFields) {
        super();
        mapBoundary = new MapBoundary();
        this.numberOfGrassFields = numberOfGrassFields;
        maxWidth = Integer.MAX_VALUE;
        maxHeight = Integer.MAX_VALUE;
        minWidth = Integer.MIN_VALUE;
        minHeight = Integer.MIN_VALUE;
    }

    public IPositionChangeObserver getBoundsObserver() { return mapBoundary; }

    public void initializeGrass() {
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
                mapBoundary.place(position);
                break;
            }
        }
    }

    public void removeGrass(Grass grass) {
        objects.remove(grass.getPosition());
        mapBoundary.remove(grass.getPosition());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && !(objects.get(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if(super.place(animal)) {
            mapBoundary.place(animal.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public Vector2d getMinBounds() {
        return mapBoundary.getMinBounds();
    }

    @Override
    public Vector2d getMaxBounds() {
        return mapBoundary.getMaxBounds();
    }
}
