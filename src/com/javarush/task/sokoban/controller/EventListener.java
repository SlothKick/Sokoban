package com.javarush.task.sokoban.controller;

import com.javarush.task.sokoban.model.Direction;

public interface EventListener {

    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);
}
