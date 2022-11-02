package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser parser = new OptionsParser();
        List<MoveDirection> list = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.RIGHT);
        assertEquals(list, parser.parse(new String[]{"f", "forward", "b", "backward", "l", "left", "r", "right", "123"}));
    }
}