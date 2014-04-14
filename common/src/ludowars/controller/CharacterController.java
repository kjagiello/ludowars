/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.controller;

import ludowars.core.Entity;

/**
 *
 * @author Petter
 */
public class CharacterController extends EntityController{
    @Override
    protected void create() {
        entity.setFlag(Entity.ENTITY_COLLIDABLE);
    }
}
