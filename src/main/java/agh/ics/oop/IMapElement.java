package agh.ics.oop;

public abstract class IMapElement {
    protected Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    abstract public String getTextureName();


}
