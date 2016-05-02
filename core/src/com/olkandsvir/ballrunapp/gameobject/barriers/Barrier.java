package com.olkandsvir.ballrunapp.gameobject.barriers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.olkandsvir.ballrunapp.gameobject.Ball;
import com.olkandsvir.ballrunapp.screens.GameScreen;

/**
 * Line with obstacles moving down.
 * @since 26.04.2016
 */
public class Barrier {

    private Vector2 position;
    private Vector2 speed;
    private int height;
    private boolean scrolledBottom;
    private boolean moving;
    protected Array<BarrierPart> parts;

    public Barrier(float x, float y, int height, float scrollSpeed){
        position = new Vector2(x, y);
        this.height = height;
        speed = new Vector2(0, scrollSpeed);
        scrolledBottom = false;
        parts = new Array<BarrierPart>();

        parts.add(new BarrierPart(this, y, height));
        moving = false;
    }

    public void update(float delta) {
        position.add(speed.cpy().scl(delta));

        if(position.y > GameScreen.SCREEN_HEIGHT) {
            scrolledBottom = true;
        }

        for(BarrierPart part : parts) {
            part.update(delta);
        }
    }

    public boolean isScrolledBottom() {
        return scrolledBottom;
    }

    public void moveToLast(float newY) {
        this.position.y = (int)newY;
        scrolledBottom = false;

        for(BarrierPart part : parts) {
            part.newOrientation();
        }

        if(parts.size > 1) {
            for (int i = 1; i < parts.size; i++) {
                while (parts.get(i).getOrientation() == parts.get(i - 1).getOrientation()) {
                    parts.get(i).newOrientation();
                }
            }
        }

        if (moving) startMoving();
    }

    public void stop() {
        speed.y = 0;
    }

    public void speedChange(int increment) {
        speed.y += increment;
    }

    public void startMoving() {
        moving = true;

        switch (parts.size) {
            case 1:
                for (int i = 0; i < parts.size; i++) {
                    if (parts.get(i).getOrientation() == BarrierPart.partOrientation.LEFT) {
                        parts.get(i).setMoving(true);
                    } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.MID) {
                        double random = 2 * Math.random();
                        if (random < 1) {
                            parts.get(i).setMoving(true, false);
                        } else {
                            parts.get(i).setMoving(true, true);
                        }
                    } else if (parts.get(i).getOrientation() == BarrierPart.partOrientation.RIGHT) {
                        parts.get(i).setMoving(true);
                    }
                }

            case 2:
                for (int i = 1; i < parts.size; i++) {
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

    public void stopMoving() {
         moving = false;

        for (BarrierPart part : parts) {
            part.setMoving(false);
        }
    }

    public void addPart() {
        parts.add(new BarrierPart(this, position.y, height));

        for (int i = 1; i < parts.size; i++) {
            while (parts.get(i).getOrientation() == parts.get(i- 1).getOrientation()) {
                parts.get(i).newOrientation();
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collides(Ball ball) {
        for (BarrierPart part : parts) {
            if (part.collides(ball)) {
                return true;
            }
        }

        return false;
    }

    public Array<BarrierPart> getParts() {
        return parts;
    }

    public Vector2 getSpeed() {
        return speed;
    }
}
