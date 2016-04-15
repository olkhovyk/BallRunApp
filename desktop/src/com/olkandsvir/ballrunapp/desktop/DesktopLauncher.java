package com.olkandsvir.ballrunapp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.olkandsvir.ballrunapp.BallRunGame;

/**
 * For launching the game on desktop.
 * @since 15.04.2016
 */
public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BallRunGame.WIDTH;
		config.height = BallRunGame.HEIGHT;
		config.title = BallRunGame.TITLE;
		new LwjglApplication(new BallRunGame(), config);

	}

}
