package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class RainCloudEnemy extends GameEntity implements Interactable, Dissapearable {

    private long deathTime;
    public RainCloudEnemy (Pane pane) {

        super(pane);
        deathTime = System.nanoTime() + (long)1E10;

        setImage(Globals.rainCloudEnemy);
        pane.getChildren().add(this);

        setRandomPostition();

    }
    @Override
    public void step(long now) {
        if(deathTime <= now) destroy();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(1);
        snakeHead.changeHealth(-10);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Lost 1 cookie and got cold(-10 health)";
    }
}
