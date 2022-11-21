package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() {
//        Given
        RectangularMap map = new RectangularMap(3, 3);
        Animal animal = new Animal(map, new Vector2d(2, 2));
//        When
        map.place(animal);
//        Then
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(4,5)));
    }

    @Test
    void place() {
//        Given
        RectangularMap map = new RectangularMap(3, 3);
//        When
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(2, 2));
        Animal animal3 = new Animal(map, new Vector2d(5, 5));
//        Then
        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
        assertFalse(map.place(animal3));
    }

    @Test
    void isOccupied() {
//        Given
        RectangularMap map = new RectangularMap(3,3);
        Animal animal = new Animal(map, new Vector2d(2,2));
//        When
        map.place(animal);
//        Then
        assertFalse(map.isOccupied(new Vector2d(3, 3)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAt() {
//        Given
        RectangularMap map = new RectangularMap(3, 3);
        Animal animal = new Animal(map, new Vector2d(2, 2));
//        When
        map.place(animal);
//        Then
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
        assertNull(map.objectAt(new Vector2d(3, 3)));
    }
}