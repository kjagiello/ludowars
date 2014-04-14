/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import ludowars.controller.EntityDriver;

/**
 *
 * @author IgnatioQ
 */
public class PlayerDriver extends EntityDriver {
    private long lastFire = 0;
    private long lastPower = 0;
    
    @Override
    public void refresh(OrthographicCamera camera) {
        reset();

        state.mousePosition = getWorldMousePosition(camera, Gdx.input.getX(), Gdx.input.getY());
        state.moveEast = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        state.moveSouth = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);  
        state.moveWest = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        state.moveNorth = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
        
        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFire > 600) {
                state.fire = true;
                lastFire = currentTime;
            }
        }
        if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
            long currentTime2 = System.currentTimeMillis();
            if (currentTime2 - lastPower > 1200) {
                state.fireSecondary = true;
                lastPower = currentTime2;
            }
        }
    }
}
