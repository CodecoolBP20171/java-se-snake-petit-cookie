package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class GirlScout extends GameEntity implements Animatable {

    private static final int MAX_HEALTH = 100;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private float speed = 2;
    private float turnRate = 2;
    private double dir;
    private int health;
    private int snakeLength;
    private int animationDirection = 0;
    private int animationFrame = 0;

    public GirlScout(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        setTranslateX(-Globals.PLAYER_SPRITE_SIZE / 2);
        setTranslateY(-Globals.PLAYER_SPRITE_SIZE / 2);
        health = MAX_HEALTH;
        tail = this;
        setImage(Globals.playerSprites);
        Globals.snakeHeadEntity = this;
        pane.getChildren().add(this);
        snakeLength = 0;

        addPart(4);
    }

    public void step() {
        calculateNewDirection();
        animate();
        move();
        checkCollision();
        checkGameOver();
    }

    private void move() {
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        translateXProperty();
        translateYProperty();
    }

    private void animate() {
        int newAnimationDirection = getCurrentAnimationDirection();
        if (newAnimationDirection != animationDirection) {
            animationDirection = newAnimationDirection;
            animationFrame = 0;
        }
        Rectangle2D view = getCurrentFrameView();
        setViewport(view);
        animationFrame++;
        animationFrame %= Globals.PLAYER_SPRITE_FRAME_COUNT;
    }

    private void checkCollision() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
    }

    private void checkGameOver() {
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
        }
    }

    private void calculateNewDirection() {
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        dir %= 360;
        if (dir < 0)
            dir += 360;
    }

    public void addPart(int numParts) {
        snakeLength += numParts;
        for (int i = 0; i < numParts; i++) {
            LittleRedWagon newPart = new LittleRedWagon(pane, tail);
            tail = newPart;
        }
    }

    public int getHealth() {
        return health;
    }

    public void removePart(int numParts) {
        snakeLength -= numParts;
        for (int i = 0; i < numParts; i++) {
            if (tail instanceof LittleRedWagon) {
                GameEntity oldPart = ((LittleRedWagon) tail).getGameParent();
                tail.destroy();
                tail = oldPart;
            }
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeSpeed(int diff) {
        speed += diff;
        turnRate += diff;
    }

    private int getCurrentAnimationDirection() {
        if (dir > 315 || dir < 45) return 0;
        if (dir > 225) return 1;
        if (dir > 135) return 2;
        return 3;
    }

    private Rectangle2D getCurrentFrameView() {
        return new Rectangle2D(animationFrame * Globals.PLAYER_SPRITE_SIZE,
                animationDirection * Globals.PLAYER_SPRITE_SIZE,
                Globals.PLAYER_SPRITE_SIZE, Globals.PLAYER_SPRITE_SIZE);
    }
}
