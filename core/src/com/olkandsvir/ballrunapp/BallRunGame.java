package com.olkandsvir.ballrunapp;

import com.badlogic.gdx.Game;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Main class of BallRun game.
 * @since 15.04.2016
 */
public class BallRunGame extends Game {

//	public static final int WIDTH = 1080;
//	public static final int HEIGHT = 1920;
	public static final String TITLE = "BallRun";
    private GameScreen screen;
	
	@Override
	public void create () {
        AssetsLoader.load();
        screen = new GameScreen();
        setScreen(screen);

	}

    public void dispose() {
        super.dispose();
        AssetsLoader.dispose();
        screen.dispose();

    }

}
