/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.model;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author kjagiello
 */
abstract public class QuadTreeElement {
    public QuadTree qtParent;
    abstract public Rectangle getBounds();
}
