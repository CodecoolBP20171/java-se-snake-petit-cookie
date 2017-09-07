package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class CustomerPowerup  extends RandomMoveableGameEntity implements Interactable, Animatable {

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
    public void apply(GirlScout girlScout) {
        girlScout.removePart(COOKIELOSS);
        girlScout.changeHealth(HEALTHGAIN);
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
