/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.ArrayList;
import ludowars.core.Entity;
import ludowars.model.CharacterData;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
public abstract class PickupController extends EntityController {
   @Override
   public void update(State S, float delta) {
       super.update(S, delta);
       ArrayList<Entity> entities = getCollidingEntities(S);
       
       for (Entity e : entities) {
            pickup(e);
            S.entityManager.removeEntity(entity.getID());
       }
   } 
   
   abstract boolean pickup(Entity e);
}
