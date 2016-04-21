package com.olkandsvir.ballrunapp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.olkandsvir.ballrunapp.brhelpers.InputHandler;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;

/**
 * For showing all object of the game on a screen of a device.
 * @since 15.04.2016
 */
public class GameScreen implements Screen {

    //высота и ширина экрана
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();

    private GameWorld world;
    private GameRenderer renderer;

    private float runTime;

    public GameScreen() {
        world = new GameWorld();
        Gdx.input.setInputProcessor(new InputHandler(world));
        renderer = new GameRenderer(world);
    }

    @Override
    public void show() {

    }

    /**
     * Рисуем игру.
     * @param delta    переменная, которая позволит в дальнейшем правильно
     *                 отображать игру при изменении fps.
     */
    @Override
    public void render(float delta) {

        //контроль за изменением fps
        runTime += delta;

        //запускаем метод render из GameRenderer
        renderer.render(runTime);

        //передвигаем препятствия
        world.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
