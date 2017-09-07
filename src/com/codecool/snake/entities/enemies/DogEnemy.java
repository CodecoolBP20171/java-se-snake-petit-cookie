package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class DogEnemy extends PlayerFollowerGameEntity implements Interactable, Animatable, Enemies {

    private final int HEALTHLOSS = 20;
    private final int FRAMESINONEDIRECTION = 30;
    private final int DANGERDISTANCE = 200;

    public DogEnemy (Pane pane) {
        super(pane);
        speed = 1.5;
        setImage(Globals.dogEnemy);
        pane.getChildren().add(this);

        setRandomPostition();
    }

    @Override
    public void step() {
        Point2D heading = getHeading(DANGERDISTANCE);
        moveEntity(heading);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(-HEALTHLOSS);
        destroy();
    }

    @Override
    public String getMessage() {
        return "The dog has bitten";
    }

    @Override
    public int getMaxCounter() {
        return FRAMESINONEDIRECTION;
    }


}
