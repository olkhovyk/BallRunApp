package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Responsible for moving Barriers.
 * @since 18.04.2016
 */
public class ScrollHandler {
    //public static final int ARRAY_SIZE = 100;
    //private Array<Barrier> barriers;
    public static final int SCROLL_SPEED = GameRenderer.GAME_HEIGHT / 3;
    public static final int BARRIER_GAP = 4 * GameRenderer.GAME_HEIGHT / 10;
    public static final BarrierList<Barrier> BARRIER_LIST = new BarrierList<Barrier>();
    private Array<Barrier> barriers = new Array<Barrier>();

    public ScrollHandler() {
//        barriers = new Array<Barrier>(true, ARRAY_SIZE);
//        for(int i = 0; i < ARRAY_SIZE; i++){
//            if(i == 0){
//               barriers.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 4, GameRenderer.GAME_HEIGHT / 10, SCROLL_SPEED));
//            }
//            else barriers.add(new Barrier(0, barriers.get(i-1).getPosition().y - BARRIER_GAP, GameRenderer.GAME_HEIGHT /10, SCROLL_SPEED));
//        }

        barriers.add(BARRIER_LIST.random());
    }

    public void update(float delta) {
//        for(Barrier barrier : barriers){
//            barrier.update(delta);
        for (int i = 0; i < barriers.size; i++) {
            barriers.get(i).update(delta);

            //не работает!
            if (barriers.get(i).getPosition().y == BARRIER_GAP) {
                barriers.add(BARRIER_LIST.random());
            }

            if (barriers.get(i).isScrolledBottom()) {
                barriers.removeIndex(i);
                barriers.add(BARRIER_LIST.random());
            }
        }
    }

        //Это я не знаю как засунуть в цикл
/*        if (barrier1.isScrolledBottom()) {
            barrier1.reset(barrier3.getPosition().y - BARRIER_GAP);
        } else if (barrier2.isScrolledBottom()) {
            barrier2.reset(barrier1.getPosition().y - BARRIER_GAP);
        } else if (barrier3.isScrolledBottom()) {
            barrier3.reset(barrier2.getPosition().y - BARRIER_GAP);
        }
    } */

    public Array<Barrier> getBarriers() {
        return barriers;
    }
}
