package com.olkandsvir.ballrunapp.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Implementation of Menu Buttons.
 * @since 21.04.2014
 */
public class SimpleButton extends AbstractButton {

    //текстуры обычного и нажатого состояния кнопки
    private Texture buttonUp;
    private Texture buttonDown;

    //конструктор
    public SimpleButton(float x, float y, float width, float height,
                        Texture buttonUp, Texture buttonDown) {

        super(x, y, width, height);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
    }

    @Override
    public void draw(SpriteBatch batcher) {
        if (pressed) {
            batcher.draw(buttonDown, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        } else {
            batcher.draw(buttonUp, x, y, width, height, 0, 0, buttonUp.getWidth(), buttonUp.getHeight(), false, true);
        }
    }
}
