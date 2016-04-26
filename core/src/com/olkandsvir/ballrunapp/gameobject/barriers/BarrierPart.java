package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * A dangerous part of Barriers.
 * @since 17.04.2016
 */
public class BarrierPart {

    private partOrientation orientation;
    private AbstractBarrier barrier;
    private int width;
    private int height;
    private Vector2 position;
    private Rectangle rectangle;

    public enum partOrientation {
        LEFT, MID, RIGHT
    }

    public BarrierPart(AbstractBarrier barrier, float y, int height) {
        double random = Math.random() * 3;
        if(random < 1.0) {
            orientation = partOrientation.LEFT;
        } else if (random < 2.0) {
            orientation = partOrientation.MID;
        } else if (random <= 3.0) {
            orientation = partOrientation.RIGHT;
        }

        width = GameScreen.SCREEN_WIDTH / 3;
        this.barrier = barrier;
        this.height = height;

        if (orientation == partOrientation.LEFT) {
            position = new Vector2(0, y);
        } else if (orientation == partOrientation.MID) {
            position = new Vector2(GameScreen.SCREEN_WIDTH / 3, y);
        } else {
            position = new Vector2(2 * GameScreen.SCREEN_WIDTH / 3, y);
        }

        rectangle = new Rectangle(position.x, position.y, width, height);
    }

    public void update(float delta) {
        position.y = barrier.getPosition().y;
        rectangle.set(position.x, position.y, width, height);
    }

    public boolean collides(Ball ball) {
        return (/*position.x < ball.getPosition().x + ball.getDiameter() || position.x + width > ball.getPosition().x
                && position.y > ball.getPosition().y + ball.getDiameter() || position.y + height < ball.getPosition().y)
                && (*/Intersector.overlaps(ball.getBoundingCircle(), rectangle));
    }

    public void newOrientation() {
        double random = Math.random() * 3;
        if(random < 1.0) {
            orientation = partOrientation.LEFT;
        } else if (random < 2.0) {
            orientation = partOrientation.MID;
        } else if (random <= 3.0) {
            orientation = partOrientation.RIGHT;
        }

        if (orientation == partOrientation.LEFT) {
            position.x = 0;
        } else if (orientation == partOrientation.MID) {
            position.x = GameScreen.SCREEN_WIDTH / 3;
        } else {
            position.x = 2 * GameScreen.SCREEN_WIDTH / 3;
        }
    }

    public int getWidth() {
        return width;
    }

    public float getX() {
        return position.x;
    }

    public partOrientation getOrientation() {
        return orientation;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
