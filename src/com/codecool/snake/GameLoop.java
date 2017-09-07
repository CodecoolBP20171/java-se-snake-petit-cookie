package com.codecool.snake;

import com.codecool.snake.entities.*;
import com.codecool.snake.entities.enemies.CookieMonsterEnemy;
import com.codecool.snake.entities.enemies.DogEnemy;
import com.codecool.snake.entities.enemies.PotHoleEnemy;
import com.codecool.snake.entities.enemies.RainCloudEnemy;
import com.codecool.snake.entities.powerups.CookiePowerup;
import com.codecool.snake.entities.powerups.CustomerPowerup;
import com.codecool.snake.entities.powerups.GrandmaPowerup;
import com.codecool.snake.entities.powerups.TricyclePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

import java.util.Random;

public class GameLoop extends AnimationTimer {
    private final int MAX_NUM_OF_INSTANCES = 5;
    private final int MAX_NUM_OF_ENEMIES = 4;
    private final int MAX_NUM_OF_POWERUPS = 4;

    private int countEnemies;
    private int countPowerUps;
    private int countCookies;
    private int countPotholes;
    private int countRainClowd;
    private boolean nodog;
    private boolean nograndma;
    private boolean nocustomer;
    private boolean notricycle;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {

        if (!Globals.paused && !Globals.gameOver) {
            initVariables();
            checkEntities(now);
            spawnMissingEntities();

            Globals.gameObjects.addAll(Globals.newGameObjects);
            Globals.newGameObjects.clear();

            Globals.gameObjects.removeAll(Globals.oldGameObjects);
            Globals.oldGameObjects.clear();
        }
    }

    private void spawnMissingEntities() {
        Random tempNum = new Random();
        while (countEnemies < MAX_NUM_OF_ENEMIES) {
            int enemyTospawn = tempNum.nextInt(MAX_NUM_OF_ENEMIES);
            spawnEnemy(enemyTospawn);
        }

        while (countPowerUps < MAX_NUM_OF_POWERUPS) {
            int powerUpTospawn = tempNum.nextInt(MAX_NUM_OF_POWERUPS);
            spawnPowerUps(powerUpTospawn);
        }
    }

    private void checkEntities(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable) gameObject;
                animObject.step();
            }
            if (gameObject instanceof SnakeHead) {
                SnakeHead temp = (SnakeHead) gameObject;
                Globals.healthBar.setText("Health: " + temp.getHealth());
            }
            if (gameObject instanceof Dissapearable) {
                Dissapearable dissapearableObject = (Dissapearable) gameObject;
                dissapearableObject.step(now);
            }
            if (gameObject instanceof Enemies) {
                countEnemies++;
                countEnemies(gameObject);
            }
            if (gameObject instanceof PowerUps) {
                countPowerUps++;
                countPowerUps(gameObject);
            }
        }
    }

    private void initVariables() {
        countEnemies = 0;
        countPowerUps = 0;
        countCookies = 0;
        countPotholes = 0;
        countRainClowd = 0;
        nodog = true;
        nograndma = true;
        nocustomer = true;
        notricycle = true;
    }

    private void spawnPowerUps(int powerUpTospawn) {
        switch (powerUpTospawn) {
            case 0:
                spawnCookiePowerUp();
                break;
            case 1:
                spawnGrandma();
                break;
            case 2:
                spawnCustomer();
                break;
            case 3:
                spawnTricycle();
                break;

        }
    }

    private void spawnTricycle() {
        if (notricycle && Globals.countTricycles < MAX_NUM_OF_INSTANCES) {
            new TricyclePowerup(Globals.game);
                notricycle = false;
                Globals.countTricycles++;
                countPowerUps++;
        }
    }

    private void spawnCustomer() {
        if (nograndma && nocustomer && Globals.countCustomers < MAX_NUM_OF_INSTANCES) {
            new CustomerPowerup(Globals.game);
                nocustomer = false;
                Globals.countCustomers++;
                countPowerUps++;
        }
    }

    private void spawnGrandma() {
        if (nograndma && nocustomer && Globals.countGrandmas < MAX_NUM_OF_INSTANCES) {
            new GrandmaPowerup(Globals.game);
                nograndma = false;
                Globals.countGrandmas++;
                countPowerUps++;
        }
    }

    private void spawnCookiePowerUp() {
        if (countCookies < MAX_NUM_OF_INSTANCES) {
            new CookiePowerup(Globals.game);
                countCookies++;
                countPowerUps++;
        }
    }

    private void spawnEnemy(int enemyTospawn) {
        switch (enemyTospawn) {
            case 0:
                spawnCookieMonster();
                break;
            case 1:
                spawnDog();
                break;
            case 2:
                spawnPotHole();
                break;
            case 3:
                spawnRainCloud();
                break;
        }
    }

    private void spawnRainCloud() {
        if (countRainClowd < MAX_NUM_OF_INSTANCES) {
            new RainCloudEnemy(Globals.game);
            countRainClowd++;
            countEnemies++;
        }
    }

    private void spawnPotHole() {
        if (countPotholes < MAX_NUM_OF_INSTANCES) {
            new PotHoleEnemy(Globals.game);
            countPotholes++;
            countEnemies++;
        }
    }

    private void spawnDog() {
        if (nodog && Globals.countDogs < MAX_NUM_OF_INSTANCES) {
            new DogEnemy(Globals.game);
            Globals.countDogs++;
            nodog = false;
            countEnemies++;
        }
    }

    private void spawnCookieMonster() {
        if (!Globals.playedCookieMonster) {
            new CookieMonsterEnemy(Globals.game);
            Globals.playedCookieMonster = true;
            countEnemies++;
        }
    }

    private void countPowerUps(GameEntity gameObject) {
        if (gameObject instanceof CookiePowerup) {
            countCookies++;
        }
        if (gameObject instanceof GrandmaPowerup) {
            nograndma = false;
            Globals.countGrandmas++;
        }
        if (gameObject instanceof CustomerPowerup) {
            nocustomer = false;
            Globals.countCustomers++;
        }
        if (gameObject instanceof TricyclePowerup) {
            notricycle = false;
            Globals.countTricycles++;
        }
    }

    private void countEnemies(GameEntity gameObject) {
        if (gameObject instanceof CookieMonsterEnemy) {
            Globals.playedCookieMonster = true;
        }
        if (gameObject instanceof DogEnemy) {
            nodog = false;
            Globals.countDogs++;
        }
        if (gameObject instanceof PotHoleEnemy) {
            countPotholes++;
        }
        if (gameObject instanceof RainCloudEnemy) {
            countRainClowd++;
        }
    }
}
