package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Players' representation in the game.
 * @since 15.04.2016
 */
public class Ball {
    private Vector2 position;
    private int diameter;

    private Circle boundingCircle;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
        this.diameter = GameScreen.SCREEN_WIDTH / 7;

        boundingCircle = new Circle(x, y + diameter / 2, diameter / 2);
    }

    /**
     * Двигаем мячик.
     */
    public void onClick(){
        float dx = Gdx.input.getX() - position.x;
        float dy = Gdx.input.getY() - position.y - diameter / 2;
        position.x += dx * 0.3;
        position.y += dy * 0.3;

        if(position.x <= diameter / 2){
            position.x = diameter / 2;
        }

        if(position.y <= 0){
            position.y = 0;
        }

        if (position.x >= GameScreen.SCREEN_WIDTH - diameter / 2){
            position.x = GameScreen.SCREEN_WIDTH - diameter / 2;
        }

        if(position.y >= GameScreen.SCREEN_HEIGHT - diameter){
            position.y = GameScreen.SCREEN_HEIGHT - diameter;
        }

        boundingCircle.setPosition(position.x, position.y + diameter / 2);
    }

    public void onRestart() {
        setPosition(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
    }

    private void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getDiameter() {
        return diameter;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

}
