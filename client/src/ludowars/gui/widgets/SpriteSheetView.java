/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.gui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ludowars.view.SpriteSheet;

/**
 *
 * @author kjagiello
 */
public class SpriteSheetView extends ImageView {
    SpriteSheet sheet;
    TextureRegion region;
    
    public SpriteSheetView(String path, int spriteWidth, int spriteHeight) {
        super(path);
        bounds.width = spriteWidth;
        bounds.height = spriteHeight;
        sheet = new SpriteSheet(texture, spriteWidth, spriteHeight);
        setSprite(0);
    }

    public void setSprite(int i) {
        region = sheet.grabSprite(i);
    }
    
    public void setSprite(int x, int y) {
        region = sheet.grabSprite(x, y);
    }
    
    @Override
    public void render(SpriteBatch batch, ShapeRenderer sr, float delta) {
        batch.draw(region, bounds.x, bounds.y);
    }
}
