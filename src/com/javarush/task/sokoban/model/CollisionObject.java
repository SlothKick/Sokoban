package com.javarush.task.sokoban.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (gameObject==null || direction==null) {
            return false;
        }

        int moveX = 0;
        int moveY =0;

        switch (direction) {
            case UP: moveY = moveY - Model.FIELD_CELL_SIZE; break;
            case DOWN: moveY = moveY + Model.FIELD_CELL_SIZE; break;
            case LEFT: moveX = moveX - Model.FIELD_CELL_SIZE; break;
            case RIGHT: moveX = moveX + Model.FIELD_CELL_SIZE; break;
        }
        return (this.getX() + moveX == gameObject.getX()) && (this.getY() + moveY == gameObject.getY());
    }
}
