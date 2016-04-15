package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.math.Vector2;

/**
 * Players' representation in the game.
 * @since 15.04.2016
 */
public class Ball {

    private Vector2 position;
    private int diameter;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
        this.diameter = 75;

    }

    public Vector2 getPosition() {
        return position;

    }

    public int getDiameter() {
        return diameter;
    }
}
