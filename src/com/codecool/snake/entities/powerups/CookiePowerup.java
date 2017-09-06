package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class CookiePowerup extends GameEntity implements Interactable {

    public CookiePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupCookie);
        pane.getChildren().add(this);

        setRandomPostition();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Picked up cookie.";
    }
}
