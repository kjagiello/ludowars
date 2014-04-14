/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import ludowars.controller.CharacterController;
import ludowars.controller.EntityController;
import ludowars.controller.HealthPickupController;
import ludowars.controller.PickupController;
import ludowars.core.Entity;
import ludowars.core.EntityManager;
/**
 *
 * @author IgnatioQ
 */
public class State implements java.io.Serializable {
    public Rectangle worldBounds;
    public HashMap<Integer, EntityData> entities;
    
    // internal stuff, shouldn't be serialized
    transient public Entity localPlayer;
    transient public EntityManager entityManager;
    transient public TiledMap map;

    public State(boolean a) {
        
    }
    
    public State() {
        
    }
    
    public void load() {
        map = new TmxMapLoader().load("assets/maps/forest.tmx");
        
        int mapWidth = (Integer)map.getProperties().get("width") * (Integer)map.getProperties().get("tilewidth");
        int mapHeight = (Integer)map.getProperties().get("height") * (Integer)map.getProperties().get("tileheight");
        int mapSize = Math.max(mapWidth, mapHeight);
        
        worldBounds = new Rectangle(0, 0, mapWidth, mapHeight);
        entityManager = new EntityManager(new Rectangle(0, 0, mapSize, mapSize));
       
        CharacterData cool = new CharacterData(new Vector2(356f, 356f), 250, 10);
        cool.width = 16;
        cool.height = 10;
        cool.controller = "ludowars.controller.PlayerController";
        cool.driver = "ludowars.controller.EntityDriver";
        cool.representation = "ludowars.view.PlayerRepresentation";
        Entity e = entityManager.createEntity(cool);
        
        for (int i = 0; i < 20; i++) {
            EntityData d = new EntityData();
            d.position.x = MathUtils.random(10, worldBounds.width - 10);
            d.position.y = MathUtils.random(10, worldBounds.height - 10);
            d.width = 18;
            d.height = 16;
            d.controller = "ludowars.controller.HealthPickupController";
            d.representation = "ludowars.view.HealthPickupRepresentation";
            entityManager.createEntity(d);
        }
        
        // map colliders
        Iterator it = map.getLayers().get("Colliders").getObjects().iterator();
        while(it.hasNext()) {
            RectangleMapObject o = (RectangleMapObject)it.next();

            EntityData d = new EntityData();
            d.position.x = o.getProperties().get("x", Integer.class);
            d.position.y = o.getProperties().get("y", Integer.class);
            d.width = (int)o.getRectangle().width;
            d.height = (int)o.getRectangle().height;
            d.controller = "ludowars.controller.EntityController";
            d.representation = "ludowars.view.NullRepresentation";

            Entity entity = entityManager.createEntity(d);
            entity.setFlag(Entity.ENTITY_COLLIDABLE);
        }
    }
    
    public byte[] save() {
        entities = new HashMap<>();
        Collection<Entity> ea = entityManager.getAll();
        
        for (Entity e: ea) {
            entities.put(e.getID(), e.getData());
        }
        
        return null;
    }
    
    public void init() {
        map = new TmxMapLoader().load("assets/maps/forest.tmx");
        
        int mapWidth = (Integer)map.getProperties().get("width") * (Integer)map.getProperties().get("tilewidth");
        int mapHeight = (Integer)map.getProperties().get("height") * (Integer)map.getProperties().get("tileheight");
        int mapSize = Math.max(mapWidth, mapHeight);
        
        worldBounds = new Rectangle(0, 0, mapWidth, mapHeight);
        entityManager = new EntityManager(new Rectangle(0, 0, mapSize, mapSize));

        for (EntityData d: entities.values()) {
            Entity e = entityManager.createEntity(d.id, d);
        }
    }
    
    public State load(byte[] data) {
        return null;
    }
}
