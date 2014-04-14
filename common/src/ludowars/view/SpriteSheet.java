package ludowars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Petter
 */
public class SpriteSheet {
    public Texture texture;
    public TextureRegion[] sprites;
    public int rows;
    public int cols;

    public SpriteSheet(Texture s, int tileWidth, int tileHeight) {
        int width = s.getWidth();
        int height = s.getHeight();

        this.texture = s;
        this.cols = (int) (width / tileWidth);
        this.rows = (int) (height / tileHeight);
        this.sprites = new TextureRegion[cols * rows];

        for (int y = 0, i = 0; y < this.rows; y++) {
            for (int x = 0; x < this.cols; x++) {
                sprites[i++] = new TextureRegion(texture, x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }
    }

    public TextureRegion grabSprite(int i) {
        return sprites[i];
    }
    
    public TextureRegion grabSprite(int x, int y) {
        return grabSprite((y * this.cols) + x);
    }
}
