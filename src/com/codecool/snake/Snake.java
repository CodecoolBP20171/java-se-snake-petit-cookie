package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Snake extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Globals.game = game;

        initHealthBar(game);
        initEndGameLabels(game);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }

    private void initHealthBar(Game game) {
        Globals.healthBar = new Label("Health: " + ((SnakeHead) Globals.snakeHeadEntity).getHealth());
        game.getChildren().add(Globals.healthBar);
        Globals.healthBar.setLayoutX(30);
        Globals.healthBar.setLayoutY(30);
        Globals.healthBar.setFont(Font.font("PT Sans Narrow", 20));
        Globals.healthBar.setTextFill(Color.web("#6A3602"));
        Globals.healthBar.setVisible(true);
    }

    private void initEndGameLabels(Game game) {
        Globals.endGameLabel = new Label("Game Over");
        Globals.infoPanel = new Label("Press R to restart, Q to quit game");
        Globals.snakeLength = new Label();
        game.getChildren().add(Globals.endGameLabel);
        game.getChildren().add(Globals.infoPanel);
        game.getChildren().add(Globals.snakeLength);
        Globals.endGameLabel.setLayoutX(Globals.WINDOW_WIDTH / 2 - 125);
        Globals.endGameLabel.setLayoutY(Globals.WINDOW_HEIGHT / 2);
        Globals.endGameLabel.setFont(Font.font("PT Sans Narrow", 40));
        Globals.endGameLabel.setTextFill(Color.web("#6A3602"));
        Globals.endGameLabel.setVisible(false);
        Globals.infoPanel.setLayoutX(Globals.WINDOW_WIDTH - 350);
        Globals.infoPanel.setLayoutY(Globals.WINDOW_HEIGHT- 50);
        Globals.infoPanel.setFont(Font.font("PT Sans Narrow", 20));
        Globals.infoPanel.setTextFill(Color.web("#6A3602"));
        Globals.infoPanel.setVisible(true);
        Globals.snakeLength.setLayoutX(Globals.WINDOW_WIDTH / 2 - 300);
        Globals.snakeLength.setLayoutY(Globals.WINDOW_HEIGHT / 2 + 50);
        Globals.snakeLength.setFont(Font.font("PT Sans Narrow", 30));
        Globals.snakeLength.setTextFill(Color.web("#6A3602"));
        Globals.snakeLength.setVisible(false);
    }

}
