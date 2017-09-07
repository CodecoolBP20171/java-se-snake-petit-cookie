package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.CookieMonsterEnemy;
import com.codecool.snake.entities.enemies.DogEnemy;
import com.codecool.snake.entities.enemies.PotHoleEnemy;
import com.codecool.snake.entities.enemies.RainCloudEnemy;
import com.codecool.snake.entities.powerups.CookiePowerup;
import com.codecool.snake.entities.powerups.CustomerPowerup;
import com.codecool.snake.entities.powerups.GrandmaPowerup;
import com.codecool.snake.entities.powerups.TricyclePowerup;
import com.codecool.snake.entities.snakes.GirlScout;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Pane {

    public Game() {
        init();
    }

    private void init() {
        new GirlScout(this, 500, 500);

        new PotHoleEnemy(this);
        new CookieMonsterEnemy(this);
        new RainCloudEnemy(this);
        new DogEnemy(this);

        new CookiePowerup(this);
        new CustomerPowerup(this);
        new GrandmaPowerup(this);
        new TricyclePowerup(this);
    }


    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = true;
                    if (Globals.infoPanel.isVisible()) {
                        Globals.infoPanel.setVisible(false);
                    }
                    break;
                case RIGHT:
                    Globals.rightKeyDown = true;
                    if (Globals.infoPanel.isVisible()) {
                        Globals.infoPanel.setVisible(false);
                    }
                    break;
                case P:
                    Globals.paused = !Globals.paused;
                    break;
                case R:
                    restart();
                    break;
                case Q:
                    Stage primaryStage = (Stage) getScene().getWindow();
                    primaryStage.close();
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = false;
                    break;
                case RIGHT:
                    Globals.rightKeyDown = false;
                    break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    private void restart() {
        for (GameEntity gameEntity : Globals.gameObjects) {
            gameEntity.destroy();
        }
        init();
        Globals.healthBar.setVisible(true);
        Globals.endGameLabel.setVisible(false);
        Globals.snakeLength.setVisible(false);
    }

}
