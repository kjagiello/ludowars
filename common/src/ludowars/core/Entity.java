/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.core;

import com.badlogic.gdx.math.Rectangle;
import java.util.concurrent.ConcurrentLinkedQueue;
import ludowars.controller.EntityController;
import ludowars.controller.EntityDriver;
import ludowars.controller.EntityDriverState;
import ludowars.model.EntityData;
import ludowars.model.QuadTree;
import ludowars.view.EntityRepresentation;
import ludowars.model.QuadTreeElement;

/**
 *
 * @author kjagiello
 */
public class Entity extends QuadTreeElement {
    static final public int ENTITY_COLLIDABLE = 1 << 0;
    static final public int ENTITY_MOVED = 1 << 1;
    static final public int ENTITY_BRITTMARIE = 1 << 2;
    
    private EntityController controller;
    private EntityData data;
    private EntityRepresentation representation;
    private EntityDriver driver;
    int flags;
    
    public ConcurrentLinkedQueue<EntityDriverState> driverStateQueue;
    
    public Entity(int id, EntityController controller, EntityData data, EntityRepresentation representation, EntityDriver driver) {
        this.controller = controller;
        this.data = data;
        this.representation = representation;
        this.driver = driver;
        this.flags = 0;
        this.driverStateQueue = new ConcurrentLinkedQueue<EntityDriverState>();
        
        this.data.id = id;
    }
    
    public EntityController getController() {
        return controller;
    }
    
    public EntityData getData() {
        return data;
    }
    
    public void setData(EntityData data) {
        this.data = data;
    }
    
    public EntityRepresentation getRepresentation() {
        return representation;
    }
    
    public void setRepresentation(EntityRepresentation r) {
        r.setEntity(this);
        representation = r;
    }
    
    public EntityDriver getDriver() {
        return driver;
    }
    
    public void setDriver(EntityDriver d) {
        driver = d;
    }
    
    public void setController(EntityController c) {
        controller = c;
    }
    
    public int getID() {
        return data.id;
    }
    
    public Rectangle getBounds() {
        if (data == null)
            return new Rectangle(0f, 0f, 0f, 0f);
        
        return new Rectangle(data.position.x, data.position.y, data.width, data.height);
    }
    
    public QuadTree getQTParent() {
        return qtParent;
    }
    
    public boolean hasFlag(int flag) {
        return (flags & flag) == flag;
    }

    public void setFlag(int flag) {
        flags |= flag;
    }

    public void toggleFlag(int flag) {
        flags ^= flag;
    }

    public void removeFlag(int flag) {
        flags &= ~flag;
    }
    
    public float getDepth() {
        return data.position.y;
    }
}
