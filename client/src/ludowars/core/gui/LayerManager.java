/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.core.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
public class LayerManager {
    ArrayList<Layer> layers;
    InputMultiplexer multiplexer;
    
    public LayerManager() {
        layers = new ArrayList<Layer>();
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
    }
    
    public void addLayer(Layer layer) {
        layer.layerManager = this;
        layers.add(layer);
        layer.create();
        layer.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        reorganize();
    }
    
    public void update(State S) {
        for (Layer l : layers) {
            l.update(S);
        }
    }
    
    public void render(SpriteBatch batch, float delta) {
        for (Layer l : layers) {
            OrthographicCamera camera = l.getCamera();
            
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            batch.begin();
            l.render(batch, delta);
            batch.end();
        }
    }
    
    public void resize(int width, int height) {
        for (Layer l : layers) {
            l.resize(width, height);
        }
    }
    
    public void reorganize() {
        Collections.sort(layers, new Comparator<Layer>() {
            @Override
            public int compare(Layer l1, Layer l2) {
                Integer z1 = l1.getZIndex();
                Integer z2 = l2.getZIndex();
                return z1.compareTo(z2);
            }
        });
        
        multiplexer.clear();
        
        
        for (int i = layers.size() - 1; i > 0; i--) {
            multiplexer.addProcessor(layers.get(i));
        }
    }
}
