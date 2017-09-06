package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.control.Label;
import javafx.geometry.Point2D;
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
    public static Image potHoleEnemy = new Image("enemy_pothole.png");
    public static Image rainCloudEnemy = new Image("enemy_raincloud.png");
    public static Image dogEnemy = new Image("enemy_dog.png");
    public static Image cookieMonsterEnemy = new Image("enemy_cookiemonster.png");
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

    public static Label healthBar;
    public static Label endGameLabel;
    public static Label infoPanel;
    public static Label snakeLength;

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

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static double distanceFromSnakeHead(GameEntity entity) {
        double a = Math.abs(entity.getX() - snakeHeadEntity.getX());
        double b = Math.abs(entity.getY() - snakeHeadEntity.getY());

        return Math.sqrt(a*a + b*b);
    }

    public static Point2D vectorToSnakeHead(GameEntity entity) {
        return new Point2D(snakeHeadEntity.getX() - entity.getX(), snakeHeadEntity.getY() - entity.getY());
    }
}
