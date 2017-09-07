package com.codecool.snake.entities.snakes;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LittleRedWagon extends GameEntity implements Animatable {

    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 10;

    public LittleRedWagon(Pane pane, GameEntity parent) {
        super(pane);
        this.parent = parent;
        setImage(Globals.snakeBody);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        Image image = getImage();
        setTranslateX(-image.getWidth() / 2);
        setTranslateY(-image.getHeight() / 2);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public void step() {
        setRotate(Utils.getDirectionToOtherEntity(this, parent));
        Vec2d pos = history.poll(); // remove the oldest item from the history
        setX(pos.x);
        setY(pos.y);
        translateXProperty();
        translateYProperty();
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
    }

    public GameEntity getGameParent() {
        return parent;
    }
}
