/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.core.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import ludowars.gui.widgets.Widget;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
abstract public class Layer implements InputProcessor {
    public LayerManager layerManager;
    private ArrayList<Widget> widgets;
    private ArrayList<Widget> widgetsCopy;
    private int zIndex;
    private OrthographicCamera camera;
    
    public Layer() {
        widgets = new ArrayList<Widget>();
    }
    
    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
        
        if (layerManager != null)
            layerManager.reorganize();
    }
    
    public int getZIndex() {
        return zIndex;
    }
    
    public void addWidget(Widget w) {
        widgets.add(w);
    }
    
    public void removeWidget(Widget w) {
        widgets.remove(w);
    }
    
    public void render(SpriteBatch batch, float delta) {
        for (Widget w : widgets) {
            w.render(batch, delta);
        }
    }
    
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(2f * aspectRatio, 2f);
        camera.setToOrtho(false, width, height);
    }
    
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // translate coordinates
        Vector3 s = new Vector3(screenX, screenY, 0);
        camera.unproject(s);
        
        for (Widget w: (ArrayList<Widget>)widgets.clone()) {
            if (w.touchUp(s.x, s.y, pointer, button))
                return true;
        }
        
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // translate coordinates
        Vector3 s = new Vector3(screenX, screenY, 0);
        camera.unproject(s);
        
        for (Widget w: widgets) {
            if (w.touchDragged(s.x, s.y, pointer))
                return true;
        }
        
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // translate coordinates
        Vector3 s = new Vector3(screenX, screenY, 0);
        camera.unproject(s);
        
        for (Widget w: widgets) {
            if (w.mouseMoved(s.x, s.y))
                return true;
        }
        
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    abstract public void create();
    abstract public void show();
    abstract public void hide();
    abstract public void update(State S);
}
