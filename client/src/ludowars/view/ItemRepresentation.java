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
import com.badlogic.gdx.math.Vector3;
import ludowars.model.CharacterData;
import ludowars.model.EntityData;

/**
 *
 * @author IgnatioQ
 */
public class ItemRepresentation extends CharacterRepresentation {

    SpriteSheet handle;

    public ItemRepresentation() {
        Texture temp = new Texture(Gdx.files.internal("assets/images/fAidSheet.png"));
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
        
        //drawHealth(sr, sv, data);
        //renderBoundingBox(sr);

        batch.begin();
    }

    private CharacterData getData() {
        return (CharacterData) entity.getData();
    }
}