package com.javarush.task.sokoban.model;


import com.javarush.task.sokoban.controller.EventListener;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private Path path = Paths.get("E:\\Java\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt");
    private LevelLoader levelLoader = new LevelLoader(path);

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel += 1;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if(checkWallCollision(player, direction)) {
            return;
        }
        if(checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }
        moveObject(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if(gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public void checkCompletion() {
        int finishedBoxes = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    finishedBoxes++;

                }
            }
        }

        if (finishedBoxes == gameObjects.getBoxes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        CollisionObject player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            boolean hasBox = player.isCollision(box, direction);
            if(hasBox) {
                if (checkWallCollision(box, direction)) {
                    return true;
                }
                for (Box dBox : gameObjects.getBoxes()) {
                    boolean hasDoubleBoxes = box.isCollision(dBox, direction);
                    if (hasDoubleBoxes) {
                        return true;
                    }
                }
                moveObject(box, direction);
            }
        }
        return false;
    }

    private void moveObject(GameObject gameObject, Direction direction) {
        switch (direction) {
            case UP:
                gameObject.setY(gameObject.getY() - FIELD_CELL_SIZE);
                break;
            case DOWN:
                gameObject.setY(gameObject.getY() + FIELD_CELL_SIZE);
                break;
            case LEFT:
                gameObject.setX(gameObject.getX() - FIELD_CELL_SIZE);
                break;
            case RIGHT:
                gameObject.setX(gameObject.getX() + FIELD_CELL_SIZE);
                break;
        }
    }
}
