package model;

public enum GameMode {
    RANDOM("Random Numbers"), SEQUENCE("Sequence of Numbers");

    private final String name;
    GameMode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}