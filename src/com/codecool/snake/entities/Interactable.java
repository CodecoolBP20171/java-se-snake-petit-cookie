package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.GirlScout;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(GirlScout girlScout);

    String getMessage();

}
