package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.io.FileNotFoundException;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private List<MoveDirection> moves;
    private final IWorldMap map;
    private final Vector2d[] animalPositions;
    App mapObserver;

    public void setMoves(List<MoveDirection> newMoves) {
        moves = newMoves;
    }

    public SimulationEngine(IWorldMap map, Vector2d[] animalPositions, App mapObserver) {
        this.map = map;
        this.animalPositions = animalPositions;
        this.mapObserver = mapObserver;
        for (Vector2d animalPosition
                : animalPositions) {
            map.place(new Animal(map, animalPosition));
        }
        if (this.map instanceof GrassField) {
            ((GrassField) this.map).initializeGrass();
        }
        if (mapObserver != null) {
            this.mapObserver.updateMap();
        }
    }
    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, Vector2d[] animalPositions, App mapObserver) {
        this(map, animalPositions, mapObserver);
        this.moves = moves;
    }

    @Override
    public void run() {
        int animalIndex = 0;
        for (MoveDirection move : moves) {
            Vector2d position = animalPositions[animalIndex];
            Animal animal = (Animal) map.objectAt(position);
            try {
                animal.move(move);
                if (mapObserver != null) {
                    this.mapObserver.updateMap();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            animalPositions[animalIndex] = animal.getPosition();
            animalIndex = (animalIndex + 1) % animalPositions.length;
        }
    }
}
