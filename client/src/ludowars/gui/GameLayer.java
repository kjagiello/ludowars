/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.minlog.Log;
import ludowars.controller.PlayerController;
import ludowars.controller.PlayerDriver;
import ludowars.controller.controlLogic.LogicMain;
import ludowars.core.NetworkedClient;
import ludowars.core.gui.Layer;
import ludowars.model.CharacterData;
import ludowars.model.EmptyState;
import ludowars.model.State;
import ludowars.network.Network;
import ludowars.view.Extractor;
import ludowars.view.PlayerRepresentation;

/**
 *
 * @author kjagiello
 */
public class GameLayer extends Layer {
    public LogicMain L;
    public State S;
    public Extractor E;
    public NetworkedClient N;
    
    // temporary stuff for testing purpose
    public long test = System.currentTimeMillis();
    public long test2 = 3;
    public byte[] stateSnapshot;
    public float logicTime;
    
    public static final int TICKS_PER_SECOND = 48;
    public static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    public static final int MAX_FRAMESKIP = 5;
    
    private long nextTick;
    
    public GameLayer() {
        L = new LogicMain();
        S = new EmptyState();
        E = new Extractor();
        
        //Log.set(Log.LEVEL_DEBUG);
        N = new NetworkedClient();
        N.start();
        Network.register(N);
        N.connect();
    }
    
    @Override
    public void create() {
        // create the player gui
        layerManager.addLayer(new HUDLayer());
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer sr, float delta) {
        Gdx.graphics.setTitle("LUDOWARS ~ TPS: " + TICKS_PER_SECOND + ", FPS: " + Gdx.graphics.getFramesPerSecond());
        
        int loops = 0;
        
        if (nextTick == 0)
            nextTick = System.currentTimeMillis();
        
        while(System.currentTimeMillis() > nextTick && loops < MAX_FRAMESKIP) {
            S = N.process(S);
            L.update(S, (float)SKIP_TICKS / 1000, E.getCamera());
            
            // propagate the updated state to all gui layers
            layerManager.update(S);
            
            nextTick += SKIP_TICKS;
            loops++;
        }
        
        //float interpolation = (System.currentTimeMillis() + SKIP_TICKS - nextTick) / SKIP_TICKS;
        E.render(S);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        E.resize(width, height);
    }

    @Override
    public void update(State S) {
        
    }
}
