package com.codecool.snake.entities;

public interface RandomMoveable {
    int getCounter();
    void resetCounter();
    int getMaxCounter();
    double getSpeed();
    double getDirection();
    void setDirection(double direction);
    void incrementCounter();
}
