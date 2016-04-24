package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
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

    private GameState currentGameState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld() {
        ball = new Ball(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
        this.handler = new ScrollHandler(this);
        score = 0;

        currentGameState = GameState.MENU;
    }

    public void update(float delta) {

        switch (currentGameState) {
            case READY:
            case MENU:
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

        //ПЕРЕНЕСТИ ОКОНЧАТЛЕЬНО В InputHandler
        if(Gdx.input.isTouched()) {
            ball.onClick();
        }

        if(handler.collides(ball)) {
            handler.stop();
            currentGameState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentGameState = GameState.HIGHSCORE;
            }
        }
    }

    public void addScore() {
        score++;
    }

    public void ready() {
        currentGameState = GameState.READY;
    }

    public void start() {
        currentGameState = GameState.RUNNING;
    }

    public void restart() {
        currentGameState = GameState.READY;
        score = 0;
        ball.onRestart();
        handler.onRestart();
    }

    public boolean isMenu() {
        return  currentGameState == GameState.MENU;
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
