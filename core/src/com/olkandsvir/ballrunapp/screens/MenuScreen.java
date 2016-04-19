package com.olkandsvir.ballrunapp.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Created by ilya on 19.04.2016.
 */
public class MenuScreen extends Game implements Screen   {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture background;
    private GameScreen game;
    private Texture buttonTexture;

    Button button = new Button();

    public MenuScreen(SpriteBatch batch) {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameRenderer.GAME_WIDTH, GameRenderer.GAME_HEIGHT);
        batch.setProjectionMatrix(camera.combined);

        background = AssetsLoader.background;
    }

    public MenuScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameRenderer.GAME_WIDTH, GameRenderer.GAME_HEIGHT);
        batch.setProjectionMatrix(camera.combined);

        background = AssetsLoader.background;
        buttonTexture = AssetsLoader.buttonStart;



    }

    @Override
    public void show() {

        button.setPosition(100, 100);
        button.setSize(300,300);
        button.setVisible(true);
        button.setColor(0,0,0,1);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                create();
            }
        });
    }

    @Override
    public void render(float delta) {
        //устанавливаем черный фон
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //запускаем пакет рисования
        batch.begin();

        //убираем прозрачность, полезно для производительности
        batch.disableBlending();

        //рисуем фон
        batch.draw(background, 0, 0);
        batch.enableBlending();
        batch.draw(buttonTexture, GameRenderer.GAME_WIDTH / 2 - buttonTexture.getWidth() / 5, GameRenderer.GAME_HEIGHT / 8, GameRenderer.GAME_WIDTH/ 2,
                GameRenderer.GAME_HEIGHT / 6);
        batch.end();
    }

    @Override
    public void create() {
        game = new GameScreen();
        setScreen(game);
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
        batch.dispose();

    }
}
