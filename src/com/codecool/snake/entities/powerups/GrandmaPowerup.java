package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GrandmaPowerup extends GameEntity implements Interactable {
    public GrandmaPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupGrandma);
        pane.getChildren().add(this);

        GameEntity snakeHead = Globals.snakeHeadEntity;
        Random rnd = new Random();
        do {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        } while (Math.abs(getX() - snakeHead.getX()) < Globals.MIN_SPAWN_GAP);
        do {
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        } while (Math.abs(getY() - snakeHead.getY()) < Globals.MIN_SPAWN_GAP);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(2);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Picked up multiple cookies.";
    }
}
