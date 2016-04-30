package com.olkandsvir.ballrunapp.gameobject.barriers;

/**
 * Line with one static obstacle.
 * @since 17.04.2016
 */
public class StaticOnePieceBarrier extends AbstractBarrier {

    public StaticOnePieceBarrier(float x, float y, int height, float scrollSpeed) {
        super(x, y, height, scrollSpeed);

        parts.add(new BarrierPart(this, y, height));
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        for(BarrierPart part : parts) {
            part.update(delta);
        }
    }
}
