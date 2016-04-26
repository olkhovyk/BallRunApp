package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.olkandsvir.ballrunapp.gameobject.Ball;

/**
 * Line with two easy obstacles.
 * @since 26.04.2016
 */
public class EasyBarrier extends AbstractBarrier {

    public EasyBarrier(float x, float y, int height, float scrollSpeed) {
        super(x, y, height, scrollSpeed);

        parts.add(new BarrierPart(this, y, height));
        parts.add(new BarrierPart(this, y, height));

        for (int i = 1; i < parts.size; i++) {
            while (parts.get(i).getOrientation() == parts.get(i- 1).getOrientation()) {
                parts.get(i).newOrientation();
            }
        }
    }

    @Override
    public void moveToLast(float newY) {
        super.moveToLast(newY);

        for (int i = 1; i < parts.size; i++) {
            while (parts.get(i).getOrientation() == parts.get(i - 1).getOrientation()) {
                parts.get(i).newOrientation();
            }
        }
    }
}