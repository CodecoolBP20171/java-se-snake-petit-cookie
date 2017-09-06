package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    protected void moveEntity(Point2D heading) {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    protected final void setRandomPostition() {
        Random rnd = new Random();
        do {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        } while (Math.abs(getX() - Globals.snakeHeadEntity.getX()) < Globals.MIN_SPAWN_GAP);
        do {
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        } while (Math.abs(getY() - Globals.snakeHeadEntity.getY()) < Globals.MIN_SPAWN_GAP);
    }
}
