package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Lines with obstacles.
 * @since 17.04.2016
 */
public class Barrier {

    private BarrierPart part;
    private Vector2 position;
    private Vector2 speed;
    private int height;
    private boolean scrolledBottom;

    public Barrier(float x, float y, int height, float scrollSpeed){
        position = new Vector2(x, y);
        this.height = height;
        this.speed = new Vector2(0, scrollSpeed);
        this.part = new BarrierPart(y, height, speed);
        scrolledBottom = false;
    }

    public void update(float delta) {
        position.add(speed.cpy().scl(delta));
        part.update(delta);

        if(position.y > GameScreen.SCREEN_HEIGHT) {
            scrolledBottom = true;
        }
    }

    public boolean isScrolledBottom() {
        return scrolledBottom;
    }

    public boolean collides(Ball ball) {
        return part.collides(ball);
    }

    public void stop() {
        speed.y = 0;
    }

    public void speedUp(int increment) {
        speed.y += increment;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public BarrierPart getPart() {
        return part;
    }

}
