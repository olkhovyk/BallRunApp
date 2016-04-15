package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;

/**
 * Created by ilya on 15.04.2016.
 */
public class Ball {
    private Vector2 position;
    private Texture ball;
    public Ball(int x, int y) {
        position = new Vector2(x, y);
        ball = AssetsLoader.ball;
    }

    public Texture getBall() {
        return ball;
    }
    public Vector2 getPosition() {
        return position;
    }
}
