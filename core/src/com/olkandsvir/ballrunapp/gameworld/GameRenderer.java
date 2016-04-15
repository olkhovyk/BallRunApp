package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.olkandsvir.ballrunapp.BallRunGame;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;

/**
 * For rendering the world of the game.
 * @since 15.04.2016
 */
public class GameRenderer {

    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private SpriteBatch batcher;

    private int height = BallRunGame.HEIGHT;
    private int width = BallRunGame.WIDTH;
    private Texture background;

    public GameRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, width, height);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();

    }

    private void initGameObjects() {


    }

    private void initAssets() {
        background = AssetsLoader.background;

    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(background, 0, 0);
        batcher.end();

    }

}
