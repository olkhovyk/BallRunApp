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
    public static Texture buttonTryAgain;
    public static Texture buttonTryAgainPressed;

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

        buttonStart = new Texture(Gdx.files.internal("data/texture_button/btnStartText_text.png"));
        buttonStart.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonStartPressed = new Texture(Gdx.files.internal("data/texture_button/btnStartTextPressed_text.png"));
        buttonStartPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonOptions = new Texture(Gdx.files.internal("data/texture_button/btnOptionsText_text.png"));
        buttonOptions.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonOptionsPressed = new Texture(Gdx.files.internal("data/texture_button/btnOptionsTextPressed_text.png"));
        buttonOptionsPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonHighScores = new Texture(Gdx.files.internal("data/texture_button/btnHighScoresText_text.png"));
        buttonHighScores.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonHighScoresPressed = new Texture(Gdx.files.internal("data/texture_button/btnHighScoresTextPressed_text.png"));
        buttonHighScoresPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExit = new Texture(Gdx.files.internal("data/texture_button/btnExitText_text.png"));
        buttonExit.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExitPressed = new Texture(Gdx.files.internal("data/texture_button/btnExitTextPressed_text.png"));
        buttonExitPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonPause = new Texture(Gdx.files.internal("data/btnPause.png"));
        buttonPause.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonPausePressed = new Texture(Gdx.files.internal("data/btnPausePressed.png"));
        buttonPausePressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonResume = new Texture(Gdx.files.internal("data/texture_button/btnPlayText.png"));
        buttonResume.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonResumePressed = new Texture(Gdx.files.internal("data/texture_button/btnPlayTextPressed.png"));
        buttonResumePressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonBack = new Texture(Gdx.files.internal("data/btnBack.png"));
        buttonBack.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonBackPressed = new Texture(Gdx.files.internal("data/btnBackPressed.png"));
        buttonBackPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonSoundOn = new Texture(Gdx.files.internal("data/texture_button/SoundOn_text.png"));
        buttonSoundOn.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonSoundOff = new Texture(Gdx.files.internal("data/texture_button/SoundOff_text.png"));
        buttonSoundOff.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMusicOn = new Texture(Gdx.files.internal("data/texture_button/MusicOn_text.png"));
        buttonMusicOn.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMusicOff = new Texture(Gdx.files.internal("data/texture_button/MusicOff_text.png"));
        buttonMusicOff.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonCredits = new Texture(Gdx.files.internal("data/texture_button/btnCredits_text.png"));
        buttonCredits.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonCreditsPressed = new Texture(Gdx.files.internal("data/texture_button/btnCreditsPressed_text.png"));
        buttonCreditsPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMainMenu = new Texture(Gdx.files.internal("data/texture_button/btnNewMainMenu.png"));
        buttonMainMenu.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonMainMenuPressed = new Texture(Gdx.files.internal("data/texture_button/btnNewMainMenuPressed.png"));
        buttonMainMenuPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonTryAgain = new Texture(Gdx.files.internal("data/texture_button/btnTryAgain.png"));
        buttonTryAgain.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonTryAgainPressed = new Texture(Gdx.files.internal("data/texture_button/btnTryAgainPressed.png"));
        buttonTryAgainPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        musicMenu = Gdx.audio.newMusic(Gdx.files.internal("data/sleepingfairy.wav"));
        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("data/background.wav"));

        soundScored = Gdx.audio.newSound(Gdx.files.internal("data/scoreCoin.wav"));
        soundClicked = Gdx.audio.newSound(Gdx.files.internal("data/buttonClicked.wav"));
        soundDefeat = Gdx.audio.newSound(Gdx.files.internal("data/defeat.wav"));
        soundHighScore = Gdx.audio.newSound(Gdx.files.internal("data/highscore.wav"));

        fontFile = Gdx.files.internal("data/font/font_fixed.fnt");

        preferences = Gdx.app.getPreferences("BallRunApp");

        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
//        }

        //Быдлокодищеее
//        if(!preferences.contains("resultsOne")){
            preferences.putInteger("resultsOne", preferences.getInteger("highScore"));
//        }
//        if(!preferences.contains("resultsTwo")){
            preferences.putInteger("resultsTwo", 0);
//        }
//        if(!preferences.contains("resultsThree")){
            preferences.putInteger("resultsThree", 0);
//        }
//        if(!preferences.contains("resultsFour")){
            preferences.putInteger("resultsFour", 0);
//        }
//       if(!preferences.contains("resultsFive")){
            preferences.putInteger("resultsFive", 0);
            preferences.putBoolean("Sound", true);
            preferences.putBoolean("Music", true);
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
        buttonMusicOn.dispose();
        buttonMusicOff.dispose();
        buttonMainMenu.dispose();
        buttonMainMenuPressed.dispose();
        buttonCredits.dispose();
        buttonCreditsPressed.dispose();
        buttonTryAgain.dispose();
        buttonTryAgainPressed.dispose();
        musicMenu.dispose();
        musicBackground.dispose();
        soundScored.dispose();
        soundClicked.dispose();
        soundDefeat.dispose();
        soundHighScore.dispose();
    }

    public static void setHighScore(int val){
        if(val > preferences.getInteger("highScore")){
            preferences.putInteger("highScore", val);
        }
        if(val > preferences.getInteger("resultsOne")){
            preferences.putInteger("resultsFive", preferences.getInteger("resultsFour"));
            preferences.putInteger("resultsFour", preferences.getInteger("resultsThree"));
            preferences.putInteger("resultsThree", preferences.getInteger("resultsTwo"));
            preferences.putInteger("resultsTwo", preferences.getInteger("resultsOne"));
            preferences.putInteger("resultsOne",val);
        }
        if(val > preferences.getInteger("resultsTwo") && val < preferences.getInteger("resultsOne")){
            preferences.putInteger("resultsFive", preferences.getInteger("resultsFour"));
            preferences.putInteger("resultsFour", preferences.getInteger("resultsThree"));
            preferences.putInteger("resultsThree", preferences.getInteger("resultsTwo"));
            preferences.putInteger("resultsTwo", val);
        }
        if(val > preferences.getInteger("resultsThree") && val < preferences.getInteger("resultsTwo")){
            preferences.putInteger("resultsFive", preferences.getInteger("resultsFour"));
            preferences.putInteger("resultsFour", preferences.getInteger("resultsThree"));
            preferences.putInteger("resultsThree", val);

        }
        if(val > preferences.getInteger("resultsFour") && val < preferences.getInteger("resultsThree")){
            preferences.putInteger("resultsFive", preferences.getInteger("resultsFour"));
            preferences.putInteger("resultsFour", val);
        }
        if(val > preferences.getInteger("resultsFive") && val < preferences.getInteger("resultsFour")){
            preferences.putInteger("resultsFive", val);
        }
        preferences.flush();
    }

    public static int getLastHighScore() {
        return preferences.getInteger("resultsFive");
    }

    public static void putSoundValue(boolean val) {
        preferences.putBoolean("Sound", val);
        preferences.flush();
    }

    public static void putMusicValue(boolean val) {
        preferences.putBoolean("Music", val);
        preferences.flush();
    }

    public static boolean isSoundOn() {
        return preferences.getBoolean("Sound");
    }

    public static boolean isMusicOn() {
        return preferences.getBoolean("Music");
    }
}
