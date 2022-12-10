package agh.ics.oop;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Lab04Test {
    @Test
    void test() throws FileNotFoundException {
        // Given
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        List<MoveDirection> directions = new OptionsParser().parse(moves);
        IEngine engine = new SimulationEngine(directions, map, positions, null);
        Animal animal1 = (Animal) map.objectAt(new Vector2d(2,2));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(3,4));

        // When
        engine.run();

        // Then
        assertEquals(new Vector2d(2, 0), animal1.getPosition());
        assertEquals(new Vector2d(3, 5), animal2.getPosition());
    }
}
