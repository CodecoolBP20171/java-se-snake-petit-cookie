package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class GrandmaPowerup extends RandomMoveableGameEntity implements Interactable, Animatable, PowerUps {

    private final int COOKIEGAIN = 2;
    private final int FRAMESINONEDIRECTION = 60;

    public GrandmaPowerup(Pane pane) {
        super(pane);
        speed = 1;
        setImage(Globals.powerupGrandma);
        pane.getChildren().add(this);

        setRandomPostition();
    }

    @Override
    public void apply(GirlScout girlScout) {
        girlScout.addPart(COOKIEGAIN);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Picked up multiple cookies.";
    }

    @Override
    public void step() {
        Point2D heading = Utils.getRandomMoveVector(this);
        moveEntity(heading);
    }

    @Override
    public int getMaxCounter() {
        return FRAMESINONEDIRECTION;
    }
}
