package com.codecool.snake;

import com.codecool.snake.entities.Dissapearable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        if (!Globals.paused) {
            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject instanceof Animatable) {
                    Animatable animObject = (Animatable) gameObject;
                    animObject.step();
                }
                if (gameObject instanceof GirlScout) {
                    GirlScout temp = (GirlScout) gameObject;
                    Globals.healthBar.setText("Health: " + temp.getHealth());
                }
                if (gameObject instanceof Dissapearable) {
                    Dissapearable dissapearableObject = (Dissapearable) gameObject;
                    dissapearableObject.step(now);
                }
            }
            Globals.gameObjects.addAll(Globals.newGameObjects);
            Globals.newGameObjects.clear();

            Globals.gameObjects.removeAll(Globals.oldGameObjects);
            Globals.oldGameObjects.clear();
        }
    }
}
