package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Enemies;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.PlayerFollowerGameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class CookieMonsterEnemy extends PlayerFollowerGameEntity implements Interactable, Animatable, Enemies{

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
        Point2D heading = getHeading(DANGERDISTANCE);
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
