package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public abstract class PlayerFollowerGameEntity extends RandomMoveableGameEntity {

    protected PlayerFollowerGameEntity(Pane pane) {
        super(pane);
    }

    protected Point2D getHeading(int dangerDistance) {
        double distance = Globals.distanceFromSnakeHead(this);
        Point2D heading;
        if (distance < dangerDistance) {
            double ratio = speed / distance;

            heading = Globals.vectorToSnakeHead(this);
            heading = heading.multiply(ratio);
        } else {
            heading = Utils.getRandomMoveVector(this);
        }
        return heading;
    }
}
