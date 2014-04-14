/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ludowars.view.SpriteSheet;
import ludowars.core.Entity;
import ludowars.model.EntityData;
import ludowars.view.SpriteSheet;

/**
 *
 * @author Petter
 */
public abstract class EntityRepresentation {
    
    public SpriteSheet sprite;
    public Entity entity;
    
    abstract public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, OrthographicCamera camera);
    abstract public void update();
    
    public void setEntity(Entity e) {
        entity = e;
    }
    
    public void renderBoundingBox(ShapeRenderer sr) {
        EntityData data = entity.getData();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.RED);
        sr.rect(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
        sr.end();
    }
}
