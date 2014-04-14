/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ludowars.core.Entity;

/**
 *
 * @author Petter
 */
public class CharacterRepresentation extends EntityRepresentation {
    protected int moveAnimationFrame;
    private long moveAnimationTimer;
    public int moveAnimationDelay = 100;
    public static final int CHARACTER_IDLE_ANIMATION = 0;
    
    public CharacterRepresentation() {
        super();
    }
    
    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        if ((currentTime - moveAnimationTimer) > moveAnimationDelay) {
            moveAnimationFrame = ++moveAnimationFrame % 3;
            moveAnimationTimer = currentTime;
        }
        
        if (entity.getData().velocity.len() == 0f)
            moveAnimationFrame = 0;
    }

    @Override
    public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, com.badlogic.gdx.graphics.OrthographicCamera camera) {
        //batch.draw(sprite.grabSprite(moveFrame, c.getCakeSlice(c.getAngle())), c.position.x, c.position.y);
    }
    
    public Character getCharacter() {
        // return (Character)entity;
        return null;
    }
}
