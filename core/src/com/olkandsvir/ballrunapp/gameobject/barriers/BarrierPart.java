package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * A dangerous part of Barriers.
 * @since 17.04.2016
 */
public class BarrierPart {

    private partOrientation orientation;
    private int width;
    private int height;
    private Vector2 position;

    public enum partOrientation {
        LEFT, MID, RIGHT
    }

    public BarrierPart(float y, int height) {
        double random = Math.random() * 3;
        if(random < 1.0) {
            orientation = partOrientation.LEFT;
        } else if (random < 2.0) {
            orientation = partOrientation.MID;
        } else if (random <= 3.0) {
            orientation = partOrientation.RIGHT;
        }

        width = GameRenderer.GAME_WIDTH / 3;
        this.height = height;

        if (orientation == partOrientation.LEFT) {
            position = new Vector2(0, y);
        } else if (orientation == partOrientation.MID) {
            position = new Vector2(GameRenderer.GAME_WIDTH / 3, y);
        } else {
            position = new Vector2(2 * GameRenderer.GAME_WIDTH / 3, y);
        }
    }

    public int getWidth() {
        return width;
    }

    public float getX() {
        return position.x;
    }

}
