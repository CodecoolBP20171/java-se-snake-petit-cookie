package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private static final int MAX_HEALTH = 100;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int snakeLength;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = MAX_HEALTH;
        tail = this;
        setImage(Globals.snakeHead);
        Globals.snakeHeadEntity = this;
        pane.getChildren().add(this);
        snakeLength = 0;

        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.healthBar.setVisible(false);
            Globals.endGameLabel.setVisible(true);
            Globals.infoPanel.setVisible(true);
            Globals.snakeLength.setText("You had " + snakeLength + " red toy wagons full of cookies");
            Globals.snakeLength.setVisible(true);
            for (GameEntity gameEntity : Globals.gameObjects) {
                gameEntity.destroy();
            }
            //Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        snakeLength += numParts;
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int diff) {
        health += diff;
    }

}
