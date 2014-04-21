/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.core;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import ludowars.model.QuadTree;
import ludowars.controller.EntityController;
import ludowars.controller.EntityDriver;
import ludowars.core.Entity;
import ludowars.model.EntityData;
import ludowars.view.EntityRepresentation;

/**
 *
 * @author kjagiello
 */
public class EntityManager {
    private int currentId;
    private int currentLocalId;
    private ConcurrentHashMap<Integer, Entity> map;
    private ArrayList<Integer> toRemove;
    private ArrayList<Entity> depthBuffer;
   
    // contains all entities
    public QuadTree<Entity> entities;
    
    public EntityManager(Rectangle worldBounds) {
        currentId = 0;
        currentLocalId = -1;
        map = new ConcurrentHashMap<Integer, Entity>();
        toRemove = new ArrayList<Integer>();
        entities = new QuadTree<Entity>(worldBounds, 8, 16);
        depthBuffer = new ArrayList<Entity>();
    }
    
    public Entity createEntity(int id, EntityController controller, EntityData data, EntityRepresentation representation, EntityDriver driver) {
        Entity e = new Entity(
                id,
                controller,
                data,
                representation,
                driver
        );
        
        if (data.controller == null && controller != null)
            data.controller = controller.getClass().getCanonicalName();
        
        if (data.representation == null && representation != null)
            data.representation = representation.getClass().getCanonicalName();
        
        if (data.driver == null && driver != null)
            data.driver = driver.getClass().getCanonicalName();
        
        controller.setEntity(e);
        if (representation != null) representation.setEntity(e);
        
        map.put(id, e);
        entities.insert(e);
        depthBuffer.add(e);
        
        currentId = id + 1;
        
        return e;
    }
    
    public Entity createEntity(EntityController controller, EntityData data, EntityRepresentation representation, EntityDriver driver) {
        Entity e = createEntity(currentId, controller, data, representation, driver);
        currentId++;
        return e;
    }
    
    public Entity createEntity(int id, EntityData data) {
        EntityController c = null;
        EntityDriver d = null;
        EntityRepresentation r = null;
        
        try {
            c = (EntityController)Class.forName(data.controller).newInstance(); 
            d = (EntityDriver)(data.driver == null || data.driver.isEmpty() ? null : Class.forName(data.driver).newInstance());
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("Creating an entity from data: " + e.getMessage());
            return null;
        }
        
        try {
            r = (EntityRepresentation)(data.representation == null || data.representation.isEmpty() ? null : Class.forName(data.representation).newInstance()); 
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | java.lang.RuntimeException e) {
            // this is okay
        }
        
        return createEntity(id, c, data, r, d);
    }
    
    public Entity createEntity(EntityData data) {
        Entity e = createEntity(currentId, data);
        currentId++;
        return e;
    }
    
    public Entity createLocalEntity(EntityData data) {
        Entity e = createEntity(currentLocalId, data);
        currentLocalId--;
        return e;
    }
    
    public Entity getEntity(Integer id) {
        return map.get(id);
    }
    
    public int getCount() {
        return map.size();
    }
    
    public Collection<Entity> getAll() {
        return map.values();
    }
    
    public ArrayList<Entity> getEntities(Rectangle bounds) {
        return getEntities(bounds, Entity.ENTITY_COLLIDABLE);
    }
    
    public ArrayList<Entity> getEntities(Rectangle bounds, int entityFlags) {
        ArrayList<Entity> l = entities.retrieve(bounds);
        Iterator<Entity> iter = l.iterator();

        while (iter.hasNext()) {
            Entity e = (Entity)iter.next();
            
            if (!e.hasFlag(entityFlags))
                iter.remove();
        }

        return l;
    }
    
    public void removeEntity(Integer id) {
        toRemove.add(id);
    }
    
    public Iterator iterator() {
        for (Integer id: toRemove) {
            Entity e = map.get(id);
            entities.delete(e);
            depthBuffer.remove(e);
            map.remove(id);
        }
        
        toRemove.clear();
        
        return map.entrySet().iterator();
    }
    
    public void handleMovement() {
        Iterator it = iterator();
        
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            Entity e = (Entity) pairs.getValue();

            if (e.hasFlag(Entity.ENTITY_MOVED)) {
                entities.move(e);
                e.removeFlag(Entity.ENTITY_MOVED);
            }
        }
    }
    
    public void updateDepthBuffer() {
        Collections.sort(depthBuffer, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                Float d1 = e1.getDepth();
                Float d2 = e2.getDepth();
                return d2.compareTo(d1);
            }
        });
    }
    
    public ArrayList<Entity> getDepthBuffer() {
        return depthBuffer;
    }
}
