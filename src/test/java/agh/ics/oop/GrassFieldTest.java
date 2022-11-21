package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveTo() {
//        Given
        GrassField map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(-1, -1));
//        When
        map.place(animal);
//        Then
        assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
        assertTrue(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void place() {
//        Given
        GrassField map = new GrassField(5);
//        When
        Animal animal1 = new Animal(map, new Vector2d(-1, -1));
        Animal animal2 = new Animal(map, new Vector2d(-1, -1));
//        Then
        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
    }

    @Test
    void isOccupied() {
//        Given
        GrassField map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(-1, -1));
//        When
        map.place(animal);
//        Then
        assertFalse(map.isOccupied(new Vector2d(-2, -2)));
        assertTrue(map.isOccupied(new Vector2d(-1, -1)));
    }

    @Test
    void objectAt() {
//        Given
        GrassField map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(-1,-1));
//        When
        map.place(animal);
//        Then
        assertEquals(animal, map.objectAt(new Vector2d(-1, -1)));
        assertNull(map.objectAt(new Vector2d(-1, -2)));
    }
}