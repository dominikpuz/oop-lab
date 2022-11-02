package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
//      Lab03
        Animal animal = new Animal();
        OptionsParser parser = new OptionsParser();
        for (MoveDirection move:
             parser.parse(args)) {
            animal.move(move);
            System.out.println(animal);
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
