package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class PotHoleEnemy extends GameEntity implements Interactable, Dissapearable {

    private long deathTime;

    public PotHoleEnemy(Pane pane) {
        super(pane);
        deathTime = System.nanoTime() + (long)5E9;

        setImage(Globals.potHoleEnemy);
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
