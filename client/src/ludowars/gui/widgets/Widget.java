/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.gui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author kjagiello
 */
abstract public class Widget {
    protected Rectangle bounds = new Rectangle(0, 0, 0, 0);
    public InputHandler inputHandler = null;
    
    public void setPosition(float x, float y) {
        bounds.x = x;
        bounds.y = y;
    }
    
    public float getWidth() {
        return bounds.width;
    }
    
    public float getHeight() {
        return bounds.height;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public boolean keyUp(int keycode) {
        if (inputHandler != null) {
            return inputHandler.keyUp(keycode);
        }
        
        return false;
    }
    
    public boolean keyDown(int keycode) {
        if (inputHandler != null) {
            return inputHandler.keyDown(keycode);
        }
        
        return false;
    }
    
    public boolean keyTyped(char character) {
        if (inputHandler != null) {
            return inputHandler.keyTyped(character);
        }
        
        return false;
    }
    
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        if (inputHandler != null) {
            if (bounds.contains(screenX, screenY)) {
                return inputHandler.touchDown(screenX - bounds.x, screenY - bounds.y, pointer, button);
            }
        }
        
        return false;
    }
    
    public boolean touchUp(float screenX, float screenY, int pointer, int button) {
        if (inputHandler != null) {
            if (bounds.contains(screenX, screenY)) {
                return inputHandler.touchUp(screenX - bounds.x, screenY - bounds.y, pointer, button);
            }
        }
        
        return false;
    }
    
    public boolean touchDragged(float screenX, float screenY, int pointer) {
        if (inputHandler != null) {
            if (bounds.contains(screenX, screenY)) {
                return inputHandler.touchDragged(screenX - bounds.x, screenY - bounds.y, pointer);
            }
        }
        
        return false;
    }
    
    public boolean mouseMoved(float screenX, float screenY) {
//        if (inputHandler != null) {
//            if (bounds.contains(screenX, screenY)) {
//                inputHandler.touchDragged(screenX - bounds.x, screenY - bounds.y);
//            }
//        }
        
        return false;
    }
    
    public boolean scrolled(int amount) {
        // TODO: only when mouse over the element
//        if (inputHandler != null) {
//            if (bounds.contains(screenX, screenY)) {
//                inputHandler.scrolled(amount);
//            }
//        }
        
        return false;
    }
    
    abstract public void render(SpriteBatch batch, ShapeRenderer sr, float delta);
}
