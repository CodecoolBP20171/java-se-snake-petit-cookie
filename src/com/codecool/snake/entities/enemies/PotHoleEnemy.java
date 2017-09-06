package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

// a simple enemy TODO make better ones.
public class PotHoleEnemy extends GameEntity implements Interactable, Dissapearable {

    private long deathTime;

    public PotHoleEnemy(Pane pane) {
        super(pane);
        deathTime = System.nanoTime() + (long)5E9;

        setImage(Globals.potHoleEnemy);
        pane.getChildren().add(this);

        setRandomPostition();
    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(1);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Lost one cookie";
    }

    @Override
    public void step(long now) {
        if(deathTime <= now) destroy();
    }
}
