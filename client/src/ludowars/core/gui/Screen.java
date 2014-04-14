package ludowars.core.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author kjagiello
 */
public abstract class Screen {
    protected ScreenManager manager;
    protected LayerManager layerManager;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    
    public Screen() {
        layerManager = new LayerManager();
        batch = new SpriteBatch();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setManager(ScreenManager manager) {
        this.manager = manager;
    }
    
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        layerManager.render(batch, Gdx.graphics.getDeltaTime());
    }
    
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(2f * aspectRatio, 2f);
        camera.setToOrtho(false, width, height);
        
        layerManager.resize(width, height);
    }

    abstract public void show();
    abstract public void hide();
}
