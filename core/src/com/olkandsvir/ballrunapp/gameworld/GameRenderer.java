package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.olkandsvir.ballrunapp.BallRunGame;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.gameobject.Ball;

/**
 * For rendering the world of the game.
 * @since 15.04.2016
 */
public class GameRenderer {

    public static final int BALL_WIDTH = 75;
    public static final int BALL_HEIGHT = 75;
    private Ball ball;

    //камера для игры
    private OrthographicCamera camera;

    //рисует картинки для игры
    private SpriteBatch batcher;

    //высота и ширина игрового поля
    private int height = BallRunGame.HEIGHT;
    private int width = BallRunGame.WIDTH;

    //текстура фона
    private Texture background;

    private Texture ballTexture;

    //конструктор
    public GameRenderer() {
        //инициализируем камеру
        camera = new OrthographicCamera();
        //первый параметр устанавливает (0;0) координату в левый верхний угол
        //второй и третий отвечает за ширину и высоту
        camera.setToOrtho(true, width, height);

        //инициализируем пакет для рисования
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();

    }

    /**
     * Иницализируем игровые объекты.
     */
    private void initGameObjects() {
        //TO DO
        ball = new Ball(width / 2 - BALL_WIDTH / 2, (int) (height /1.2));

    }

    /**
     * Инициализируем дополнительные ресурсы.
     */
    private void initAssets() {
        //загружаем картинку фона
        background = AssetsLoader.background;

        ballTexture = AssetsLoader.ball;

    }

    /**
     * Рендерим все, что нам нужно.
     * @param runTime    переменная, которая позволит в дальнейшем правильно
     *                   отображать игру при изменении fps.
     */
    public void render(float runTime) {

        //устанавливаем черный фон
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //запускаем пакет рисования
        batcher.begin();

        //убираем прозрачность, полезно для производительности
        batcher.disableBlending();

        //рисуем фон
        batcher.draw(background, 0, 0);

        //добавляем прозрачность, она нужна мячику
        batcher.enableBlending();

        //рисуем мяч
        batcher.draw(ballTexture, ball.getPosition().x, ball.getPosition().y, BALL_WIDTH, BALL_HEIGHT);

        //закрываем пакет
        batcher.end();

    }

}