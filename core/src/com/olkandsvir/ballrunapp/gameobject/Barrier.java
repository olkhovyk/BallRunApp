package com.olkandsvir.ballrunapp.gameobject;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.olkandsvir.ballrunapp.gameworld.GameRenderer;

/**
 * Ball's enemies, try to avoid them!
 * @since 17.04.2016
 */
public class Barrier {

    public static int GAP_WIDTH = GameRenderer.GAME_WIDTH / 3;

    private Vector2 position;
    private Vector2 speed;
    private int width;
    private int height;
    private float rightX;
    private boolean isScrolledBottom;
    private Rectangle leftSide;
    private Rectangle rightSide;

    public Barrier(float x, float y, int width, int height, float scrollSpeed){
        position = new Vector2(x, y);
        speed = new Vector2(0, scrollSpeed);
        this.width = width;
        this.height = height;
        this.rightX = GameRenderer.GAME_WIDTH;
        this.isScrolledBottom = false;
        leftSide = new Rectangle();
        rightSide = new Rectangle();

    }

/*    public void drawBarrier(float delta) {
        leftSide.set(position.x, position.y, width, height);
        rightSide.set(position.x + width + GAP_WIDTH, position.y, rightX - width - GAP_WIDTH, height);

    }
*/
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }
}
