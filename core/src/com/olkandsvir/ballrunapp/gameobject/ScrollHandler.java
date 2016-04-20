package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

import java.util.Iterator;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    public static final int ARRAY_SIZE = 5;
    private Array<Barrier> barriers;
    public static final int SCROLL_SPEED = GameRenderer.GAME_HEIGHT / 2;
    public static final int BARRIER_GAP = 4 * GameRenderer.GAME_HEIGHT / 10;

    public ScrollHandler() {
        barriers = new Array<Barrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){
            if(i == 0){
                barriers.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 4, GameRenderer.GAME_HEIGHT / 10, SCROLL_SPEED));
            }
            else barriers.add(new Barrier(0, barriers.get(i-1).getPosition().y - BARRIER_GAP, GameRenderer.GAME_HEIGHT /10, SCROLL_SPEED));
        }
    }

    public void update(float delta) {
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);
            if(barriers.get(i).isScrolledBottom()) {
                barriers.removeIndex(i);
                barriers.add(new Barrier(0, barriers.get(barriers.size - 1).getPosition().y - BARRIER_GAP, GameRenderer.GAME_HEIGHT / 10, SCROLL_SPEED));
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
