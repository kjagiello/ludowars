/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.controller;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author kjagiello
 */
public class EntityDriverState {
    public boolean moveNorth;
    public boolean moveSouth;
    public boolean moveWest;
    public boolean moveEast;
    public boolean fire;
    public boolean fireSecondary;
    public Vector2 mousePosition;
}
