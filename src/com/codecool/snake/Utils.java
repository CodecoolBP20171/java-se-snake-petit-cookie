package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.RandomMoveable;
import javafx.geometry.Point2D;

import java.util.Random;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), -length * Math.cos(directionInRadians));
        return heading;
    }

    public static Point2D getRandomMoveVector(RandomMoveable randomMoveable) {
        Random rnd = new Random();
        if (randomMoveable.getCounter() > randomMoveable.getMaxCounter()) {
            randomMoveable.resetCounter();
            randomMoveable.setDirection(rnd.nextDouble() * 360);
        }
        randomMoveable.incrementCounter();
        return directionToVector(randomMoveable.getDirection(), randomMoveable.getSpeed());
    }

    public static double getDirectionToOtherEntity(GameEntity from, GameEntity to) {
        double x = Math.abs(from.getX() - to.getX());
        double y = Math.abs(from.getY() - to.getY());
        double alpha = Math.atan(x / y);
        // target is to top left
        if (from.getX() > to.getX() && from.getY() > to.getY()) {
            return 360 - (alpha * 180 / Math.PI);
            // target is to top right
        } else if (from.getX() < to.getX() && from.getY() > to.getY()) {
            return alpha * 180 / Math.PI;
            // target is to bottom left
        } else if (from.getX() > to.getX() && from.getY() < to.getY()) {
            return 180 + (alpha * 180 / Math.PI);
            // target is to bottom right
        } else {
            return 180 - (alpha * 180 / Math.PI);
        }
    }
}
