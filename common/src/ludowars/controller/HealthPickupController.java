/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.controller;

import ludowars.core.Entity;
import ludowars.model.CharacterData;

/**
 *
 * @author kjagiello
 */
public class HealthPickupController extends PickupController {
    @Override
    boolean pickup(Entity e) {
        if (e.getData() instanceof CharacterData) {
            CharacterData d = (CharacterData)e.getData();
            d.changeHealth(50);
        }
        
        return false;
    }
    
}
