package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        JFrame frame = new JFrame();
        JPanel container = new JPanel();
        JScrollPane scrPane = new JScrollPane(container);
        scrPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea area = new JTextArea(map.toString());
        container.add((area));
        frame.add(scrPane);
        frame.setVisible(true);
        area.setFont(new Font("Monospaced", Font.PLAIN, 50));

        IEngine engine = new SimulationEngine(directions, map, positions, area);
        engine.run();
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
