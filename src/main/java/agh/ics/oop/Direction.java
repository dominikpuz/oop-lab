package agh.ics.oop;

public enum Direction {
    FORWARD("Zwierzak idzie do przodu"),
    BACKWARD("Zwierzak idzie do tyłu"),
    RIGHT("Zwierzak skręca w prawo"),
    LEFT("Zwierzak skręca w lewo");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
