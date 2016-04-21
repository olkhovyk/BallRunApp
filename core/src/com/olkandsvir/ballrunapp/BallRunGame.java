package com.olkandsvir.ballrunapp;

import com.badlogic.gdx.Game;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Main class of BallRun game.
 * @since 15.04.2016
 */
public class BallRunGame extends Game {

	public static final String TITLE = "BallRun";
    private GameScreen gameScreen;
	
	@Override
	public void create () {
        AssetsLoader.load();
        gameScreen = new GameScreen();
        setScreen(gameScreen);
	}

    public void dispose() {
        super.dispose();
        AssetsLoader.dispose();
        gameScreen.dispose();
    }

}
