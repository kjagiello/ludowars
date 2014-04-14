/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ignatio
 */
public class TestPlayer {
    public Vector2 currentPosition; // relative to arbritary 0 position in map.
    public Vector2 currentAcceleration;
    public Vector2 mousePosition;
    public int sizeX = 1;
    public int sizeY = 1;
    TestPlayer(){
        Vector2 tmp = new Vector2();
        tmp.x = 300;
        tmp.y = 300;
        this.currentPosition = tmp;
        this.currentAcceleration = Vector2.Zero;
        this.mousePosition = new Vector2(0, 0);
    }
}
