package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class CustomerPowerup  extends RandomMoveableGameEntity implements Interactable, Animatable, PowerUps {

    private  final int COOKIELOSS = 1;
    private final int HEALTHGAIN = 10;

    public CustomerPowerup(Pane pane) {
        super(pane);
        speed = 1.5;
        setImage(Globals.powerupCustomer);
        pane.getChildren().add(this);

        setRandomPostition();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(COOKIELOSS);
        snakeHead.changeHealth(HEALTHGAIN);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Customer payed up.";
    }

    @Override
    public void step() {
        Point2D heading = Utils.getRandomMoveVector(this);
        moveEntity(heading);
    }

    @Override
    public int getMaxCounter() {
        return 60;
    }
}
