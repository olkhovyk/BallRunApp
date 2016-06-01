package com.olkandsvir.ballrunapp.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Implementation of Switch Buttons.
 * @since 31.05.2016
 */
public class SimpleSwitchButton extends AbstractButton {

    //текстуры обычного и нажатого состояния кнопки
    private Texture switchOn;
    private Texture switchOff;

    //переменная, хранящая состояние переключателя
    private boolean on;

    //конструктор
    public SimpleSwitchButton(float x, float y, float width, float height,
                        Texture switchOn, Texture switchOff) {
        super(x, y, width, height);
        this.switchOn = switchOn;
        this.switchOff = switchOff;

        on = true;
    }

    @Override
    public void draw(SpriteBatch batcher) {
        if (on) {
            batcher.draw(switchOn, x, y, width, height, 0, 0, switchOn.getWidth(), switchOn.getHeight(), false, true);
        } else {
            batcher.draw(switchOff, x, y, width, height, 0, 0, switchOff.getWidth(), switchOff.getHeight(), false, true);
        }
    }

    @Override
    public boolean isTouchUp(int screenX, int screenY) {
        if (isClicked(screenX, screenY) && pressed) {
            on = !on;
        }

        return super.isTouchUp(screenX, screenY);
    }
}

