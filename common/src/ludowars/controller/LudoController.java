/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
public class LudoController extends EntityController {

    @Override
    public void update(State S, float delta) {
      //  entity.getData().velocity.add(entity.getData().velocity.mul(0.59f));
        super.update(S, delta);
    }
}
