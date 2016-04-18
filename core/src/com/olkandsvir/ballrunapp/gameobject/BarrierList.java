package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.barriers.Barrier;
import com.olkandsvir.ballrunapp.gameobject.barriers.BarrierPart;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * List of all possible barriers.
 * @since 18.04.2016
 */
public class BarrierList<T> extends Array<Barrier> implements Iterable<Barrier> {

    public BarrierList() {
        super();

        this.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 10, GameRenderer.GAME_HEIGHT / 10,
                ScrollHandler.SCROLL_SPEED, BarrierPart.partOrientation.LEFT));
        this.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 10, GameRenderer.GAME_HEIGHT / 10,
                ScrollHandler.SCROLL_SPEED, BarrierPart.partOrientation.MID));
        this.add(new Barrier(0, 0 - GameRenderer.GAME_HEIGHT / 10, GameRenderer.GAME_HEIGHT / 10,
                ScrollHandler.SCROLL_SPEED, BarrierPart.partOrientation.RIGHT));
    }
}
