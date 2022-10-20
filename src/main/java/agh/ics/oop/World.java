package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
//        Lab01
        System.out.println("system wystartowal");
        run(convert(args));
        System.out.println("system zakonczyl dzialanie");

//        Lab02
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection test = MapDirection.NORTH;
        System.out.println(test.toUnitVector());

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
