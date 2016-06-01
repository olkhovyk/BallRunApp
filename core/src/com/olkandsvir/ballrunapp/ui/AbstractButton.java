package com.olkandsvir.ballrunapp.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.olkandsvir.ballrunapp.brhelpers.SoundHandler;

/**
 * Implementation of Buttons.
 * @since 31.05.2016
 */
public abstract class AbstractButton {

    //координаты кнопки, ее ширина и высота
    protected float x, y, width, height;

    //границы кнопки
    protected Rectangle bounds;

    //нажатие кнопки
    protected boolean pressed;

    public AbstractButton(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        pressed = false;

        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Проверяет, была ли нажата ли кнопка.
     */
    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    /**
     * Рисование кнопки.
     */
    public abstract void draw(SpriteBatch batcher);

    /**
     * Вызывается при прекращении нажатия на экран.
     */
    public boolean isTouchUp(int screenX, int screenY) {

        if (isClicked(screenX, screenY) && pressed) {
            pressed = false;
            return true;
        }

        pressed = false;
        return false;
    }

    /**
     * Вызывается при нажатиии на экран.
     */
    public boolean isTouchDown(int screenX, int screenY) {

        if (isClicked(screenX, screenY)) {
            pressed = true;
            SoundHandler.playSoundClicked();
            return true;
        }
        return false;
    }
}
