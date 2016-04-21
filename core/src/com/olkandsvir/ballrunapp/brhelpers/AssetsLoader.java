package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

/**
 * Responsible for using assets in the game.
 * @since 15.04.2014
 */
public class AssetsLoader {

    public static Texture background;
    public static Texture ball;
    public static Texture barrier;
    public static Texture buttonStart;
    public static Texture buttonStartPressed;
    public static Texture buttonExit;
    public static Texture buttonExitPressed;

    public static Preferences preferences;

    public static void load() {
        background = new Texture(Gdx.files.internal("data/back.png"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        ball = new Texture(Gdx.files.internal("data/circle.png"));
        ball.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        barrier = new Texture(Gdx.files.internal("data/rect.png"));
        barrier.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonStart = new Texture(Gdx.files.internal("data/btnStart.png"));
        buttonStart.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonStartPressed = new Texture(Gdx.files.internal("data/btnStartPressed.png"));
        buttonStartPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExit = new Texture(Gdx.files.internal("data/btnExit.png"));
        buttonExit.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        buttonExitPressed = new Texture(Gdx.files.internal("data/btnExitPressed.png"));
        buttonExitPressed.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        preferences = Gdx.app.getPreferences("BallRunApp");

        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
        }
    }

    public static void dispose() {
        background.dispose();
        ball.dispose();
        barrier.dispose();
        buttonStart.dispose();
        buttonStartPressed.dispose();
        buttonExit.dispose();
        buttonExitPressed.dispose();
    }

    public static void setHighScore(int val) {
        preferences.putInteger("highScore", val);
        preferences.flush();
    }

    public static int getHighScore() {
        return preferences.getInteger("highScore");
    }

}
