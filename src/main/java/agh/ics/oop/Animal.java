package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "direction=" + direction +
                ", position=" + position +
                '}';
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();

            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> {
                this.position = this.position.add(this.direction.toUnitVector());
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight((new Vector2d(0, 0)));
            }
            case BACKWARD -> {
                this.position = this.position.subtract(this.direction.toUnitVector());
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight((new Vector2d(0, 0)));
            }
        }
    }
}
