package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
<<<<<<< HEAD
import com.codecool.snake.entities.PowerUps;
import com.codecool.snake.entities.snakes.SnakeHead;
=======
import com.codecool.snake.entities.snakes.GirlScout;
>>>>>>> animation-and-graphics
import javafx.scene.layout.Pane;

public class CookiePowerup extends GameEntity implements Interactable, PowerUps {

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
