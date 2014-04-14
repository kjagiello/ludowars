/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.model;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import ludowars.core.EntityManager;

/**
 *
 * @author kjagiello
 */
public class EmptyState extends State {

    public EmptyState() {
        super(true);
        
        map = new TmxMapLoader().load("assets/maps/forest.tmx");
        
        int mapWidth = (Integer)map.getProperties().get("width") * (Integer)map.getProperties().get("tilewidth");
        int mapHeight = (Integer)map.getProperties().get("height") * (Integer)map.getProperties().get("tileheight");
        int mapSize = Math.max(mapWidth, mapHeight);
        
        System.out.println("mapWidth: " + mapWidth + ", " + mapHeight);
        
        worldBounds = new Rectangle(0, 0, mapWidth, mapHeight);
        entityManager = new EntityManager(new Rectangle(0, 0, mapSize, mapSize));
    }
    
}
