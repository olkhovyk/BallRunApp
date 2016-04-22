package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.brhelpers.InputHandler;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.screens.GameScreen;
import com.olkandsvir.ballrunapp.ui.SimpleButton;

import java.util.List;

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

    //шрифт
    BitmapFont font;

    //Кнопки меню
    private List<SimpleButton> buttons;

    //конструктор
    public GameRenderer(GameWorld world) {

        //создаем игровой мир
        this.world = world;

        //кнопки меню
        this.buttons = ((InputHandler) Gdx.input.getInputProcessor()).getButtons();

        //инициализируем камеру
        camera = new OrthographicCamera();

        //первый параметр устанавливает (0;0) координату в левый верхний угол
        //второй и третий отвечает за ширину и высоту
        camera.setToOrtho(true, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);

        //инициализируем пакет для рисования
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        //инициализируем пакет для рисования счета
        //true - чтоб рисовалось с (0,0) и не было перевернуто
        font = new BitmapFont(true);
        font.setColor(0,0,0,1);
        font.getData().setScale((float) 1.3, (float) 1.3);

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
        background = AssetLoader.background;

        //загружаем картинку для мячика
        ballTexture = AssetLoader.ball;

        //загружаем картинку для препятствий
        barrierTexture = AssetLoader.barrier;
    }


    /**
     * Рисуем препятствия
     */
    private void drawBarriers() {
        for(Barrier barrier : barriers){
            batcher.draw(barrierTexture, barrier.getPart().getX(), barrier.getPosition().y,
                    barrier.getPart().getWidth(), barrier.getHeight());
        }
    }

    /**
     * Рисуем мячик
     */
    private void drawBall() {
        batcher.draw(ballTexture, ball.getPosition().x - ball.getDiameter() / 2, ball.getPosition().y,
                ball.getDiameter(), ball.getDiameter());
    }

    /**
     * Рисуем счет
     */
    private void drawScore() {
        font.draw(batcher, "Score: " + world.getScore(), 0, 0);
    }

    /**
     * Рисуем меню.
     */
    public void drawIfMenu() {
        for(SimpleButton button : buttons) {
            button.draw(batcher);
        }
    }

    private void drawIfReady() {
        font.draw(batcher, "Touch to start!", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 3 / 10);
    }

    private void drawIfGameOver() {
        if (world.isGameOver()) {
            font.draw(batcher, "Game Over", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 6 / 20);
            font.draw(batcher, "High Score:", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 7 / 20);
            String highScore = AssetLoader.getHighScore() + "";
            font.draw(batcher, highScore, GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 8 / 20);
        } else {
            font.draw(batcher, "Congratulations!", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 6 / 20);
            font.draw(batcher, "High Score!", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 7 / 20);
        }

        font.draw(batcher, "Your score:", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 9 / 20);
        String score = world.getScore() + "";
        font.draw(batcher, score, GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 10 / 20);
        font.draw(batcher, "Try again?", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 11 / 20);
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

        drawBarriers();

        //добавляем прозрачность, она нужна мячику
        batcher.enableBlending();

        drawBall();

        if(world.isMenu()) {
            drawIfMenu();
        } else if (world.isReady()) {
            drawIfReady();
        } else if (world.isRunning()) {
            drawScore();
        } else if (world.isGameOver() || world.isHighScore()) {
                drawIfGameOver();
        }

        //закрываем пакет
        batcher.end();
    }

    public void dispose() {
        batcher.dispose();
        font.dispose();
    }
}
