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
    private Vector2 horizontalSpeed;
    private Rectangle rectangle;
    private boolean moving;
    private boolean toTheRight;

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

        horizontalSpeed = new Vector2(0, 0);
        moving = false;
        toTheRight = false;
    }

    public void update(float delta) {
        position.y = barrier.getPosition().y;
        rectangle.set(position.x, position.y, width, height);
        if(moving && position.y > 0) {
            moveHorizontally(delta, toTheRight);
        }
    }

    public boolean collides(Ball ball) {
        return (Intersector.overlaps(ball.getBoundingCircle(), rectangle));
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

        horizontalSpeed.x = 0;
        moving = false;
        toTheRight = false;
    }

    public void moveHorizontally(float delta, boolean toTheRight) {
        //как определить скорость?
        horizontalSpeed.x = barrier.getSpeed().y * GameScreen.SCREEN_WIDTH / 750000 * GameScreen.SCREEN_HEIGHT;

        if(orientation == partOrientation.LEFT) {
            moveLeftPart(delta);
        } else if (orientation == partOrientation.MID) {
            moveMidPart(delta, toTheRight);
        } else if (orientation == partOrientation.RIGHT) {
            moveRightPart(delta);
        }
    }

    public void moveLeftPart(float delta) {
        if (position.x < GameScreen.SCREEN_WIDTH / 3) {
            position.add(horizontalSpeed.cpy().scl(delta));
        }
    }

    public void moveMidPart(float delta, boolean toTheRight) {
        if (toTheRight && position.x < 2 * GameScreen.SCREEN_WIDTH / 3) {
            position.add(horizontalSpeed.cpy().scl(delta));
        } else if (position.x > 0) {
            horizontalSpeed.x = - horizontalSpeed.x;
            position.add(horizontalSpeed.cpy().scl(delta));
        }
    }

    public void moveRightPart(float delta) {
        if (position.x > GameScreen.SCREEN_WIDTH / 3) {
            horizontalSpeed.x = - horizontalSpeed.x;
            position.add(horizontalSpeed.cpy().scl(delta));
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setMoving(boolean moving, boolean toTheRight) {
        this.moving = moving;
        this.toTheRight = toTheRight;
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
