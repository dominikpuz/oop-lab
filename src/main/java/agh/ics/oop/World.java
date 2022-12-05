package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class World {
    public static void main(String[] args) {
//        f b r l f f r r f f f f f f f f
        try {
            Application.launch(App.class, args);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    static void run(List<Direction> args) {
        args.stream()
                .map(Direction::getValue)
                .forEach(System.out::println);
    }

    static Direction mapToEnum(String arg) {
        return switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> null;
        };
    }

    static List<Direction> convert(String[] args) {
        return Arrays.stream(args)
                .map(World::mapToEnum)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
