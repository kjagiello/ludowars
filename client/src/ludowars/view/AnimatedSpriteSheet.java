/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kjagiello
 */
public class AnimatedSpriteSheet extends SpriteSheet {            
    Map<String, KeyFrameGroup> groups;
    KeyFrameGroup currentGroup = null;
    KeyFrame currentFrame = null;
    long playStart;
    
    public AnimatedSpriteSheet(Texture s, int boxX, int boxY) {
        super(s, boxX, boxY);
        groups = new HashMap<String, KeyFrameGroup>();
    }
    
    TextureRegion grabSprite() {
        KeyFrame f = currentGroup.getForOffset(System.currentTimeMillis() - playStart);
        return grabSprite(f.x, f.y);
    }
    
    public boolean play(String name) {
        if (groups.containsKey(name)) {
            currentGroup = groups.get(name);
            playStart = System.currentTimeMillis();
            return true;
        }
        
        return false;
    }
    
    public void addGroup(String name, KeyFrameGroup group) {
        groups.put(name, group);
    }
}
