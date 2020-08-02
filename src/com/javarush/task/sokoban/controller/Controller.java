package com.javarush.task.sokoban.controller;


import com.javarush.task.sokoban.model.Direction;
import com.javarush.task.sokoban.model.GameObjects;
import com.javarush.task.sokoban.model.Model;
import com.javarush.task.sokoban.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        this.model = new Model();
        model.restart();
        model.setEventListener(this);
        this.view = new View(this);
        view.init();
        view.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
