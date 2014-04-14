/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.gui.widgets;

import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author kjagiello
 */
public class InputHandler {

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The
     * button parameter will be {@link Buttons#LEFT} on Android.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button the button
     * @return whether the input was processed
     */
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The
     * button parameter will be {@link Buttons#LEFT} on Android.
     *
     * @param pointer the pointer for the event.
     * @param button the button
     * @return whether the input was processed
     */
    public boolean touchUp(float screenX, float screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param pointer the pointer for the event.
     * @return whether the input was processed
     */
    public boolean touchDragged(float screenX, float screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will
     * not be called on Android.
     *
     * @return whether the input was processed
     */
    public boolean mouseMoved(float screenX, float screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on Android.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the
     * wheel was scrolled.
     * @return whether the input was processed.
     */
    public boolean scrolled(int amount) {
        return false;
    }
}
