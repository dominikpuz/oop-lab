package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        run(convert(args));
        System.out.println("system zakonczyl dzialanie");
    }
    static void run(List<Direction> args) {
        args.stream()
                .map(Direction::getValue)
                .forEach(System.out::println);
    }
    static Direction mapToEnum(String arg) {
        switch (arg) {
            case "f":
                return Direction.FORWARD;
            case "b":
                return Direction.BACKWARD;
            case "r":
                return Direction.RIGHT;
            case "l":
                return Direction.LEFT;
        }
        return null;
    }
    static List<Direction> convert(String[] args) {
        return Arrays.stream(args)
                .map(World::mapToEnum)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
