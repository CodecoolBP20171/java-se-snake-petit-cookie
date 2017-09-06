package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.RandomMoveableGameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class CookieMonsterEnemy extends RandomMoveableGameEntity implements Interactable, Animatable{

    private final int FRAMESINONEDIRECTION = 30;
    private final int COOKIELOSS = 4;
    private final int DANGERDISTANCE = 200;

    public CookieMonsterEnemy(Pane pane) {
        super(pane);
        speed = 1.8;
        setImage(Globals.cookieMonsterEnemy);
        pane.getChildren().add(this);

        setRandomPostition();
    }

    @Override
    public void step() {
        double distance = Globals.distanceFromSnakeHead(this);
        Point2D heading;
        if (distance < DANGERDISTANCE) {
            double ratio = speed / distance;

            heading = Globals.vectorToSnakeHead(this);
            heading = heading.multiply(ratio);
        } else {
            heading = Utils.getRandomMoveVector(this);
        }
        moveEntity(heading);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(COOKIELOSS);
        destroy();
    }

    @Override
    public String getMessage() {
        return "The cookie monster has taken your cookie";
    }

    @Override
    public int getMaxCounter() {
        return FRAMESINONEDIRECTION;
    }
}