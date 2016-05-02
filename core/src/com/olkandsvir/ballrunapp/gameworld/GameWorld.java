package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.screens.GameScreen;

import java.util.Stack;

/**
 * World of Ball and Rectangles.
 * @since 20.04.2016
 */
public class GameWorld {

    private Ball ball;
    private ScrollHandler handler;

    private int score;

    private GameState currentGameState;

    private Music backgroundMusic;
    private Music menuMusic;

    private Stack<GameState> statesStack;

    public enum GameState {
        MENU, OPTIONS, AUTHORS, READY, RUNNING, PAUSE, GAMEOVER, HIGHSCORE
    }

    public GameWorld() {
        ball = new Ball(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
        this.handler = new ScrollHandler(this);
        this.backgroundMusic = AssetLoader.musicBackground;
        this.menuMusic = AssetLoader.musicMenu;
        score = 0;

        statesStack = new Stack<GameState>();

        currentGameState = GameState.MENU;
        statesStack.push(currentGameState);
        menuMusic.setLooping(true);
        backgroundMusic.setLooping(true);
        menuMusic.play();
    }

    public void update(float delta) {

        switch (currentGameState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
            case PAUSE:
                updateRunning(delta);
                break;

            default:
                break;
        }
    }

    private void updateReady(float delta) {

    }

    private void updateRunning(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }

        if(!isPaused()) {
            handler.update(delta);

            //ПЕРЕНЕСТИ ОКОНЧАТЕЛЬНО В InputHandler
            if (Gdx.input.isTouched() &&
                    !((Gdx.input.getX() > GameScreen.SCREEN_WIDTH * 4 / 5) && (Gdx.input.getY() < GameScreen.SCREEN_HEIGHT / 9))) {
                ball.onClick();
            }

            if (handler.collides(ball)) {
                backgroundMusic.stop();
                handler.stop();
                currentGameState = GameState.GAMEOVER;
                statesStack.push(currentGameState);

                if (score > AssetLoader.getHighScore()) {
                    AssetLoader.setHighScore(score);
                    currentGameState = GameState.HIGHSCORE;
                    AssetLoader.soundHighScore.play();
                } else {
                    AssetLoader.soundDefeat.play();
                }

                menuMusic.play();
            }
        }
    }

    public void addScore() {
        score++;
    }

    public void goToOptions() {
        currentGameState = GameState.OPTIONS;
        statesStack.push(currentGameState);
        if(backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
            menuMusic.play();
        }
    }

    public void ready() {
        currentGameState = GameState.READY;
        statesStack.push(currentGameState);
    }

    public void start() {
        currentGameState = GameState.RUNNING;
        statesStack.push(currentGameState);
        menuMusic.stop();
        backgroundMusic.play();
    }

    public void pause() {
        currentGameState = GameState.PAUSE;
        statesStack.push(currentGameState);
        backgroundMusic.pause();
        menuMusic.play();
    }

    public void resume() {
        back();
        menuMusic.stop();
        backgroundMusic.play();
    }

    public void restart() {
        statesStack.pop();
        back();
        score = 0;
        ball.onRestart();
        handler.onRestart();
    }

    public void back() {
        statesStack.pop();
        currentGameState = statesStack.peek();
    }

    public boolean isMenu() {
        return  currentGameState == GameState.MENU;
    }

    public boolean isOptions() {
        return currentGameState == GameState.OPTIONS;
    }

    public boolean isAuthors() {
        return currentGameState == GameState.AUTHORS;
    }

    public boolean isReady() {
        return currentGameState == GameState.READY;
    }

    public boolean isRunning() {
        return currentGameState == GameState.RUNNING;
    }

    public boolean isPaused() {
        return currentGameState == GameState.PAUSE;
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
