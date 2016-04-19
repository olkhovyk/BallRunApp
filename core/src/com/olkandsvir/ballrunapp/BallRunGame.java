package com.olkandsvir.ballrunapp;

import com.badlogic.gdx.Game;
import com.olkandsvir.ballrunapp.brhelpers.AssetsLoader;
import com.olkandsvir.ballrunapp.screens.GameScreen;
import com.olkandsvir.ballrunapp.screens.MenuScreen;

/**
 * Main class of BallRun game.
 * @since 15.04.2016
 */
public class BallRunGame extends Game {

	public static final String TITLE = "BallRun";
    private GameScreen screen;
    private MenuScreen menuScreen;
	
	@Override
	public void create () {
        AssetsLoader.load();
     //   screen = new GameScreen();
      //  setScreen(screen);

        menuScreen = new MenuScreen();
        setScreen(menuScreen);
	}

    public void dispose() {
        super.dispose();
        AssetsLoader.dispose();
      //  screen.dispose();
        menuScreen.dispose();
    }

}
