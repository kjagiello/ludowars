/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author kjagiello
 */
public class ProjectileData extends EntityData {
    public int senderId;
    public float damage;
    
    public ProjectileData() {
        super();
    }
    
    public ProjectileData(Vector2 origin) {
        super(origin);
    }
}
