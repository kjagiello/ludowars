/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.controller;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Ignatio
 */
public class EntityDriver {
    public EntityDriverState state;

    public EntityDriver() {
        state = new EntityDriverState();
        state.moveEast = false;
        state.moveNorth = false;
        state.moveSouth = false;
        state.moveWest = false;
        state.fire = false;
        state.fireSecondary = false;
        state.mousePosition = Vector2.Zero;
    }
    
    public void refresh(OrthographicCamera camera) {
        
    }
    
    protected void reset() {
        state.moveEast = false;
        state.moveNorth = false;
        state.moveSouth = false;
        state.moveWest = false;
        state.fire = false;
        state.fireSecondary = false;
        state.mousePosition = Vector2.Zero;
    }

    public Vector2 getAccelerationVector() {
        return new Vector2(getAccelerationX(), getAccelerationY());
    }
    
    protected Vector2 getWorldMousePosition(OrthographicCamera camera, int x, int y) {
        Vector3 m = new Vector3(x, y, 0f);
        camera.unproject(m);
        return new Vector2(m.x, m.y);
    }

    private int getAccelerationX() {
        int result = 0;
        
        result += state.moveEast ? 1 : 0;
        result -= state.moveWest ? 1 : 0;
        
        return result;
    }

    private int getAccelerationY() {
        int result = 0;
        
        result += state.moveNorth ? 1 : 0;
        result -= state.moveSouth ? 1 : 0;
        
        return result;
    }
}
