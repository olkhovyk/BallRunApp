package com.olkandsvir.ballrunapp.brhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Responsible for using assets in the game.
 * @since 15.04.2014
 */
public class AssetsLoader {

    public static Texture background;
    public static Texture ball;
    public static Texture barrier;

    public static void load() {
        background = new Texture(Gdx.files.internal("data/back.png"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        ball = new Texture(Gdx.files.internal("data/circle.png"));
        ball.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        barrier = new Texture(Gdx.files.internal("data/rect.png"));
        barrier.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    public static void dispose() {
        background.dispose();
        ball.dispose();
        barrier.dispose();
    }

}
