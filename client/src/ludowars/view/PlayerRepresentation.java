/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import ludowars.model.CharacterData;
import ludowars.model.EntityData;

/**
 *
 * @author IgnatioQ
 */
public class PlayerRepresentation extends CharacterRepresentation {

    SpriteSheet handle;
    float health;
    long healthChangeTime;

    public PlayerRepresentation() {
        Texture temp = new Texture(Gdx.files.internal("assets/images/Players.png"));
        this.handle = new SpriteSheet(temp, 32, 32);
    }

    @Override
    public void update() {
        // Britt-Marie was here...
        super.update();
    }

    @Override
    public void render(ludowars.model.State S, SpriteBatch batch, ShapeRenderer sr, com.badlogic.gdx.graphics.OrthographicCamera camera) {
        super.update();
        
        CharacterData data = getData();
        float sx = data.position.x;
        float sy = data.position.y;
        Vector3 sv = new Vector3((float) sx, (float) sy, 0f);

        batch.draw(handle.grabSprite(moveAnimationFrame, data.getCakeSlice()), sx - 10, sy - 6);
        batch.end();
        
        if (data.getHealth() < health && healthChangeTime + 500 < System.currentTimeMillis()) {
            Sound s = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/grunt.wav"));
            s.play(0.1f);
        }
        
        if (data.getHealth() != health) {
            healthChangeTime = System.currentTimeMillis();
        }
        
        health = data.getHealth();
        
        if (healthChangeTime + 2000 > System.currentTimeMillis()) {
            drawHealth(sr, sv, data);
        }
        //renderBoundingBox(sr);

        batch.begin();
    }

    public void drawHealth(ShapeRenderer s, Vector3 sv, CharacterData data) {
        float healthWidth = data.getHealth() / data.maxHealth * 40;
        
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(Color.valueOf("FFAA00"));
        s.rect(sv.x - 12 - 4, sv.y + 36, 40, 8);
        s.end();

        if (data.getHealth() > 0) {
            s.begin(ShapeRenderer.ShapeType.Filled);
            s.setColor(Color.RED);
            s.rect(sv.x - 12 + 2 - 4, sv.y + 36 + 2, healthWidth - 4, 8 - 4);
            s.end();
        }
    }

    public CharacterData getData() {
        return (CharacterData) entity.getData();
    }
}
