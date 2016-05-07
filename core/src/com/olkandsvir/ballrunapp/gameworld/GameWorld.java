package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * World of Ball and Rectangles.
 * @since 20.04.2016
 */
public class GameWorld {

    //игровые объекты
    private Ball ball;
    private ScrollHandler handler;

    //счет нынешней игры
    private int score;

    //состояние игры
    private GameState currentGameState;

    //фоновая музыка игры
    private Music backgroundMusic;
    private Music menuMusic;

    //стэк состояний игры
    private Stack<GameState> statesStack;

    //ТЕРБУЕТСЯ ПРОВЕРКА
    public ArrayList<Integer> listScore;

    /**
     * Перечисление состояний игры.
     */
    public enum GameState {
        MENU, OPTIONS, AUTHORS, READY, RUNNING, PAUSE, GAMEOVER, HIGHSCORE
    }

    //конструктор
    public GameWorld() {
        ball = new Ball(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
        this.handler = new ScrollHandler(this);
        this.backgroundMusic = AssetLoader.musicBackground;
        this.menuMusic = AssetLoader.musicMenu;
        score = 0;

        statesStack = new Stack<GameState>();
        listScore = new ArrayList<Integer>();
        currentGameState = GameState.MENU;
        statesStack.push(currentGameState);
        menuMusic.setLooping(true);
        backgroundMusic.setLooping(true);
        menuMusic.play();
    }


    //Вызывается для обновления игры
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

    /**
     * Не уверен в необходимости метода ...
     * @param delta
     */
    private void updateReady(float delta) {

    }

    /**
     * Обновляет игру после старта.
     * @param delta
     */
    private void updateRunning(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }

        //выполняется, когда игра не поставлена на паузу
        if(!isPaused()) {

            //обновляет прокручивателя барьеров
            handler.update(delta);

            //ПЕРЕНЕСТИ ОКОНЧАТЕЛЬНО В InputHandler
            //отвечает за передвижение мяча
            if (Gdx.input.isTouched() &&
                    !((Gdx.input.getX() > GameScreen.SCREEN_WIDTH * 4 / 5) && (Gdx.input.getY() < GameScreen.SCREEN_HEIGHT / 9))) {
                ball.onClick();
            }

            //проверяет столкновения
            if (handler.collides(ball)) {
                backgroundMusic.stop();
                handler.stop();
                currentGameState = GameState.GAMEOVER;
                statesStack.push(currentGameState);
                AssetLoader.setHighScore(score);
                if (score > AssetLoader.getHighScore()) {
                 //   AssetLoader.setHighScore(score);
                    currentGameState = GameState.HIGHSCORE;
                    AssetLoader.soundHighScore.play();
                } else {
                    AssetLoader.soundDefeat.play();
                }
                /* Проверка
                Gdx.app.log("1",AssetLoader.preferences.getString("resultsOne"));
                Gdx.app.log("2",AssetLoader.preferences.getString("resultsTwo"));
                Gdx.app.log("3",AssetLoader.preferences.getString("resultsThree"));
                Gdx.app.log("4",AssetLoader.preferences.getString("resultsFour"));
                Gdx.app.log("5",AssetLoader.preferences.getString("resultsFive"));*/

                menuMusic.play();
            }
        }
    }

    /**
     * Увеличение счета.
     */
    public void addScore() {
        score++;
    }

    /**
     * Переход в настройки.
     */
    public void goToOptions() {
        currentGameState = GameState.OPTIONS;
        statesStack.push(currentGameState);
        if(backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
            menuMusic.play();
        }
    }

    /**
     * Переход к готовности игры.
     */
    public void ready() {
        currentGameState = GameState.READY;
        statesStack.push(currentGameState);
    }

    /**
     * Старт игры, запуск барьеров.
     */
    public void start() {
        currentGameState = GameState.RUNNING;
        statesStack.push(currentGameState);
        menuMusic.stop();
        backgroundMusic.play();
    }

    /**
     * Ставит игру на паузу.
     */
    public void pause() {
        currentGameState = GameState.PAUSE;
        statesStack.push(currentGameState);
        backgroundMusic.pause();
        menuMusic.play();
    }

    /**
     * Продолжает игру после паузы.
     */
    public void resume() {
        back();
        menuMusic.stop();
        backgroundMusic.play();
    }

    /**
     * Начинает игру заново.
     */
    public void restart() {
        statesStack.pop();
        back();
        score = 0;
        ball.onRestart();
        handler.onRestart();
    }

    /**
     * Возвращается на состояние назад.
     */
    public void back() {
        statesStack.pop();
        currentGameState = statesStack.peek();
    }

    //Следующие методы проверяют состояние игры или получают какие-то поля мира.

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
