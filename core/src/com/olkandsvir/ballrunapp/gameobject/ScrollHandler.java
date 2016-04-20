package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameworld.GameWorld;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    public static final int ARRAY_SIZE = 5;
    private Array<Barrier> barriers;
    public static int barrierHeight = GameScreen.SCREEN_HEIGHT / 10;
    public static int scrollSpeed = GameScreen.SCREEN_HEIGHT / 2;
    public static int barrierGap = 4 * GameScreen.SCREEN_HEIGHT / 10;

    private final GameWorld world;

    public ScrollHandler(GameWorld world) {
        this.world = world;

        barriers = new Array<Barrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){
            if(i == 0){
                barriers.add(new Barrier(0, 0 - GameScreen.SCREEN_HEIGHT / 4, barrierHeight, scrollSpeed));
            }
            else barriers.add(new Barrier(0, barriers.get(i-1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
        }
    }

    public void update(float delta) {
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);
            if(barriers.get(i).isScrolledBottom()) {
                world.addScore();
                barriers.removeIndex(i);
                barriers.add(new Barrier(0, barriers.get(barriers.size - 1).getPosition().y - barrierGap, barrierHeight, scrollSpeed));
            }
        }
    }

    public Array<Barrier> getBarriers() {
        return barriers;
    }

    public boolean collides(Ball ball) {
        for (int i = 0; i < barriers.size; i++) {
            if(barriers.get(i).collides(ball)) {
                return true;
            }
        }
        return false;
    }
}
