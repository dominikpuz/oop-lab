package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
//        Given
        OptionsParser parser = new OptionsParser();
//        When
        List<MoveDirection> list = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> parser.parse(new String[]{"123"}));
//        Then
        assertEquals(list, parser.parse(new String[]{"f", "forward", "b", "backward", "l", "left", "r", "right"}));
        assertTrue(thrown.getMessage().contains("is not legal move specification"));
    }
}