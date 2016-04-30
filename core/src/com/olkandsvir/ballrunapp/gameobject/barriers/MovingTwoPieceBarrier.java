package com.olkandsvir.ballrunapp.gameobject.barriers;

/**
 * Line with one static and one moving obstacles.
 * @since 30.04.2016
 */
public class MovingTwoPieceBarrier extends AbstractBarrier {

    public MovingTwoPieceBarrier (float x, float y, int height, float scrollSpeed) {
        super(x, y, height, scrollSpeed);

        parts.add(new BarrierPart(this, y, height));
        parts.add(new BarrierPart(this, y, height));

        for (int i = 1; i < parts.size; i++) {
            while (parts.get(i).getOrientation() == parts.get(i- 1).getOrientation()) {
                parts.get(i).newOrientation();
            }

            if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.MID) {
                parts.get(i - 1).setMoving(true, true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                double random = 2 * Math.random();
                if (random < 1) parts.get(i).setMoving(true);
                else parts.get(i - 1).setMoving(true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.MID &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i).setMoving(true, false);
            }  else  if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.MID) {
                parts.get(i).setMoving(true, true);
            } else if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                double random = 2 * Math.random();
                if (random < 1) parts.get(i - 1).setMoving(true);
                else parts.get(i).setMoving(true);
            } else if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.MID &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i - 1).setMoving(true, false);
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

            if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.MID) {
                parts.get(i - 1).setMoving(true, true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                double random = 2 * Math.random();
                if (random < 1) parts.get(i).setMoving(true);
                else parts.get(i - 1).setMoving(true);
            } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.MID &&
                    parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i).setMoving(true, false);
            } else  if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.MID) {
                parts.get(i).setMoving(true, true);
            } else if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.LEFT &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                double random = 2 * Math.random();
                if (random < 1) parts.get(i - 1).setMoving(true);
                else parts.get(i).setMoving(true);
            } else if (parts.get(i - 1).getOrientation() == BarrierPart.partOrientation.MID &&
                    parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                parts.get(i - 1).setMoving(true, false);
            }
        }
    }
}
