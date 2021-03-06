package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.Enemies;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.scene.layout.Pane;

public class RainCloudEnemy extends GameEntity implements Interactable, Dissapearable, Enemies {

    private long deathTime;
    private final int HEALTHLOSS = 10;
    private final int COOKIELOSS = 1;
    // 10 seconds (in nano seconds)
    private final long LIFETIME = (long)1E10;

    public RainCloudEnemy (Pane pane) {
        super(pane);
        deathTime = System.nanoTime() + LIFETIME;
        setImage(Globals.rainCloudEnemy);
        pane.getChildren().add(this);
        setRandomPostition();
    }

    @Override
    public void step(long now) {
        if(deathTime <= now) destroy();
    }

    @Override
    public void apply(GirlScout girlScout) {
        girlScout.removePart(COOKIELOSS);
        girlScout.changeHealth(-HEALTHLOSS);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Lost 1 cookie and got cold(-10 health)";
    }
}
