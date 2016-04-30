package com.olkandsvir.ballrunapp.gameobject.barriers;

/**
 * Line with one moving obstacle.
 * @since 30.04.2016
 */
public class MovingOnePieceBarrier extends AbstractBarrier {

    public MovingOnePieceBarrier(float x, float y, int height, float scrollSpeed) {
        super(x, y, height, scrollSpeed);

        parts.add(new BarrierPart(this, y, height));

        for(int i = 0; i < parts.size; i++) {
            if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT) {
                parts.get(i).setMoving(true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.MID) {
                double random = 2 * Math.random();
                if(random < 1) {
                    parts.get(i).setMoving(true, false);
                } else {
                    parts.get(i).setMoving(true, true);
                }
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i).setMoving(true);
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

        for(int i = 0; i < parts.size; i++) {
            if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT) {
                parts.get(i).setMoving(true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.MID) {
                double random = 2 * Math.random();
                if(random < 1) {
                    parts.get(i).setMoving(true, false);
                } else {
                    parts.get(i).setMoving(true, true);
                }
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i).setMoving(true);
            }
        }
    }
}
