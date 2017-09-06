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

        initHealthBar(game);
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
    }

}
