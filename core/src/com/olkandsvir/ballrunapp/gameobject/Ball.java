package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Players' representation in the game.
 * @since 15.04.2016
 */
public class Ball {
    private Vector2 position;
    private int diameter;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
        this.diameter = GameRenderer.GAME_WIDTH / 6;

    }

    public Vector2 getPosition() {
        return position;

    }

    public int getDiameter() {
        return diameter;

    }

    //Метод, изменяющий позицию шарика
    public void move(){
        float dx = Gdx.input.getX() - getPosition().x;
        float dy = Gdx.input.getY() - getPosition().y - getDiameter() / 2;
        position.x += dx * 0.3;
        position.y += dy * 0.3;

        if(getPosition().x <= 0 - diameter){
            position.x = 0 - diameter;
        }

        if(getPosition().y <= 0){
            position.y = 0;
        }

        if (getPosition().x >= GameRenderer.GAME_WIDTH - diameter / 2){
            position.x = GameRenderer.GAME_WIDTH - diameter / 2;
        }

        if(getPosition().y >= GameRenderer.GAME_HEIGHT - diameter){
            position.y = GameRenderer.GAME_HEIGHT - diameter;
        }

    }
}
