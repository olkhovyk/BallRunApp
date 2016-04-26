package com.olkandsvir.ballrunapp.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;

/**
 * Implementation of Menu Buttons.
 * @since 21.04.2014
 */
public class SimpleButton {

    private float x, y, width, height;

    private Texture buttonUp;
    private Texture buttonDown;

    private Rectangle bounds;

    private boolean isPressed = false;

    public SimpleButton(float x, float y, float width, float height,
                        Texture buttonUp, Texture buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);
    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        } else {
            batcher.draw(buttonUp, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (isClicked(screenX, screenY)) {
            isPressed = true;
            AssetLoader.soundClicked.play();
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        if (isClicked(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        isPressed = false;
        return false;
    }
}
