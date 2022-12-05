package agh.ics.oop;

import java.util.List;

public class SimulationEngine implements IEngine {

    private final List<MoveDirection> moves;

    private final IWorldMap map;

    private final Vector2d[] animalPositions;

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, Vector2d[] animalPositions) {
        this.moves = moves;
        this.map = map;
        this.animalPositions = animalPositions;
        for (Vector2d animalPosition
                : animalPositions) {
            map.place(new Animal(map, animalPosition));
        }
        if (this.map instanceof GrassField){
            ((GrassField) this.map).initializeGrass();
        }
        ((AbstractWorldMap) this.map).updateMap();
    }

    @Override
    public void run() {
        int animalIndex = 0;
        for (MoveDirection move : moves) {
            Vector2d position = animalPositions[animalIndex];
            Animal animal = (Animal) map.objectAt(position);
            animal.move(move);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            ((AbstractWorldMap) this.map).updateMap();
            animalPositions[animalIndex] = animal.getPosition();
            animalIndex = (animalIndex + 1) % animalPositions.length;
        }
    }
}
