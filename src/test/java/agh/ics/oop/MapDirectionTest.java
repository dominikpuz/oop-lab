package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void next() {
        MapDirection test = MapDirection.NORTH;
        assertEquals(MapDirection.EAST, test.next());
        test = MapDirection.EAST;
        assertEquals(MapDirection.SOUTH, test.next());
        test = MapDirection.SOUTH;
        assertEquals(MapDirection.WEST, test.next());
        test = MapDirection.WEST;
        assertEquals(MapDirection.NORTH, test.next());

    }

    @Test
    void previous() {
        MapDirection test = MapDirection.NORTH;
        assertEquals(MapDirection.WEST, test.previous());
        test = MapDirection.WEST;
        assertEquals(MapDirection.SOUTH, test.previous());
        test = MapDirection.SOUTH;
        assertEquals(MapDirection.EAST, test.previous());
        test = MapDirection.EAST;
        assertEquals(MapDirection.NORTH, test.previous());
    }
}