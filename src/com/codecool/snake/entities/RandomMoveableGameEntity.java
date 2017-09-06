package com.codecool.snake.entities;

import javafx.scene.layout.Pane;

public abstract class RandomMoveableGameEntity extends GameEntity implements RandomMoveable {

    protected double speed;
    protected int counter;
    protected double direction;

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public void resetCounter() {
        counter = 0;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public void incrementCounter() {
        counter++;
    }

    protected RandomMoveableGameEntity(Pane pane) {
        super(pane);
    }
}
