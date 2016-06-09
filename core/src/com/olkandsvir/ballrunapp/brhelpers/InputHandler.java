package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;
import com.olkandsvir.ballrunapp.ui.AbstractButton;
import com.olkandsvir.ballrunapp.ui.SimpleButton;
import com.olkandsvir.ballrunapp.ui.SimpleSwitchButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Process inputs from users.
 * @since 21.04.2016
 */
public class InputHandler implements InputProcessor {

    //игровой мир
    private GameWorld world;

    //кнопки меню
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> pauseMenuButtons;
    private List<AbstractButton> optionsMenuButtons;
    private List<SimpleButton> gameOverButtons;

    //все кнопки в игре
    private SimpleButton startButton, optionsButton, highScoresButton, exitButton, mainMenuButton,
            pauseButton, resumeButton, backButton, creditsButton, mainMenuGameOverButton, highScoresGameOverButton, tryAgainButton;
    private SimpleSwitchButton musicOffButton, soundOffButton;

    //проигрыватель
    private SoundHandler soundHandler = new SoundHandler();

    //конструктор
    public InputHandler(GameWorld world) {

        //инициализируем мир и мячик
        this.world = world;

        //создаем кнопки
        startButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonStart, AssetLoader.buttonStartPressed);
        optionsButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 2 * GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonOptions, AssetLoader.buttonOptionsPressed);
        highScoresButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 3 * GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonHighScores, AssetLoader.buttonHighScoresPressed);
        exitButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 4 * GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonExit, AssetLoader.buttonExitPressed);
        mainMenuButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonMainMenu, AssetLoader.buttonMainMenuPressed);
        pauseButton = new SimpleButton(GameScreen.SCREEN_WIDTH * 3 / 4, 0,
                GameScreen.SCREEN_WIDTH / 4, GameScreen.SCREEN_WIDTH / 4,
                AssetLoader.buttonPause, AssetLoader.buttonPausePressed);
        resumeButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 3, 4 * GameScreen.SCREEN_HEIGHT / 6,
                GameScreen.SCREEN_WIDTH / 3, GameScreen.SCREEN_WIDTH / 3,
                AssetLoader.buttonResume, AssetLoader.buttonResumePressed);
        backButton = new SimpleButton(4 * GameScreen.SCREEN_WIDTH / 5, 8 * GameScreen.SCREEN_HEIGHT / 9,
                GameScreen.SCREEN_WIDTH / 5, GameScreen.SCREEN_HEIGHT / 9,
                AssetLoader.buttonBack, AssetLoader.buttonBackPressed);
        musicOffButton = new SimpleSwitchButton(GameScreen.SCREEN_WIDTH / 4, 3 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonMusicOn, AssetLoader.buttonMusicOff, AssetLoader.isMusicOn());
        soundOffButton = new SimpleSwitchButton(GameScreen.SCREEN_WIDTH / 4, 5 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonSoundOn, AssetLoader.buttonSoundOff, AssetLoader.isSoundOn());
        creditsButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 7 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonCredits, AssetLoader.buttonCreditsPressed);
        mainMenuGameOverButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 5 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonMainMenu, AssetLoader.buttonMainMenuPressed);
        highScoresGameOverButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 7 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonHighScores, AssetLoader.buttonHighScoresPressed);
        tryAgainButton = new SimpleButton(GameScreen.SCREEN_WIDTH / 4, 9 * GameScreen.SCREEN_HEIGHT / 12,
                GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 8,
                AssetLoader.buttonTryAgain, AssetLoader.buttonTryAgainPressed);

        //записываем кнопки в списки меню
        menuButtons = new ArrayList<SimpleButton>();
        menuButtons.add(startButton);
        menuButtons.add(optionsButton);
        menuButtons.add(highScoresButton);
        menuButtons.add(exitButton);

        pauseMenuButtons = new ArrayList<SimpleButton>();
        pauseMenuButtons.add(mainMenuButton);
        pauseMenuButtons.add(optionsButton);
        pauseMenuButtons.add(highScoresButton);
        pauseMenuButtons.add(resumeButton);

        optionsMenuButtons = new ArrayList<AbstractButton>();
        optionsMenuButtons.add(musicOffButton);
        optionsMenuButtons.add(soundOffButton);
        optionsMenuButtons.add(creditsButton);

        gameOverButtons = new ArrayList<SimpleButton>();
        gameOverButtons.add(mainMenuGameOverButton);
        gameOverButtons.add(highScoresGameOverButton);
        gameOverButtons.add(tryAgainButton);
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
     * Получает кнопки меню настроек
     * @return меню настроек
     */
    public List<AbstractButton> getOptionsMenuButtons() {
        return optionsMenuButtons;
    }

    /**
     * Получает кнопки меню в конце игры.
     * @return меню конца игры
     */
    public List<SimpleButton> getGameOverButtons() {
        return gameOverButtons;
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
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isMenu()) {
            startButton.isTouchDown(screenX, screenY);
            optionsButton.isTouchDown(screenX, screenY);
            highScoresButton.isTouchDown(screenX, screenY);
            exitButton.isTouchDown(screenX, screenY);
        } else if (world.isOptions()) {
            musicOffButton.isTouchDown(screenX, screenY);
            soundOffButton.isTouchDown(screenX, screenY);
            creditsButton.isTouchDown(screenX, screenY);
            backButton.isTouchDown(screenX, screenY);
        } else if (world.isBestResults()) {
            backButton.isTouchDown(screenX, screenY);
        } else if (world.isAuthors()) {
            backButton.isTouchDown(screenX, screenY);
        } else if(world.isRunning()) {
            pauseButton.isTouchDown(screenX, screenY);
        } else if(world.isPaused()) {
            mainMenuButton.isTouchDown(screenX, screenY);
            optionsButton.isTouchDown(screenX, screenY);
            highScoresButton.isTouchDown(screenX, screenY);
            resumeButton.isTouchDown(screenX, screenY);
        } else if (world.isReady()) {
            world.start();
        } else if (world.isGameOver() || world.isHighScore()) {
            mainMenuGameOverButton.isTouchDown(screenX, screenY);
            highScoresGameOverButton.isTouchDown(screenX, screenY);
            tryAgainButton.isTouchDown(screenX, screenY);
        }

        return false;
    }

    /**
     * Вызывается при отпускании кнопки мыши или прекращению касания экрана
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (world.isMenu()) {
            if (startButton.isTouchUp(screenX, screenY)) {
                world.ready();
                return true;
            } else if (optionsButton.isTouchUp(screenX, screenY)) {
                world.goToOptions();
                return true;
            } else if(highScoresButton.isTouchUp(screenX, screenY)) {
                world.goToBestResults();
                return true;
            } else if(exitButton.isTouchUp(screenX, screenY)) {
                Gdx.app.exit();
                return true;
            }
        } else if (world.isOptions()) {
            if (backButton.isTouchUp(screenX, screenY)) {
                world.back();
                return true;
            } else if (musicOffButton.isTouchUp(screenX, screenY)){
                soundHandler.changeMusic();
                return true;
            } else if (soundOffButton.isTouchUp(screenX, screenY)){
                soundHandler.changeSound();
                return true;
            } else if (creditsButton.isTouchUp(screenX, screenY)) {
                world.goToAuthors();
                return true;
            }
        } else if (world.isBestResults()) {
            if (backButton.isTouchUp(screenX, screenY)) {
                world.back();
                return true;
            }
        } else if (world.isAuthors()) {
            if (backButton.isTouchUp(screenX, screenY)) {
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
            } else if (mainMenuButton.isTouchUp(screenX, screenY)) {
                world.goToMainMenu();
                return true;
            } else if (optionsButton.isTouchUp(screenX, screenY)) {
                world.goToOptions();
                return true;
            } else if(highScoresButton.isTouchUp(screenX, screenY)) {
                world.goToBestResults();
                return true;
            }
        } else if (world.isGameOver() || world.isHighScore()) {
            if (mainMenuGameOverButton.isTouchUp(screenX, screenY)) {
                world.goToMainMenu();
                return true;
            } else if (highScoresGameOverButton.isTouchUp(screenX, screenY)) {
                world.goToBestResults();
                return true;
            } else if(tryAgainButton.isTouchUp(screenX, screenY)) {
                world.restart();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
