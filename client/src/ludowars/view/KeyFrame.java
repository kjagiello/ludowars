/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.view;

/**
 *
 * @author kjagiello
 */
public class KeyFrame {
    public int x;
    public int y;
    public long duration;
    public long offset;

    public KeyFrame(int x, int y, long duration, long offset) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.offset = offset;
    }
}