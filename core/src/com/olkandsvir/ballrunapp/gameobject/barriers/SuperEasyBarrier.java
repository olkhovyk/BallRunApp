package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.utils.Array;

/**
 * Line with one easy obstacle.
 * @since 17.04.2016
 */
public class SuperEasyBarrier extends AbstractBarrier {

    public SuperEasyBarrier(float x, float y, int height, float scrollSpeed) {
        super(x, y, height, scrollSpeed);

        parts.add(new BarrierPart(this, y, height));
    }
}
