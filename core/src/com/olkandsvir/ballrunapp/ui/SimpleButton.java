package com.olkandsvir.ballrunapp.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.olkandsvir.ballrunapp.brhelpers.AssetLoader;
import com.olkandsvir.ballrunapp.brhelpers.InputHandler;
import com.olkandsvir.ballrunapp.brhelpers.SoundHandler;

/**
 * Implementation of Menu Buttons.
 * @since 21.04.2014
 */
public class SimpleButton {

    //координаты кнопки, ее ширина и высота
    private float x, y, width, height;

    //текстуры обычного и нажатого состояния кнопки
    private Texture buttonUp;
    private Texture buttonDown;

    //границы кнопки
    private Rectangle bounds;

    //нажатие кнопки
    private boolean isPressed = false;

    //конструктор
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

    /**
     * Проверяет, была ли нажата ли кнопка.
     */
    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    /**
     * Рисование кнопки.
     */
    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        } else {
            batcher.draw(buttonUp, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        }
    }

    /**
     * Вызывается при нажатиии на экран.
     */
    public boolean isTouchDown(int screenX, int screenY) {

        if (isClicked(screenX, screenY)) {
            isPressed = true;
            SoundHandler.playSoundClicked();
            return true;
        }
        return false;
    }

    /**
     * Вызывается при прекращении нажатия на экран.
     */
    public boolean isTouchUp(int screenX, int screenY) {

        if (isClicked(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        isPressed = false;
        return false;
    }
}
