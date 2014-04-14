package ludowars.core;

import ludowars.core.gui.Screen;
import ludowars.gui.GameLayer;

/**
 *
 * @author kjagiello
 */
public class GameScreen extends Screen {
    public GameScreen() {
        super();
        layerManager.addLayer(new GameLayer());
    }

    @Override
    public void show() {
        
    }

    @Override
    public void hide() {
        
    }
}
