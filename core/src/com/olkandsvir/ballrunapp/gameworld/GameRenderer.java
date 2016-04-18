package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;

/**
 * For rendering the world of the game.
 * @since 15.04.2016
 */
public class GameRenderer {


    //высота и ширина игрового поля
    public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
    public static final int GAME_WIDTH = Gdx.graphics.getWidth();

    //камера для игры
    private OrthographicCamera camera;

    //рисует картинки для игры
    private SpriteBatch batcher;

    //текстуры
    private Texture background;
    private Texture ballTexture;
    private Texture barrierTexture;

    //игровой мяч
    private Ball ball;

    //препятствия
    private Barrier barrier1;
    private Barrier barrier2;
    private Barrier barrier3;

    //отвечает за движение препятствий
    private ScrollHandler handler;

    //конструктор
    public GameRenderer(ScrollHandler handler) {

        this.handler = handler;

        //инициализируем камеру
        camera = new OrthographicCamera();

        //первый параметр устанавливает (0;0) координату в левый верхний угол
        //второй и третий отвечает за ширину и высоту
        camera.setToOrtho(true, GAME_WIDTH, GAME_HEIGHT);

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
        ball = new Ball(GAME_WIDTH / 2, (int) (GAME_HEIGHT /1.2));
      //  barrier1 = handler.getBarrier1();
      //  barrier2 = handler.getBarrier2();
       // barrier3 = handler.getBarrier3();
    }

    /**
     * Инициализируем дополнительные ресурсы.
     */
    private void initAssets() {

        //загружаем картинку фона
        background = AssetsLoader.background;

        //загружаем картинку для мячика
        ballTexture = AssetsLoader.ball;

        //загружаем картинку для препятствий
        barrierTexture = AssetsLoader.barrier;
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

        //рисуем препятствия
        for(Barrier barrier : handler.getB()){
            batcher.draw(barrierTexture, barrier.getPart().getX(), barrier.getPosition().y,
                barrier.getPart().getWidth(), barrier.getHeight());
        }
     /*   batcher.draw(barrierTexture, barrier1.getPart().getX(), barrier1.getPosition().y,
                barrier1.getPart().getWidth(), barrier1.getHeight());

        batcher.draw(barrierTexture, barrier2.getPart().getX(), barrier2.getPosition().y,
                barrier2.getPart().getWidth(), barrier2.getHeight());

        batcher.draw(barrierTexture, barrier3.getPart().getX(), barrier3.getPosition().y,
                barrier3.getPart().getWidth(), barrier3.getHeight()); */

        //добавляем прозрачность, она нужна мячику
        batcher.enableBlending();

        //рисуем мяч
        batcher.draw(ballTexture, ball.getPosition().x - ball.getDiameter() / 2, ball.getPosition().y,
                ball.getDiameter(), ball.getDiameter());

        //закрываем пакет
        batcher.end();

        //Перемещение шарика по касанию
        if(Gdx.input.isTouched()){
            ball.move();
        }
    }

    public void dispose() {
        batcher.dispose();
    }

}
