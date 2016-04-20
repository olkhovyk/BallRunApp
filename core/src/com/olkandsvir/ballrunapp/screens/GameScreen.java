package com.olkandsvir.ballrunapp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

/**
 * For showing all object of the game on a screen of a device.
 * @since 15.04.2016
 */
public class GameScreen implements Screen {

    private GameRenderer renderer;
    private ScrollHandler handler;
    Ball ball;

    public static int collisions = 0;

    private float runTime;

    public GameScreen() {

        this.handler = new ScrollHandler();
        renderer = new GameRenderer(handler);
        ball = renderer.getBall();
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
        handler.update(delta);

        if(handler.collides(ball)) {
            collisions++;
            Gdx.app.log("Collided", Integer.toString(collisions));
        }
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
