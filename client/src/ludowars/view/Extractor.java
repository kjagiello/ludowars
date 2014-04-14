/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import ludowars.core.Entity;
import ludowars.model.EntityData;
import ludowars.model.State;

/**
 *
 * @author Petter
 */
public class Extractor {

    private final int tileSize = 32;
    private SpriteSheet ss;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer sr;
    private OrthogonalTiledMapRenderer mapRenderer;

    public Extractor() {
        ss = new SpriteSheet(new Texture(Gdx.files.internal("assets/images/tilea4.png")), tileSize, tileSize);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        
        TextureRegion r = ss.grabSprite(11, 5);
        
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void outputSynth(State S) {

    }

    public void render(State S) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // setup camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);

        // let's roll ol ol ol
        createBackdrop(S, batch, ss);
        
        batch.begin();
        // for quadtree debug purposes
//        ArrayList<Rectangle> qt = S.entityManager.entities.getAllBounds();
//        
//        batch.end();
//        for (Rectangle bounds : qt) {
//            sr.begin(ShapeRenderer.ShapeType.Line);
//            sr.setColor(Color.BLUE);
//            sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
//            sr.end();
//        }
//        batch.begin();
        
        // render only visible entities
        Rectangle cb = new Rectangle(camera.position.x - camera.viewportWidth / 2, 
                camera.position.y - camera.viewportHeight / 2, 
                camera.viewportWidth, camera.viewportHeight);
        //ArrayList<Entity> entities = S.entityManager.getEntities(cb);
        ArrayList<Entity> entities = S.entityManager.getDepthBuffer();
        
        //System.out.println(cb);
        
        for (Entity e : entities) {
            e.getRepresentation().render(S, batch, sr, camera);
        }

        batch.end();
        
        mapRenderer.getSpriteBatch().begin();
        mapRenderer.renderTileLayer((TiledMapTileLayer)S.map.getLayers().get("Roof"));
        mapRenderer.getSpriteBatch().end();
    }

    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(2f * aspectRatio, 2f);
        camera.setToOrtho(false, width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    private void createBackdrop(State S, SpriteBatch batch, SpriteSheet ss) {
        if (mapRenderer == null) {
            // setup map renderer
            float unitScale = 1 / 1f;
            mapRenderer = new OrthogonalTiledMapRenderer(S.map, unitScale);
        }
        
        mapRenderer.setView(camera);
        mapRenderer.getSpriteBatch().begin();
        mapRenderer.renderTileLayer((TiledMapTileLayer)S.map.getLayers().get("Background"));
        mapRenderer.renderTileLayer((TiledMapTileLayer)S.map.getLayers().get("Ground"));
        mapRenderer.getSpriteBatch().end();
        //mapRenderer.render(S.map.getLayers().get(null));
    }
}
