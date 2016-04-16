package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.BallRunGame;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Players' representation in the game.
 * @since 15.04.2016
 */
public class Ball {
    GameRenderer renderer;
    private Vector2 position;
    private int diameter;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
        this.diameter = GameRenderer.W / 5;

    }

    public Vector2 getPosition() {
        return position;

    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getDiameter() {
        return diameter;
    }

    //Метод, изменяющий позицию шарика
    public void move(){
        float dx = Gdx.input.getX() - getPosition().x;
        float dy = Gdx.input.getY() - getPosition().y;
        position.x += dx * 0.3;
        position.y += dy * 0.3;

        if(getPosition().x <= 0 - diameter){
            position.x = 0 - diameter;
        }

        if(getPosition().y <= 0){
            position.y = 0;
        }

        if (getPosition().x >= GameRenderer.W - diameter / 2){
            position.x = GameRenderer.W - diameter / 2;
        }

        if(getPosition().y >= GameRenderer.H - diameter){
            position.y = GameRenderer.H - diameter;
        }

    }
}
