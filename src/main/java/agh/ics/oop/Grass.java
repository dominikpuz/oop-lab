package agh.ics.oop;

public class Grass extends IMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
