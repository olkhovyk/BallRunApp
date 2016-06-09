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
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.BarrierPart;
import com.olkandsvir.ballrunapp.screens.GameScreen;
import com.olkandsvir.ballrunapp.ui.AbstractButton;
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
    private Texture background2;
    private Texture ballTexture;
    private Texture barrierTexture;

    //игровой мяч
    private Ball ball;

    //препятствия
    private Array<Barrier> barriers;

    //отвечает за движение препятствий
    private ScrollHandler handler;

    //шрифт
    private BitmapFont font;
    private FreeTypeFontGenerator generator;

    //кнопки меню
    private List<SimpleButton> menuButtons;
    private List<SimpleButton> pauseMenuButtons;
    private List<AbstractButton> optionsMenuButtons;
    private List<SimpleButton> gameOverMenuButtons;

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

        //инициализируем ресурсы, шрифт и игровые объекты
        initAssets();
        initFont();
        initGameObjects();
    }

    /**
     * Иницализируем игровые объекты.
     */
    private void initGameObjects() {
        menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
        optionsMenuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getOptionsMenuButtons();
        pauseMenuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getPauseMenuButtons();
        pauseButton = (((InputHandler) Gdx.input.getInputProcessor()).getPauseButton());
        gameOverMenuButtons = (((InputHandler)Gdx.input.getInputProcessor()).getGameOverButtons());
        backButton = (((InputHandler) Gdx.input.getInputProcessor()).getBackButton());
        ball = world.getBall();
        handler = world.getHandler();
        barriers = handler.getBarriers();

    }

    /**
     * Инициализируем дополнительные ресурсы.
     */
    private void initAssets() {

        //загружаем картинки фона
        background = AssetLoader.background;
        background2 = AssetLoader.background2;

        //загружаем картинку для мячика
        ballTexture = AssetLoader.ball;

        //загружаем картинку для препятствий
        barrierTexture = AssetLoader.barrier;
    }

    /**
     * Инициализируем шрифт
     */
    private void initFont(){
        /*
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
        */
        font = new BitmapFont(AssetLoader.fontFile, true);
        //font.setColor(0,0,0,1);
        font.getData().setScale((float) 1.3, (float) 1.3);
        //font.dispose();

    }

    /**
     * Рисуем препятствия
     */
    private void drawBarriers() {
        for(Barrier barrier : barriers){
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

    /**
     * Рисуем кнопку паузы
     */
    private void drawPause() {
        pauseButton.draw(batcher);
    }

    /**
     * Рисуем меню паузы
     */
    private void drawPauseMenu() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        for (SimpleButton button : pauseMenuButtons) {
            button.draw(batcher);
        }
    }

    /**
     * Рисуем меню.
     */
    private void drawIfMenu() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        for(SimpleButton button : menuButtons) {
            button.draw(batcher);
        }
    }

    /**
     * Рисуем настройки
     */
    private void drawIfOptions() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        backButton.draw(batcher);
        for(AbstractButton button : optionsMenuButtons){
            button.draw(batcher);
        }
    }

    /**
     * Рисуем лучшие результаты
     */
    private void drawIfBestResults() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        backButton.draw(batcher);

        font.draw(batcher, "Best results:", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 2 / 10);
        font.draw(batcher, "1 :  " + AssetLoader.preferences.getInteger("resultsOne"),
                GameScreen.SCREEN_WIDTH * 2/ 10, GameScreen.SCREEN_HEIGHT * 3 / 10);
        font.draw(batcher, "2 :  " + AssetLoader.preferences.getInteger("resultsTwo"),
                GameScreen.SCREEN_WIDTH * 2/ 10, GameScreen.SCREEN_HEIGHT * 4 / 10);
        font.draw(batcher, "3 :  " + AssetLoader.preferences.getInteger("resultsThree"),
                GameScreen.SCREEN_WIDTH * 2/ 10, GameScreen.SCREEN_HEIGHT * 5 / 10);
        font.draw(batcher, "4 :  " + AssetLoader.preferences.getInteger("resultsFour"),
                GameScreen.SCREEN_WIDTH * 2/ 10, GameScreen.SCREEN_HEIGHT * 6 / 10);
        font.draw(batcher, "5 :  " + AssetLoader.preferences.getInteger("resultsFive"),
                GameScreen.SCREEN_WIDTH * 2/ 10, GameScreen.SCREEN_HEIGHT * 7 / 10);
    }

    /**
     * Рисуем страницу авторов
     */
    private void drawIfAuthors() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        font.draw(batcher, "Credits", GameScreen.SCREEN_WIDTH * 4 / 10, GameScreen.SCREEN_HEIGHT * 2 / 10);
        font.draw(batcher, "Programming:", GameScreen.SCREEN_WIDTH * 2 / 10, GameScreen.SCREEN_HEIGHT * 6 / 20);
        font.draw(batcher, "Vsevolod Svirin", GameScreen.SCREEN_WIDTH * 5 / 20, GameScreen.SCREEN_HEIGHT * 7 / 20);
        font.draw(batcher, "Ilya Olkhovik", GameScreen.SCREEN_WIDTH * 5 / 20, GameScreen.SCREEN_HEIGHT * 8 / 20);
        font.draw(batcher, "Design:", GameScreen.SCREEN_WIDTH * 2 / 10, GameScreen.SCREEN_HEIGHT * 10 / 20);
        font.draw(batcher, "Daniel Bulavsky", GameScreen.SCREEN_WIDTH * 5 / 20, GameScreen.SCREEN_HEIGHT * 11 / 20);
        font.draw(batcher, "Thank you for", GameScreen.SCREEN_WIDTH * 2 / 10, GameScreen.SCREEN_HEIGHT * 13 / 20);
        font.draw(batcher, "choosing our app!", GameScreen.SCREEN_WIDTH * 2 / 10, GameScreen.SCREEN_HEIGHT * 14 / 20);
        font.draw(batcher, "Cheers!", GameScreen.SCREEN_WIDTH * 4 / 10, GameScreen.SCREEN_HEIGHT * 16 / 20);
        backButton.draw(batcher);
    }

    /**
     * Рисуем готовое игровое поле
     */
    private void drawIfReady() {
        font.draw(batcher, "Touch to start!", GameScreen.SCREEN_WIDTH * 3 / 10, GameScreen.SCREEN_HEIGHT * 3 / 10);
    }

    /**
     * Рисуем игру в действии
     */
    private void drawIfRunning() {
        drawScore();
        if(world.isRunning()) {
            drawPause();
        } else {
            drawPauseMenu();
        }
    }

    /**
     * Рисуем конец игры
     */
    private void drawIfGameOver() {
        batcher.draw(background2, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        for (SimpleButton button : gameOverMenuButtons) {
            button.draw(batcher);
        }

        if (world.isGameOver()) {
            font.draw(batcher, "Game Over", GameScreen.SCREEN_WIDTH * 4 / 15, GameScreen.SCREEN_HEIGHT * 3 / 15);
        } else {
            font.draw(batcher, "High Score!", GameScreen.SCREEN_WIDTH * 4 / 15, GameScreen.SCREEN_HEIGHT * 3 / 15);
            font.draw(batcher, "Congratulations!", GameScreen.SCREEN_WIDTH * 4 / 15, GameScreen.SCREEN_HEIGHT * 4 / 15);
        }

        String score = world.getScore() + "";
        font.draw(batcher, "Your score:  " + score, GameScreen.SCREEN_WIDTH * 4 / 15, GameScreen.SCREEN_HEIGHT * 5 / 15);
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

        //рисуем барьеры
        drawBarriers();

        //добавляем прозрачность, она нужна мячику
        batcher.enableBlending();

        //рисуем мячик
        drawBall();

        //рисуем оставшуюся игру в зависимости от ее нынешнего состояния
        if(world.isMenu()) {
            drawIfMenu();
        } else if (world.isOptions()) {
            drawIfOptions();
        } else if(world.isAuthors()) {
            drawIfAuthors();
        } else if(world.isBestResults()) {
            drawIfBestResults();
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
