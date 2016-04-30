package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.gameobject.barriers.AbstractBarrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.StaticTwoPieceBarrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.StaticOnePieceBarrier;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    private static final int ARRAY_SIZE = 5;
    private Array<AbstractBarrier> barriers;
    private static int barrierHeight = GameScreen.SCREEN_HEIGHT / 10;
    private static int scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
    private static int barrierGap = 4 * GameScreen.SCREEN_HEIGHT / 10;

    private final GameWorld world;

    public ScrollHandler(GameWorld world) {
        this.world = world;

        barriers = new Array<AbstractBarrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){

            if(i == 0) {
                barriers.add(new StaticOnePieceBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
                barriers.add(new StaticOnePieceBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public void update(float delta) {
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);
            if (barriers.get(i).isScrolledBottom()) {
                world.addScore();
                AssetLoader.soundScored.play();
                speedChange(scrollSpeed / 50);
                if(world.getScore() > 5 && world.getScore() < 11) {
                    if (i != 0) {
                        barriers.set(i, new StaticTwoPieceBarrier(0, barriers.get(i - 1).getPosition().y - barrierGap,
                                barrierHeight, scrollSpeed));
                    } else {
                        barriers.set(i, new StaticTwoPieceBarrier(0, barriers.get(barriers.size - 1).getPosition().y - barrierGap,
                                barrierHeight, scrollSpeed));
                    }
                }

                //Пока пусть так, чтобы видеть, что работает, потом немного упростить
                if (world.getScore() % 2 == 0 && 4 * world.getBall().getDiameter() < barrierGap) {
                    Gdx.app.log("Gap", Integer.toString(barrierGap));
                    Gdx.app.log("Diameter", Integer.toString(world.getBall().getDiameter()));
                    gapChange(- barrierGap / 10);
                }

                if (i != 0) {
                    barriers.get(i).moveToLast(barriers.get(i - 1).getPosition().y - barrierGap);
                } else {
                    barriers.get(i).moveToLast(barriers.get(barriers.size - 1).getPosition().y - barrierGap);
                }
            }
        }
    }

    public boolean collides(Ball ball) {
        for (int i = 0; i < barriers.size; i++) {
            if(barriers.get(i).collides(ball)) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        for (AbstractBarrier barrier : barriers) {
            barrier.stop();
        }
    }

    public void speedChange(int increment) {
        for (AbstractBarrier barrier : barriers) {
            barrier.speedChange(increment);
        }
        scrollSpeed += increment;
    }

    public void gapChange(int increment) {
        barrierGap += increment;
    }

    public void onRestart() {
        barriers.clear();
        scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
        barrierGap = 4 * GameScreen.SCREEN_HEIGHT / 10;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if(i == 0) {
                barriers.add(new StaticOnePieceBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
                barriers.add(new StaticOnePieceBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public Array<AbstractBarrier> getBarriers() {
        return barriers;
    }

}
