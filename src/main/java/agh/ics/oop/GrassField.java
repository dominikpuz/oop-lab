package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private List<Grass> grassList;
    private int numberOfGrassFields;

    public GrassField(int numberOfGrassFields) {
        this.numberOfGrassFields = numberOfGrassFields;
        animals = new ArrayList<>();
        grassList = new ArrayList<>();
        maxWidth = Integer.MAX_VALUE;
        maxHeight = Integer.MAX_VALUE;
        minWidth = Integer.MIN_VALUE;
        minHeight = Integer.MIN_VALUE;
        spawnGrass();
    }

    public void spawnGrass() {
        Random rand = new Random();
        while (grassList.size()  < numberOfGrassFields) {
            Vector2d grassPosition = new Vector2d(rand.nextInt((int) Math.sqrt(numberOfGrassFields * 10)), rand.nextInt((int) Math.sqrt(numberOfGrassFields * 10)));
            if (!isOccupied(grassPosition)) {
                grassList.add(new Grass(grassPosition));
            }
        }
    }

    public void removeGrass(Grass grass) {
        grassList.remove(grass);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animal :
                animals) {
            if (animal.getPosition().equals(position)) {
                return false;
            }
        }
        return super.canMoveTo(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal :
                animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        for (Grass grass :
                grassList) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public Vector2d getMinBounds() {
        Vector2d minBounds = new Vector2d(maxWidth, maxHeight);
        for (Animal animal :
                animals) {
            minBounds = minBounds.lowerLeft(animal.getPosition());
        }
        for (Grass grass :
                grassList) {
            minBounds = minBounds.lowerLeft(grass.getPosition());
        }
        return minBounds;
    }

    @Override
    public Vector2d getMaxBounds() {
        Vector2d maxBounds = new Vector2d(minWidth, minHeight);
        for (Animal animal :
                animals) {
            maxBounds = maxBounds.upperRight(animal.getPosition());
        }
        for (Grass grass :
                grassList) {
            maxBounds = maxBounds.upperRight(grass.getPosition());
        }
        return maxBounds;
    }
}
