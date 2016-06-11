package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.brhelpers.SoundHandler;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.screens.GameScreen;

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

    /**
     * Перечисление состояний игры.
     */
    public enum GameState {
        MENU, OPTIONS, BEST_RESULTS, AUTHORS, READY, RUNNING, PAUSE, GAME_OVER, HIGH_SCORE
    }

    //конструктор
    public GameWorld() {
        ball = new Ball(GameScreen.SCREEN_WIDTH / 2, (int) (GameScreen.SCREEN_HEIGHT /1.2));
        handler = new ScrollHandler(this);
        backgroundMusic = SoundHandler.getBackgroundMusic();
        menuMusic = SoundHandler.getMenuMusic();

        score = 0;

        statesStack = new Stack<GameState>();
        currentGameState = GameState.MENU;
        statesStack.push(currentGameState);
        SoundHandler.setLooping(backgroundMusic, true);
        SoundHandler.setLooping(menuMusic, true);
        SoundHandler.setMusicVolume();
        SoundHandler.playMusic(menuMusic);
    }

    /**
     * Вызывается для обновления игры.
     * @param delta проверяет время после последнего обновления
     */
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
     * @param delta проверяет время после последнего обновления
     */
    private void updateReady(float delta) {

    }

    /**
     * Обновляет игру после старта.
     * @param delta проверяет время после последнего обновления
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
                    !((Gdx.input.getX() > GameScreen.SCREEN_WIDTH * 3 / 4) && (Gdx.input.getY() < GameScreen.SCREEN_HEIGHT / 8))) {
                ball.onClick();
            }

            //проверяет столкновения
            if (handler.collides(ball)) {
                SoundHandler.stopMusic(backgroundMusic);

                handler.stop();
                currentGameState = GameState.GAME_OVER;
                statesStack.push(currentGameState);

                if (score > AssetLoader.getLastHighScore()) {
                    AssetLoader.setHighScore(score);
                    currentGameState = GameState.HIGH_SCORE;
                    SoundHandler.playSoundHighScore();

                } else {
                    SoundHandler.playSoundDefeat();

                }

                SoundHandler.playMusic(menuMusic);
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
     * Переход в главное меню.
     */
    public void goToMainMenu() {
        handler.onRestart();
        ball.onRestart();
        resetScore();
        statesStack.clear();
        currentGameState = GameState.MENU;
        statesStack.push(currentGameState);
    }

    /**
     * Переход в настройки.
     */
    public void goToOptions() {
        currentGameState = GameState.OPTIONS;
        statesStack.push(currentGameState);
    }

    /**
     * Переход в рекорды.
     */
    public void goToBestResults() {
        currentGameState = GameState.BEST_RESULTS;
        statesStack.push(currentGameState);
    }

    /**
     * Переход к авторам.
     */
    public void goToAuthors() {
        currentGameState = GameState.AUTHORS;
        statesStack.push(currentGameState);
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

        SoundHandler.stopMusic(menuMusic);
        SoundHandler.playMusic(backgroundMusic);
    }

    /**
     * Ставит игру на паузу.
     */
    public void pause() {
        currentGameState = GameState.PAUSE;
        statesStack.push(currentGameState);
        SoundHandler.pauseMusic(backgroundMusic);
        SoundHandler.playMusic(menuMusic);
    }

    /**
     * Продолжает игру после паузы.
     */
    public void resume() {
        back();
        SoundHandler.stopMusic(menuMusic);
        SoundHandler.playMusic(backgroundMusic);
    }

    /**
     * Начинает игру заново.
     */
    public void restart() {
        statesStack.pop();
        back();
        resetScore();
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

    /**
     * Сбрасывает счет.
     */
    public void resetScore() {
        score = 0;
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

    public boolean isBestResults() {
        return currentGameState == GameState.BEST_RESULTS;
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
        return currentGameState == GameState.GAME_OVER;
    }

    public boolean isHighScore() {
        return currentGameState == GameState.HIGH_SCORE;
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
