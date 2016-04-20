package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * For rendering the world of the game.
 * @since 15.04.2016
 */
public class GameRenderer {

    //игровой мир
    private final GameWorld world;

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
    private Array<Barrier> barriers;

    //отвечает за движение препятствий
    private ScrollHandler handler;

    //ДЛЯ ТЕСТОВ!
    private ShapeRenderer shapeRenderer;

    //конструктор
    public GameRenderer(GameWorld world) {

        //создаем игровой мир
        this.world = world;

        //инициализируем камеру
        camera = new OrthographicCamera();

        //первый параметр устанавливает (0;0) координату в левый верхний угол
        //второй и третий отвечает за ширину и высоту
        camera.setToOrtho(true, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);

        //ДЛЯ ТЕСТОВ!
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

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
        ball = world.getBall();
        handler = world.getHandler();
        barriers = handler.getBarriers();
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

        //ВЕРНУТЬ ПО ОКОНЧАНИИ ТЕСТОВ!
        //убираем прозрачность, полезно для производительности
 //       batcher.disableBlending();

        //ДЛЯ ТЕСТОВ, ПОТОМ УДАЛИТЬ!
        batcher.enableBlending();

        //рисуем фон
        batcher.draw(background, 0, 0);

        //рисуем препятствия
        for(Barrier barrier : barriers){
            batcher.draw(barrierTexture, barrier.getPart().getX(), barrier.getPosition().y,
                barrier.getPart().getWidth(), barrier.getHeight());

            //ДЛЯ ТЕСТОВ!
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(barrier.getPart().getRectangle().getX(),
                    barrier.getPart().getRectangle().getY(),
                    barrier.getPart().getRectangle().getWidth(),
                    barrier.getPart().getRectangle().getHeight());
            shapeRenderer.end();
        }

        //добавляем прозрачность, она нужна мячику
        batcher.enableBlending();

        //рисуем мяч
        batcher.draw(ballTexture, ball.getPosition().x - ball.getDiameter() / 2, ball.getPosition().y,
                ball.getDiameter(), ball.getDiameter());

        //закрываем пакет
        batcher.end();

        //ДЛЯ ТЕСТОВ!
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(ball.getBoundingCircle().x, ball.getBoundingCircle().y, ball.getBoundingCircle().radius);
        shapeRenderer.end();

    }

    public void dispose() {
        batcher.dispose();
        shapeRenderer.dispose();
    }

}
