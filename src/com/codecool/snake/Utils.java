package com.codecool.snake;

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
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static Point2D getRandomMoveVector(RandomMoveable randomMoveable) {
        Random rnd = new Random();
        if(randomMoveable.getCounter() > randomMoveable.getMaxCounter()) {
            randomMoveable.resetCounter();
            randomMoveable.setDirection(rnd.nextDouble() * 360);
        }
        randomMoveable.incrementCounter();
        return Utils.directionToVector(randomMoveable.getDirection(), randomMoveable.getSpeed());

    }
}
