package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class TricyclePowerup extends GameEntity implements Interactable {

    private final int SPEEDGAIN = 1;

    public TricyclePowerup(Pane pane){
        super(pane);
        setImage(Globals.powerupTricycle);
        pane.getChildren().add(this);

        setRandomPostition();
    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeSpeed(SPEEDGAIN);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Pedal away.";
    }
}
