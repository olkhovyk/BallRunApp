package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.BallRunGame;

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
        position.x +=dx * 0.3;
        position.y +=dy * 0.3;
        position.set(getPosition().x, getPosition().y);

        if(getPosition().x <= 0 + diameter / 2){
            position.x = 0 + diameter / 2;
        }

        if(getPosition().y <= 0 - diameter / 2){
            position.y = 0;
        }

        if (getPosition().x >= BallRunGame.WIDTH - diameter/2){
            position.x = BallRunGame.WIDTH - diameter/2;
        }

        if(getPosition().y >= BallRunGame.HEIGHT - diameter){
            position.y = BallRunGame.HEIGHT - diameter;
        }

    }
}
