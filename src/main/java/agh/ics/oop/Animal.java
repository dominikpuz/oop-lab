package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class Animal extends IMapElement{
    private MapDirection direction;
    final private IWorldMap map;

    final private List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map) {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);
        this.map = map;
        this.observers = new ArrayList<>();
        addObserver((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.observers = new ArrayList<>();
        addObserver((IPositionChangeObserver) map);
    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return switch (direction) {
            case NORTH -> "N";
            case WEST -> "W";
            case SOUTH -> "S";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d nextPosition = null;
        if (direction == MoveDirection.LEFT) {
            this.direction = this.direction.previous();
            return;
        } else if (direction == MoveDirection.RIGHT) {
            this.direction = this.direction.next();
            return;
        } else if (direction == MoveDirection.FORWARD) {
            nextPosition = this.position.add(this.direction.toUnitVector());
        } else if (direction == MoveDirection.BACKWARD) {
            nextPosition = this.position.subtract(this.direction.toUnitVector());
        }
        if (map.canMoveTo(nextPosition)) {
            Object object = map.objectAt(nextPosition);
            if (object instanceof Grass) {
                ((GrassField) map).removeGrass((Grass) object);
                ((GrassField) map).placeGrass();
            }
            for (IPositionChangeObserver observer:
                 observers) {
                observer.positionChanged(this.position, nextPosition);
            }
            this.position = nextPosition;

        }
    }
}
