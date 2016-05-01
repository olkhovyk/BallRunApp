package com.olkandsvir.ballrunapp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.brhelpers.InputHandler;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.gameobject.ScrollHandler;
import com.olkandsvir.ballrunapp.gameobject.barriers.AbstractBarrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.BarrierPart;
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
    private Array<AbstractBarrier> barriers;

    //отвечает за движение препятствий
    private ScrollHandler handler;

    //шрифт
    private BitmapFont font;
    private FreeTypeFontGenerator generator;

    //Кнопки меню
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> pauseMenuButtons;

    //кнопки
    private SimpleButton pauseButton;
    private SimpleButton backButton;

    //конструктор
    public GameRenderer(GameWorld world) {

        //создаем игровой мир
        this.world = world;

        //инициализируем камеру
        camera = new OrthographicCamera();

        //первый параметр устанавливает (0;0) координату в левый верхний угол
        //второй и третий отвечает за ширину и высоту
        camera.setToOrtho(true, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);

        //инициализируем пакет для рисования
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        //инициализируем пакет для рисования счета
        font = new BitmapFont();
        //Инициализируем генератор для вставки своего шрифта
        generator = new FreeTypeFontGenerator(AssetLoader.fontFile);
        //Создаем параметры для шрифта
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Переворачиваем шрифт для правильного отображения
        parameter.flip = true;
        //Устанавливаем размер шрифта
        parameter.size = GameScreen.SCREEN_WIDTH / 22;
        //Привязываем новый шрифт к BitmapFont
        font = generator.generateFont(parameter);

        font.setColor(0,0,0,1);
        font.getData().setScale((float) 1.3, (float) 1.3);
        generator.dispose();


        initGameObjects();
        initAssets();
    }

    /**
     * Иницализируем игровые объекты.
     */
    private void initGameObjects() {
        menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
        pauseMenuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getPauseMenuButtons();
        pauseButton = (((InputHandler) Gdx.input.getInputProcessor()).getPauseButton());
        backButton = (((InputHandler) Gdx.input.getInputProcessor()).getBackButton());
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
        for(AbstractBarrier barrier : barriers){
            for(BarrierPart part : barrier.getParts()) {
                batcher.draw(barrierTexture, part.getX(), barrier.getPosition().y,
                        part.getWidth(), barrier.getHeight());
            }
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

    private void drawPause() {
        pauseButton.draw(batcher);
    }

    private void drawPauseMenu() {
        for (SimpleButton button : pauseMenuButtons) {
            button.draw(batcher);
        }
    }

    /**
     * Рисуем меню.
     */
    private void drawIfMenu() {
        for(SimpleButton button : menuButtons) {
            button.draw(batcher);
        }
    }

    private void drawIfOptions() {
        //TO DO
        backButton.draw(batcher);
    }

    private void drawIfAuthors() {
        //TO DO
    }

    private void drawIfReady() {
        font.draw(batcher, "Touch to start!", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 3 / 10);
    }

    private void drawIfRunning() {
        drawScore();
        if(world.isRunning()) {
            drawPause();
        } else {
            drawPauseMenu();
        }
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
        } else if (world.isOptions()) {
            drawIfOptions();
        } else if(world.isAuthors()) {
            drawIfAuthors();
        } else if (world.isReady()) {
            drawIfReady();
        } else if (world.isRunning() || world.isPaused()) {
            drawIfRunning();
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
