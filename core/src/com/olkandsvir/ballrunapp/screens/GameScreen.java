package com.olkandsvir.ballrunapp.screens;

import com.badlogic.gdx.Screen;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * For showing all object of the game on a screen of a device.
 * @since 15.04.2016
 */
public class GameScreen implements Screen {

    private GameRenderer renderer;

    private float runTime;

    public GameScreen() {
        renderer = new GameRenderer();

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
