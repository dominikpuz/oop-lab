package agh.ics.oop;

import javax.swing.*;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final List<MoveDirection> moves;

    private final IWorldMap map;

    private final Vector2d[] animalPositions;

    private JTextArea area = null;

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, Vector2d[] animalPositions, JTextArea area) {
        this.moves = moves;
        this.map = map;
        this.animalPositions = animalPositions;
        this.area = area;
        for (Vector2d animalPosition : animalPositions) {
            map.place(new Animal(map, animalPosition));
        }
        area.setText(map.toString());
    }

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, Vector2d[] animalPositions) {
        this.moves = moves;
        this.map = map;
        this.animalPositions = animalPositions;
        for (Vector2d animalPosition
                : animalPositions) {
            new Animal(map, animalPosition);
        }
        if (this.map instanceof GrassField){
            ((GrassField) map).spawnGrass();
        }
        System.out.print(map);
    }

    @Override
    public void run() {
        int animalIndex = 0;
        for (MoveDirection move : moves) {
            Vector2d position = animalPositions[animalIndex];
            Animal animal = (Animal) map.objectAt(position);
            if (area != null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            animal.move(move);
            if (area != null) {
                area.setText(map.toString());
            } else {
                System.out.print(map);
            }
            animalPositions[animalIndex] = animal.getPosition();
            animalIndex = (animalIndex + 1) % animalPositions.length;
        }
    }
}
