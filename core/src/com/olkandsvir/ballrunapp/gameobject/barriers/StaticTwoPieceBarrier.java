package com.olkandsvir.ballrunapp.gameobject.barriers;

/**
 * Line with two static obstacles.
 * @since 26.04.2016
 */
public class StaticTwoPieceBarrier extends AbstractBarrier {

    public StaticTwoPieceBarrier(float x, float y, int height, float scrollSpeed) {
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
    public void update(float delta) {
        super.update(delta);

        for(BarrierPart part : parts) {
            part.update(delta);
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