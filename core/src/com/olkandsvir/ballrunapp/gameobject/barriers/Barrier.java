package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Vector2;

/**
 * Lines with obstacles.
 * @since 17.04.2016
 */
public class Barrier {

    private BarrierPart part;
    private Vector2 position;
    private int height;

    public Barrier(float x, float y, int height){
        position = new Vector2(x, y);
        this.height = height;
        this.part = new BarrierPart(y, height);

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
