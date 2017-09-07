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

import java.util.LinkedList;
import java.util.Random;

public class GameLoop extends AnimationTimer {
    private final int maxNumOfInstances = 5;
    private final int maxNumOfEnemies = 4;
    private final int maxNumOfPowerUps = 4;

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
            countEnemies = 0;
            countPowerUps = 0;
            countCookies = 0;
            countPotholes = 0;
            countRainClowd = 0;
            nodog = true;
            nograndma = true;
            nocustomer = true;
            notricycle = true;


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

            Random tempNum = new Random();
            while (countEnemies < maxNumOfEnemies) {
                int enemyTospawn = tempNum.nextInt(maxNumOfEnemies);
                spawnEnemy(enemyTospawn);
            }

            while (countPowerUps < maxNumOfPowerUps) {
                int powerUpTospawn = tempNum.nextInt(maxNumOfPowerUps);
                spawnPowerUps(powerUpTospawn);
            }
            Globals.gameObjects.addAll(Globals.newGameObjects);
            Globals.newGameObjects.clear();

            Globals.gameObjects.removeAll(Globals.oldGameObjects);
            Globals.oldGameObjects.clear();
        }
    }

    private void spawnPowerUps(int powerUpTospawn) {
        switch (powerUpTospawn) {
            case 0:
            if (countCookies < maxNumOfInstances) {
                new CookiePowerup(Globals.game);
                    countCookies++;
                    countPowerUps++;
            }
            break;
            case 1:
            if (nograndma && nocustomer && Globals.countGrandmas < maxNumOfInstances) {
                new GrandmaPowerup(Globals.game);
                    nograndma = false;
                    Globals.countGrandmas++;
                    countPowerUps++;
            }
            break;
            case 2:
            if (nograndma && nocustomer && Globals.countCustomers < maxNumOfInstances) {
                new CustomerPowerup(Globals.game);
                    nocustomer = false;
                    Globals.countCustomers++;
                    countPowerUps++;
            }
            break;
            case 3:
            if (notricycle && Globals.countTricycles < maxNumOfInstances) {
                new TricyclePowerup(Globals.game);
                    notricycle = false;
                    Globals.countTricycles++;
                    countPowerUps++;
            }
            break;

        }
    }

    private void spawnEnemy(int enemyTospawn) {
        switch (enemyTospawn) {
            case 0:
                if (!Globals.playedCookieMonster) {
                    new CookieMonsterEnemy(Globals.game);
                    Globals.playedCookieMonster = true;
                    countEnemies++;
                }
                break;
            case 1:
                if (nodog && Globals.countDogs < maxNumOfInstances) {
                    new DogEnemy(Globals.game);
                    Globals.countDogs++;
                    nodog = false;
                    countEnemies++;
                }
                break;
            case 2:
                if (countPotholes < maxNumOfInstances) {
                    new PotHoleEnemy(Globals.game);
                    countPotholes++;
                    countEnemies++;
                }
                break;
            case 3:
                if (countRainClowd < maxNumOfInstances) {
                    new RainCloudEnemy(Globals.game);
                    countRainClowd++;
                    countEnemies++;
                }
                break;
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
