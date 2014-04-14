/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author kjagiello
 */
public class NullRepresentation extends EntityRepresentation {

    @Override
    public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, com.badlogic.gdx.graphics.OrthographicCamera camera) {
//        batch.end();
//        renderBoundingBox(sr);
//        batch.begin();
    }

    @Override
    public void update() {
        
    }
    
}
