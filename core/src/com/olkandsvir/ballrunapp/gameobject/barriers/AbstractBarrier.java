package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Line moving down.
 * @since 26.04.2016
 */
public class AbstractBarrier {

    private Vector2 position;
    private Vector2 speed;
    private int height;
    private boolean scrolledBottom;
    protected Array<BarrierPart> parts;

    public AbstractBarrier(float x, float y, int height, float scrollSpeed){
        position = new Vector2(x, y);
        this.height = height;
        speed = new Vector2(0, scrollSpeed);
        scrolledBottom = false;
        parts = new Array<BarrierPart>();
    }

    public void update(float delta) {
        position.add(speed.cpy().scl(delta));

        if(position.y > GameScreen.SCREEN_HEIGHT) {
            scrolledBottom = true;
        }

        for(BarrierPart part : parts) {
            part.update(delta);
        }
    }

    public boolean isScrolledBottom() {
        return scrolledBottom;
    }

    public void moveToLast(float newY) {
        this.position.y = (int)newY;
        scrolledBottom = false;

        for(BarrierPart part : parts) {
            part.newOrientation();
        }
    }

    public void stop() {
        speed.y = 0;
    }

    public void speedChange(int increment) {
        speed.y += increment;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collides(Ball ball) {
        for (BarrierPart part : parts) {
            if (part.collides(ball)) {
                return true;
            }
        }

        return false;
    }

    public Array<BarrierPart> getParts() {
        return parts;
    }
}
