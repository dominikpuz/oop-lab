package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OptionsParser {
    public List<MoveDirection> parse(String[] moves) {
        return Arrays.stream(moves)
                .map(OptionsParser::mapToMoveDirection)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static private MoveDirection mapToMoveDirection(String move) {
        return switch (move) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "l", "left" -> MoveDirection.LEFT;
            case "r", "right" -> MoveDirection.RIGHT;
            default -> throw new IllegalArgumentException(move + " is not legal move specification");
        };
    }
}
