package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;
import com.olkandsvir.ballrunapp.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Process inputs from users.
 * @since 21.04.2016
 */
public class InputHandler implements InputProcessor {

    //игровой мир
    private GameWorld world;

    //мячик игрока
    private Ball ball;

    //кнопки меню
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> pauseMenuButtons;

    //все кнопки в игре
    private SimpleButton startButton, optionsButton, exitButton, pauseButton, resumeButton, backButton;

    //конструктор
    public InputHandler(GameWorld world) {

        //инициализируем мир и мячик
        this.world = world;
        this.ball = world.getBall();

        //создаем кнопки
        startButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 12,
                AssetLoader.buttonStart, AssetLoader.buttonStartPressed);
        optionsButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, 2 * GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 12,
                AssetLoader.buttonOptions, AssetLoader.buttonOptionsPressed);
        exitButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, 3 * GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 12,
                AssetLoader.buttonExit, AssetLoader.buttonExitPressed);
        pauseButton = new SimpleButton(GameScreen.SCREEN_WIDTH * 4 / 5, 0,
                GameScreen.SCREEN_WIDTH / 6, GameScreen.SCREEN_HEIGHT / 10,
                AssetLoader.buttonPause, AssetLoader.buttonPausePressed);
        resumeButton = new SimpleButton(3 * GameScreen.SCREEN_WIDTH / 8, 4 * GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_WIDTH / 4,
                AssetLoader.buttonResume, AssetLoader.buttonResumePressed);
        backButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 2,
                GameScreen.SCREEN_WIDTH / 5, GameScreen.SCREEN_HEIGHT / 9,
                AssetLoader.buttonBack, AssetLoader.buttonBackPressed);

        //записываем кнопки в списки меню
        menuButtons = new ArrayList<SimpleButton>();
        menuButtons.add(startButton);
        menuButtons.add(optionsButton);
        menuButtons.add(exitButton);

        pauseMenuButtons = new ArrayList<SimpleButton>();
        pauseMenuButtons.add(optionsButton);
        pauseMenuButtons.add(exitButton);
        pauseMenuButtons.add(resumeButton);
    }

    /**
     * Получает кнопки главного меню
     * @return главное меню
     */
    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    /**
     * Получает кнопки меню паузы
     * @return меню паузы
     */
    public List<SimpleButton> getPauseMenuButtons() {
        return pauseMenuButtons;
    }

    /**
     * Получает кнопку паузы
     * @return кнопка паузы
     */
    public SimpleButton getPauseButton() {
        return pauseButton;
    }

    /**
     * Получает кнопку назад
     * @return кнопка назад
     */
    public SimpleButton getBackButton() {
        return backButton;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Вызывается при нажатии кнопки мыши или касании экрана
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isMenu()) {
            startButton.isTouchDown(screenX, screenY);
            optionsButton.isTouchDown(screenX, screenY);
            exitButton.isTouchDown(screenX, screenY);
        } else if (world.isOptions()) {
            backButton.isTouchDown(screenX, screenY);
        } else if(world.isRunning()) {
            /* СТРАННО СЕБЯ ВЕДЕТ ...
            ball.onClick(); */
            pauseButton.isTouchDown(screenX, screenY);
        } else if(world.isPaused()) {
            resumeButton.isTouchDown(screenX, screenY);
            optionsButton.isTouchDown(screenX, screenY);
            exitButton.isTouchDown(screenX, screenY);
        } else if (world.isReady()) {
            world.start();
        } else if (world.isGameOver() || world.isHighScore()) {
            world.restart();
        }

        return false;
    }

    /**
     * Вызывается при отпускании кнопки мыши или прекращению касания экрана
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (world.isMenu()) {
            if (startButton.isTouchUp(screenX, screenY)) {
                world.ready();
                return true;
            } else if (optionsButton.isTouchUp(screenX, screenY)) {
                //TO DO OPTIONS
                world.goToOptions();
                return true;
            } else if(exitButton.isTouchUp(screenX, screenY)) {
                Gdx.app.exit();
                return true;
            }
        } else if (world.isOptions()) {
            if(backButton.isTouchUp(screenX, screenY)) {
                //TO DO BACK
                world.back();
                return true;
            }
        } else if (world.isRunning()) {
            if (pauseButton.isTouchUp(screenX, screenY)) {
                world.pause();
                return true;
            }
        } else if(world.isPaused()) {
            if(resumeButton.isTouchUp(screenX, screenY)) {
                world.resume();
                return true;
            } else if (optionsButton.isTouchUp(screenX, screenY)) {
                //TO DO OPTIONS
                world.goToOptions();
                return true;
            } else if(exitButton.isTouchUp(screenX, screenY)) {
                Gdx.app.exit();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
// СТРАННО СЕБЯ ВЕДЕТ ...
//        ball.onClick();
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
