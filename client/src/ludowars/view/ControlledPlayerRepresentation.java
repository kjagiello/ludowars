/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ludowars.model.EntityData;
import ludowars.model.State;

/**
 * Handling camera of a controlled entity.
 * @author kjagiello
 */
public class ControlledPlayerRepresentation extends PlayerRepresentation {

    @Override
    public void render(State S, SpriteBatch batch, ShapeRenderer sr, OrthographicCamera camera) {
        super.render(S, batch, sr, camera);
        
        EntityData data = getData();

        int camMinX = (int)(S.worldBounds.x + camera.viewportWidth / 2);
        int camMinY = (int)(S.worldBounds.y + camera.viewportHeight / 2);
        int camMaxX = (int)(S.worldBounds.width - camera.viewportWidth / 2);
        int camMaxY = (int)(S.worldBounds.height - camera.viewportHeight / 2);
        
        int cameraX = (int)Math.max(camMinX, Math.min(camMaxX, data.position.x));
        int cameraY = (int)Math.max(camMinY, Math.min(camMaxY, data.position.y));
        
        float lerp = 0.5f;
        camera.position.x += (int)((cameraX - camera.position.x) * lerp);
        camera.position.y += (int)((cameraY - camera.position.y) * lerp);
    }

    
}
