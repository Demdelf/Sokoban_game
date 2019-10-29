package game.controller;

import game.model.Direction;

public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);
}
