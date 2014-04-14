/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.ArrayList;
import java.util.Queue;
import ludowars.core.Entity;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
public class EntityController {
    protected Entity entity;

    public EntityController() {
    }

    public void setEntity(Entity e) {
        entity = e;
        create();
    }
    
    protected void create() {
        
    }

    public void update(State S, float delta) {
        entity.getData().position.x += this.entity.getData().velocity.x * delta;
        entity.getData().position.y += this.entity.getData().velocity.y * delta;
        entity.setFlag(entity.ENTITY_MOVED);
        if (entity.getData().position.x > S.worldBounds.width || entity.getData().position.y > S.worldBounds.height
                || entity.getData().position.x < S.worldBounds.y || entity.getData().position.y < S.worldBounds.y) {
            S.entityManager.removeEntity(entity.getID());
            //System.out.println(entity.getID());
        }
    }

    public ArrayList<Entity> getCollidingEntities(State S) {
        return getCollidingEntities(S, Entity.ENTITY_COLLIDABLE);
    }
    
    public ArrayList<Entity> getCollidingEntities(State S, int flags) {
        ArrayList<Entity> l = S.entityManager.getEntities(entity.getBounds());
        l.remove(entity);
        return l;
    }
}
