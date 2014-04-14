/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author kjagiello
 */
public class PowerRepresentation extends EntityRepresentation {
    AnimatedSpriteSheet sprite;
    
    public PowerRepresentation() {
        Texture temp = new Texture(Gdx.files.internal("assets/images/FireBall.png"));
        sprite = new AnimatedSpriteSheet(temp, 32, 32);
        
        // define animation
        KeyFrameGroup g = new KeyFrameGroup();
        g.add(1, 0, 100);
        g.add(1, 1, 100);
        g.add(1, 2, 100);
        g.add(1, 3, 100);
        g.setLoop(true);
        
        // add it
        sprite.addGroup("default", g);
        
        // play it
        sprite.play("default");
    }
    
    
    @Override
    public void update() {
        // Britt-Marie was here...
    }
    
    @Override
    public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, com.badlogic.gdx.graphics.OrthographicCamera camera) {
        batch.draw(sprite.grabSprite(), entity.getBounds().x, entity.getBounds().y);
    }
}
