package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    private static final int ARRAY_SIZE = 5;
    private Array<Barrier> barriers;
    private static int barrierHeight = GameScreen.SCREEN_HEIGHT / 10;
    private static int scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
    private static int barrierGap = 4 * GameScreen.SCREEN_HEIGHT / 10;

    private final GameWorld world;

    public ScrollHandler(GameWorld world) {
        this.world = world;

        barriers = new Array<Barrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){

            if(i == 0) {
                barriers.add(new Barrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
                barriers.add(new Barrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public void update(float delta) {
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);
            if (barriers.get(i).isScrolledBottom()) {
                world.addScore();
                AssetLoader.soundScored.play();
                speedChange(scrollSpeed / 100);

                if (world.getScore() % 5 == 0 && 4 * world.getBall().getDiameter() < barrierGap) {
                    gapChange(- barrierGap / 25);
                }

                if (i != 0) {
                    barriers.get(i).moveToLast(barriers.get(i - 1).getPosition().y - barrierGap);
                } else {
                    barriers.get(i).moveToLast(barriers.get(barriers.size - 1).getPosition().y - barrierGap);
                }

                if(world.getScore() > 5 && world.getScore() < 11) {
                    barriers.get(i).startMoving();
                }

                if(world.getScore() > 15 && world.getScore() < 21) {
                    barriers.get(i).stopMoving();
                    barriers.get(i).addPart();
                }

                if(world.getScore() > 25 && world.getScore() < 31) {
                    barriers.get(i).startMoving();
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
        for (Barrier barrier : barriers) {
            barrier.stop();
        }
    }

    public void speedChange(int increment) {
        for (Barrier barrier : barriers) {
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
                barriers.add(new Barrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            } else {
                barriers.add(new Barrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public Array<Barrier> getBarriers() {
        return barriers;
    }

}
