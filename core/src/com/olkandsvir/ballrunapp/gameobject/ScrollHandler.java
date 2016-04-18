package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    public static final int ARRAY_SIZE = 100;
    private Array<Barrier> b;
    public static final int SCROLL_SPEED = GameRenderer.GAME_HEIGHT / 2;
    public static final int BARRIER_GAP = 4 * GameRenderer.GAME_HEIGHT / 10;



    public ScrollHandler() {
        b = new Array<Barrier>();
        for(int i = 0; i < ARRAY_SIZE; i++){
            if(i == 0){
                b.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 4, GameRenderer.GAME_HEIGHT / 10, SCROLL_SPEED));
            }
            else b.add(new Barrier(0, b.get(i-1).getPosition().y - BARRIER_GAP, GameRenderer.GAME_HEIGHT /10, SCROLL_SPEED));
        }

    }

    public void update(float delta) {
        for(Barrier barrier : b){
            barrier.update(delta);
        }


        //Это я не знаю как засунуть в цикл
/*        if (barrier1.isScrolledBottom()) {
            barrier1.reset(barrier3.getPosition().y - BARRIER_GAP);
        } else if (barrier2.isScrolledBottom()) {
            barrier2.reset(barrier1.getPosition().y - BARRIER_GAP);
        } else if (barrier3.isScrolledBottom()) {
            barrier3.reset(barrier2.getPosition().y - BARRIER_GAP);
        } */
    }

    public Array<Barrier> getB() {
        return b;
    }
}
