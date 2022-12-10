package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private SortedSet<Vector2d> xAxis;
    private SortedSet<Vector2d> yAxis;

    private Vector2d minBounds;
    private Vector2d maxBounds;


    public MapBoundary() {
        xAxis = new TreeSet<>(Comparator.comparing(Vector2d::getX).thenComparing(Vector2d::getY));
        yAxis = new TreeSet<>(Comparator.comparing(Vector2d::getY).thenComparing(Vector2d::getX));
        minBounds = new Vector2d(0,0);
        maxBounds = new Vector2d(0,0);
    }

    public Vector2d getMinBounds() {
        return minBounds;
    }

    public Vector2d getMaxBounds() {
        return maxBounds;
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xAxis.remove(oldPosition);
        yAxis.remove(oldPosition);
        xAxis.add(newPosition);
        yAxis.add(newPosition);
        updateBoundary();
    }

    public void place(Vector2d position) {
        xAxis.add(position);
        yAxis.add(position);
        updateBoundary();
    }

    public void remove(Vector2d position) {
        xAxis.remove(position);
        yAxis.remove(position);
        updateBoundary();
    }

    private void updateBoundary() {
        minBounds = new Vector2d(xAxis.first().getX(), yAxis.first().getY());
        maxBounds = new Vector2d(xAxis.last().getX(), yAxis.last().getY());
    }
}
