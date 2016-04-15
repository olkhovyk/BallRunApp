package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.math.Vector2;

public class Ball {

    private Vector2 position;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
    }

/*    public Texture getBall() {
        return ball;
    }
*/
    public Vector2 getPosition() {
        return position;
    }
}
