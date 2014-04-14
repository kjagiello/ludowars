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
 * @author Petter
 */
public class HealthPickupRepresentation extends EntityRepresentation {

    AnimatedSpriteSheet rep;

    public HealthPickupRepresentation() {
        Texture temp = new Texture(Gdx.files.internal("assets/images/fAidSheet.png"));
        rep = new AnimatedSpriteSheet(temp, 25, 32);

        // define animation
        KeyFrameGroup g = new KeyFrameGroup();
        for (int i = 0; i < 14; i++) {
            g.add(i, 0, 40);
        }

        // add it
        rep.addGroup("default", g);

        // play it
        rep.play("default");
    }

    @Override
    public void update() {
    }

    @Override
    public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, com.badlogic.gdx.graphics.OrthographicCamera camera) {
        batch.draw(rep.grabSprite(), this.entity.getData().position.x, this.entity.getData().position.y);
        //batch.end();
        //renderBoundingBox(sr);
        //batch.begin();
    }
}
