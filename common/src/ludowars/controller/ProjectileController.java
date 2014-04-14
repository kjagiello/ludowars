/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.ArrayList;
import ludowars.core.Entity;
import ludowars.model.State;
import ludowars.model.CharacterData;
import ludowars.model.ProjectileData;

/**
 *
 * @author kjagiello
 */
public class ProjectileController extends EntityController {
   @Override
   public void update(State S, float delta) {
       super.update(S, delta);
       
       ArrayList<Entity> entities = getCollidingEntities(S);
       for (Entity e : entities) {
           if (e.getData() instanceof CharacterData) {
               if (getData().senderId != e.getID()) {
                   CharacterData d = (CharacterData) e.getData();
                   d.changeHealth(-getData().damage);                   
                   
                   S.entityManager.removeEntity(entity.getID());
                   break;
               }
           }
           else {
               S.entityManager.removeEntity(entity.getID());
               break;
           }
       }
   } 
   
   public ProjectileData getData() {
       return (ProjectileData)entity.getData();
   }
}
