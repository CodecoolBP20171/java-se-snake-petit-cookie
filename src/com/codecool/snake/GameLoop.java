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

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        if (!Globals.paused && !Globals.gameOver) {
            int countEnemies = 0;
            int countPowerUps = 0;
            int countCookies = 0;
            int countPotholes = 0;
            int countRainClowd = 0;
            final int maxNumOfInstances = 5;
            final int maxNumOfEnemies = 4;
            final int maxNumOfPowerUps = 4;
            boolean nodog = true;
            boolean nograndma = true;
            boolean nocustomer = true;
            boolean notricycle = true;


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
                if (gameObject instanceof PowerUps) {
                    countPowerUps++;
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
            }

            Random tempNum = new Random();
            while (countEnemies < maxNumOfEnemies) {
                int enemyTospawn = tempNum.nextInt(maxNumOfEnemies);
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

            while (countPowerUps < maxNumOfPowerUps) {
                int powerUpTospawn = tempNum.nextInt(maxNumOfPowerUps);
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
            Globals.gameObjects.addAll(Globals.newGameObjects);
            Globals.newGameObjects.clear();

            Globals.gameObjects.removeAll(Globals.oldGameObjects);
            Globals.oldGameObjects.clear();
        }
    }
}
