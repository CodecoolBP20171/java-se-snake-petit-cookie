package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;
    public static final double MIN_SPAWN_GAP = 20;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupCookie = new Image("powerup_cookie.png");
    public static Image powerupGrandma = new Image("powerup_grandma.png");
    public static Image powerupCustomer = new Image("powerup_customer.png");
    public static Image powerupTricycle = new Image("powerup_tricycle.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean paused;
    public static GameEntity snakeHeadEntity;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static GameEntity getSnakeHead() {
        for(GameEntity entity : gameObjects){
            if(entity instanceof SnakeHead) {
                return entity;
            }
        }
        return null;
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
