package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.scene.layout.Pane;

public class CookiePowerup extends GameEntity implements Interactable {

    private final int COOKIEGAIN = 1;

    public CookiePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupCookie);
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
        return "Picked up cookie.";
    }
}
