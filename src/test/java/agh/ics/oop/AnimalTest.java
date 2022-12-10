package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void isAt() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal = new Animal(map);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2,1)));
        assertFalse(animal.isAt(new Vector2d(2,2)));
    }

    @Test
    void move() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(4,4);
        Animal animal = new Animal(map);
        // Test forward
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        // Test right
        animal.move(MoveDirection.RIGHT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getDirection());
        // Test left
        animal.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        // Test backward
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        // Test map bounds
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 4), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getDirection());
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 4), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getDirection());
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0, 0), animal.getPosition());
        assertEquals(MapDirection.SOUTH, animal.getDirection());

    }
}