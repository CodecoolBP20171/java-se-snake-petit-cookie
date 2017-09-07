package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.scene.layout.Pane;

public class PotHoleEnemy extends GameEntity implements Interactable, Dissapearable {

    private long deathTime;
    private final int COOKIELOSS = 1;
    // 5 seconds (in nano seconds)
    private final long LIFETIME = (long)5E9;

    public PotHoleEnemy(Pane pane) {
        super(pane);
        deathTime = System.nanoTime() + LIFETIME;

        setImage(Globals.potHoleEnemy);
        pane.getChildren().add(this);

        setRandomPostition();
    }


    @Override
    public void apply(GirlScout girlScout) {
        girlScout.removePart(COOKIELOSS);
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
