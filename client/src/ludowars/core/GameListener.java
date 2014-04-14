package ludowars.core;

/**
 *
 * @author IgnatioQ
 */
import com.badlogic.gdx.ApplicationListener;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import ludowars.core.gui.ScreenManager;
import ludowars.network.Network;

public class GameListener implements ApplicationListener {

    ScreenManager screenManager;

    @Override
    public void create() {
        screenManager = new ScreenManager();
        screenManager.push(new StartScreen());
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render() {
        screenManager.currentScreen().render();
    }

    @Override
    public void resize(int width, int height) {
        screenManager.currentScreen().resize(width, height);
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

}
