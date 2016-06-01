package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Responsible for using assets in the game.
 * @since 15.04.2014
 */
public class AssetLoader {

    public static Texture background;
    public static Texture background2;
    public static Texture ball;
    public static Texture barrier;
    public static Texture buttonStart;
    public static Texture buttonStartPressed;
    public static Texture buttonOptions;
    public static Texture buttonOptionsPressed;
    public static Texture buttonHighScores;
    public static Texture buttonHighScoresPressed;
    public static Texture buttonExit;
    public static Texture buttonExitPressed;
    public static Texture buttonPause;
    public static Texture buttonPausePressed;
    public static Texture buttonResume;
    public static Texture buttonResumePressed;
    public static Texture buttonBack;
    public static Texture buttonBackPressed;
    public static Texture buttonSoundOn;
    public static Texture buttonSoundOff;
    public static Texture buttonMusicOn;
    public static Texture buttonMusicOff;
    public static Texture buttonCredits;
    public static Texture buttonCreditsPressed;
    public static Texture buttonMainMenu;
    public static Texture buttonMainMenuPressed;

    public static Music musicMenu;
    public static Music musicBackground;

    public static Sound soundScored;
    public static Sound soundClicked;
    public static Sound soundDefeat;
    public static Sound soundHighScore;

    public static FileHandle fontFile;

    public static Preferences preferences;

    public static void load() {
        background = new Texture(Gdx.files.internal("data/back.png"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background2 = new Texture(Gdx.files.internal("data/back2.png"));
        background2.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        ball = new Texture(Gdx.files.internal("data/circle.png"));
        ball.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        barrier = new Texture(Gdx.files.internal("data/rect.png"));
        barrier.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonStart = new Texture(Gdx.files.internal("data/btnStart.png"));
        buttonStart.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonStartPressed = new Texture(Gdx.files.internal("data/btnStartPressed.png"));
        buttonStartPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonOptions = new Texture(Gdx.files.internal("data/btnOptions.png"));
        buttonOptions.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonOptionsPressed = new Texture(Gdx.files.internal("data/btnOptionsPressed.png"));
        buttonOptionsPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonHighScores = new Texture(Gdx.files.internal("data/btnHighScores.png"));
        buttonHighScores.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonHighScoresPressed = new Texture(Gdx.files.internal("data/btnHighScoresPressed.png"));
        buttonHighScoresPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExit = new Texture(Gdx.files.internal("data/btnExit.png"));
        buttonExit.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExitPressed = new Texture(Gdx.files.internal("data/btnExitPressed.png"));
        buttonExitPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonPause = new Texture(Gdx.files.internal("data/btnPause.png"));
        buttonPause.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonPausePressed = new Texture(Gdx.files.internal("data/btnPausePressed.png"));
        buttonPausePressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonResume = new Texture(Gdx.files.internal("data/btnResume.png"));
        buttonResume.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonResumePressed = new Texture(Gdx.files.internal("data/btnResumePressed.png"));
        buttonResumePressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonBack = new Texture(Gdx.files.internal("data/btnBack.png"));
        buttonBack.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonBackPressed = new Texture(Gdx.files.internal("data/btnBackPressed.png"));
        buttonBackPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonSoundOn = new Texture(Gdx.files.internal("data/btnSoundOn.png"));
        buttonSoundOn.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonSoundOff = new Texture(Gdx.files.internal("data/btnSoundOff.png"));
        buttonSoundOff.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonCredits = new Texture(Gdx.files.internal("data/btnCredits.png"));
        buttonCredits.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonCreditsPressed = new Texture(Gdx.files.internal("data/btnCreditsPressed.png"));
        buttonCreditsPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMainMenu = new Texture(Gdx.files.internal("data/btnMainMenu.png"));
        buttonMainMenu.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMainMenuPressed = new Texture(Gdx.files.internal("data/btnMainMenuPressed.png"));
        buttonMainMenuPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        musicMenu = Gdx.audio.newMusic(Gdx.files.internal("data/sleepingfairy.wav"));
        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("data/background.wav"));

        soundScored = Gdx.audio.newSound(Gdx.files.internal("data/scoreCoin.mp3"));
        soundClicked = Gdx.audio.newSound(Gdx.files.internal("data/buttonClicked.wav"));
        soundDefeat = Gdx.audio.newSound(Gdx.files.internal("data/defeat.wav"));
        soundHighScore = Gdx.audio.newSound(Gdx.files.internal("data/highscore.wav"));

        fontFile = Gdx.files.internal("data/Ilya_font2.ttf");

        preferences = Gdx.app.getPreferences("BallRunApp");

        if(!preferences.contains("resultsOne")){
            preferences.putInteger("resultsOne", 0);
            preferences.putInteger("resultsTwo", 0);
            preferences.putInteger("resultsThree", 0);
            preferences.putInteger("resultsFour", 0);
            preferences.putInteger("resultsFive", 0);
        }
    }

    public static void dispose() {
        background.dispose();
        background2.dispose();
        ball.dispose();
        barrier.dispose();
        buttonStart.dispose();
        buttonStartPressed.dispose();
        buttonOptions.dispose();
        buttonOptionsPressed.dispose();
        buttonHighScores.dispose();
        buttonHighScoresPressed.dispose();
        buttonExit.dispose();
        buttonExitPressed.dispose();
        buttonPause.dispose();
        buttonPausePressed.dispose();
        buttonResume.dispose();
        buttonResumePressed.dispose();
        buttonBack.dispose();
        buttonBackPressed.dispose();
        buttonSoundOn.dispose();
        buttonSoundOff.dispose();
        buttonMainMenu.dispose();
        buttonMainMenuPressed.dispose();
        buttonCredits.dispose();
        buttonCreditsPressed.dispose();
        musicMenu.dispose();
        musicBackground.dispose();
        soundScored.dispose();
        soundClicked.dispose();
        soundDefeat.dispose();
        soundHighScore.dispose();
    }

    public static void setHighScore(int val) {

        if(val > preferences.getInteger("resultsFive")) {
            preferences.putInteger("resultsFive", val);
        }

        if(val > preferences.getInteger("resultsFour")) {
            swap("resultsFour", "resultsFive");
        }

        if(val > preferences.getInteger("resultsThree")) {
            swap("resultsThree", "resultsFour");
        }

        if(val > preferences.getInteger("resultsTwo")) {
            swap("resultsTwo", "resultsThree");
        }

        if(val > preferences.getInteger("resultsOne")) {
            swap("resultsOne", "resultsTwo");
        }

        preferences.flush();
    }

    private static void swap(String str1, String str2) {
        int tmp = preferences.getInteger(str1);
        preferences.putInteger(str1, preferences.getInteger(str2));
        preferences.putInteger(str2, tmp);
    }

    public static int getHighScore() {
        return preferences.getInteger("resultsOne");
    }

    public static int getLastHighScore() {
        return preferences.getInteger("resultsFive");
    }

}
