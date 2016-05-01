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

    private GameWorld world;
    private Ball ball;
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> pauseMenuButtons;
    private SimpleButton startButton, optionsButton, exitButton, pauseButton, resumeButton, backButton;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.ball = world.getBall();

        startButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonStart, AssetLoader.buttonStartPressed);
        optionsButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, 2 * GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonOptions, AssetLoader.buttonOptionsPressed);
        exitButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, 3 * GameScreen.SCREEN_HEIGHT / 7,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonExit, AssetLoader.buttonExitPressed);

        menuButtons = new ArrayList<SimpleButton>();
        menuButtons.add(startButton);
        menuButtons.add(optionsButton);
        menuButtons.add(exitButton);

        pauseButton = new SimpleButton(GameScreen.SCREEN_WIDTH * 4 / 5, 0,
                GameScreen.SCREEN_WIDTH / 5, GameScreen.SCREEN_HEIGHT / 9,
                AssetLoader.buttonPause, AssetLoader.buttonPausePressed);
        resumeButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_HEIGHT / 2,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_WIDTH / 2,
                AssetLoader.buttonResume, AssetLoader.buttonResumePressed);

        pauseMenuButtons = new ArrayList<SimpleButton>();
        pauseMenuButtons.add(optionsButton);
        pauseMenuButtons.add(exitButton);
        pauseMenuButtons.add(resumeButton);

        backButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 2,
                GameScreen.SCREEN_WIDTH / 5, GameScreen.SCREEN_HEIGHT / 9,
                AssetLoader.buttonBack, AssetLoader.buttonBackPressed);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    public List<SimpleButton> getPauseMenuButtons() {
        return pauseMenuButtons;
    }

    public SimpleButton getPauseButton() {
        return pauseButton;
    }

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

        return true;
    }

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
