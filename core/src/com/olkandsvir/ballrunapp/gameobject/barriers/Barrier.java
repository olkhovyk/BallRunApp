package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

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
        this.part = new BarrierPart(y, height);
        this.speed = new Vector2(0, scrollSpeed);
        scrolledBottom = false;
    }

    public void update(float delta) {
        position.add(speed.cpy().scl(delta));

        if(position.y > GameRenderer.GAME_HEIGHT) {
            scrolledBottom = true;
        }
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

    public boolean isScrolledBottom() {
        return scrolledBottom;
    }
}
