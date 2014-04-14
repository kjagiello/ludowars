/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author kjagiello
 */
public class EntityData implements java.io.Serializable {
    public int id;
    public String controller;
    public String representation;
    public String driver;
    public Vector2 position;
    public Vector2 velocity;
    public float angle;
    public int width;
    public int height;

    public EntityData() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.angle = 0f;
    }

    public EntityData(Vector2 origin) {
        this.position = new Vector2(origin);
        this.velocity = new Vector2(0, 0);
        this.angle = 0f;
    }
        public EntityData(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.angle = 0f;
    }
    public EntityData(Vector2 origin, float angle) {
        this.position = new Vector2(origin);
        this.velocity = new Vector2(0, 0);
        this.angle = angle;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }
}
