package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.gameobject.barriers.AbstractBarrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.EasyBarrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.SuperEasyBarrier;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    public static final int ARRAY_SIZE = 5;
    private Array<AbstractBarrier> barriers;
    public static int barrierHeight = GameScreen.SCREEN_HEIGHT / 10;
    public static int scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
    public static int barrierGap = 4 * GameScreen.SCREEN_HEIGHT / 10;

    private final GameWorld world;

    public ScrollHandler(GameWorld world) {
        this.world = world;

        barriers = new Array<AbstractBarrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){

            //ДЛЯ ТЕСТОВ
            if(i == 0) {
//                barriers.add(new SuperEasyBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
                barriers.add(new EasyBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
//                barriers.add(new SuperEasyBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
                barriers.add(new EasyBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public void update(float delta) {
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);
//            levelUp();
            if (barriers.get(i).isScrolledBottom()) {
                world.addScore();
                AssetLoader.soundScored.play();
                speedChange(scrollSpeed / 50);
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

    /*
   public void levelUp() {
        if(world.getScore() == 5) {
            for(int i = 0; i < barriers.size; i++) {
                Gdx.app.log("score", Integer.toString(10) + " " + Integer.toString(i));
                if (barriers.get(i).isScrolledBottom()) {
                    if (i != 0) {
                        barriers.set(i, new EasyBarrier(0, barriers.get(i - 1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
                    } else {
                        barriers.set(i, new EasyBarrier(0, barriers.get(barriers.size - 1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
                    }
                }
            }
        }
    }
    */

    public void onRestart() {
        barriers.clear();
        scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            //ДЛЯ ТЕСТОВ
            if(i == 0) {
//                barriers.add(new SuperEasyBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
                barriers.add(new EasyBarrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
//                barriers.add(new SuperEasyBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
                barriers.add(new EasyBarrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public Array<AbstractBarrier> getBarriers() {
        return barriers;
    }

}
