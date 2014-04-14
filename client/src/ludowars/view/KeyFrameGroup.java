/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

import java.util.ArrayList;

/**
 *
 * @author kjagiello
 */
public class KeyFrameGroup {
    private ArrayList<KeyFrame> keyframes;
    private long length;
    private boolean loop;

    public KeyFrameGroup() {
        keyframes = new ArrayList<KeyFrame>();
    }
    
    public void add(int x, int y, long duration) {
        long offset = 0;
        
        for (KeyFrame f : keyframes) {
            offset += f.duration;
        }
        
        length += duration;
        keyframes.add(new KeyFrame(x, y, duration, offset));
    }
    
    public KeyFrame getForOffset(long offset) {
        KeyFrame frame = null;
        
        if (loop)
            offset %= length;

        for (KeyFrame f : keyframes) {
            if (offset >= f.offset)
                frame = f;
        }
        
        return frame;
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
