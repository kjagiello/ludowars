/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.gui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author kjagiello
 */
public class ImageView extends Widget {
    Texture texture;

    
    public ImageView(String path) {
        texture = new Texture(Gdx.files.internal(path));
        bounds.width = texture.getWidth();
        bounds.height = texture.getHeight();
    }
    
    @Override
    public void render(SpriteBatch batch, ShapeRenderer sr, float delta) {
        batch.draw(texture, bounds.x, bounds.y);
    }
}
