package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * World of Ball and Rectangles.
 * @since 20.04.2016
 */
public class GameWorld {

    private Ball ball;
    private ScrollHandler handler;

    private int score;

    //ДЛЯ ТЕСТОВ!
    public static int collisions = 0;

    private GameState currentGameState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld() {
        ball = new Ball(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
        this.handler = new ScrollHandler(this);
        score = 0;

        //ПОСЛЕ ТЕСТОВ ВЕРНУТЬ!
//        currentGameState = GameState.READY;

        //ДЛЯ ТЕСТОВ!
        currentGameState = GameState.RUNNING;
    }

    public void update(float delta) {

        switch (currentGameState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            default:
                break;
        }
    }

    private void updateReady(float delta) {

    }

    public void updateRunning(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }

        handler.update(delta);

        //Перемещение шарика по касанию
        if(Gdx.input.isTouched()){
            ball.onClick();
        }

        if(handler.collides(ball)) {

            //ДЛЯ ТЕСТОВ!
            collisions++;
            Gdx.app.log("Collided", Integer.toString(collisions));
        }

    }

    public void addScore() {
        score++;

        //ДЛЯ ТЕСТОВ!
        Gdx.app.log("Score", Integer.toString(score));
    }

    public boolean isReady() {
        return currentGameState == GameState.READY;
    }

    public boolean isRunning() {
        return currentGameState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentGameState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentGameState == GameState.HIGHSCORE;
    }

    public Ball getBall() {
        return ball;
    }

    public ScrollHandler getHandler() {
        return handler;
    }

    public int getScore() {
        return score;
    }
}
