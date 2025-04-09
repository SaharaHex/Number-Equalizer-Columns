package model;

public interface Game {
    void moveLeft();
    void moveRight();
    void dropNumber();
    boolean checkWin();
    void turnNumber();
    GameMode getGameMode();
}