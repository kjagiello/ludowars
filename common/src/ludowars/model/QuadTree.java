/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.model;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kjagiello
 */
public class QuadTree<E extends QuadTreeElement> {
    private QuadTree parent;
    private final Rectangle bounds;
    private ArrayList<E> elements;
    private ArrayList<E> reinsert;
    private int maxElements;
    private int depth;
    private int maxDepth;
    private int count;

    private QuadTree<E> topLeft;
    private QuadTree<E> topRight;
    private QuadTree<E> botLeft;
    private QuadTree<E> botRight;
    
    public QuadTree(Rectangle bounds, int maxDepth, int maxElements) {
        if (bounds.width != bounds.height)
            throw new Error("Wrong quadtree dimensions.");
        
        this.bounds = bounds;
        this.elements = new ArrayList<E>();   
        this.reinsert = new ArrayList<E>();   
        this.maxElements = maxElements;
        this.maxDepth = maxDepth;
        this.depth = 0;
        this.count = 0;
        this.parent = null;
    }
    
    public QuadTree(float x, float y, float size, int depth, int maxDepth, int maxElements) {
        this(new Rectangle(x, y, size, size), maxDepth, maxElements);
        this.depth = depth;
    }
    
    public QuadTree(QuadTree parent, float x, float y, float size, int depth, int maxDepth, int maxElements) {
        this(x, y, size, depth, maxDepth, maxElements);
        this.parent = parent;
    }
    
    public boolean insert(E e) {
        if (!bounds.overlaps(e.getBounds())) {
            // TODO: handle this case
            return false;
        }
           
        if (hasChildren()) {
            if (topRight.insert(e)) return true;
            if (topLeft.insert(e)) return true;
            if (botRight.insert(e)) return true;
            if (botLeft.insert(e)) return true;
            // System.out.println(e.getBounds());
            throw new Error("BRITT-MARIE");
            
//            reinsert.add(e);
//            e.qtParent = this;
//            return true;
        }
        
        elements.add(e);
        e.qtParent = this;

        if (depth < maxDepth && elements.size() > maxElements) {
            subdivide();
            
            for (E el: elements) {
                insert(el);
            }
            
            elements.clear();
            elements.addAll(reinsert);
            System.out.println("HELLO " + elements.size());
            reinsert.clear();
            count = 0;
            return true;
        }
        
        count++;
        return true;
    }
    
    public boolean delete(E e) {
        if (elements.remove(e)) {
            count--;
            e.qtParent = null;
            return true;
        }
        
        if (hasChildren()) {
            if (topRight.delete(e)) return true;
            if (topLeft.delete(e)) return true;
            if (botRight.delete(e)) return true;
            if (botLeft.delete(e)) return true;
        }
        
        return false;
    }
    
    public ArrayList<E> retrieve(Rectangle rect) {
        ArrayList<E> out = new ArrayList<E>();
        
        if (!bounds.overlaps(rect)) {
            return out;
        }
        
        if (hasChildren()) {
            out.addAll(topRight.retrieve(rect));
            out.addAll(topLeft.retrieve(rect));
            out.addAll(botRight.retrieve(rect));
            out.addAll(botLeft.retrieve(rect));
        }
        //System.out.println("HELLO " + rect);
        for (E el: elements) {
            if (rect.overlaps(el.getBounds())) {
        //        System.out.println(el.getBounds());
                out.add(el);
            }
        }
        
        //System.out.println(out);
        
        return out;
    }
    
    public boolean hasChildren() {
        return topLeft != null;
    }
    
    protected boolean subdivide() {
      if (hasChildren())
         return false;
      
      float hs = bounds.height / 2;
      botLeft  = new QuadTree<E>(this, bounds.x, bounds.y, hs, depth + 1, maxDepth, maxElements);
      botRight = new QuadTree<E>(this, bounds.x+hs, bounds.y, hs, depth + 1, maxDepth, maxElements);
      topLeft  = new QuadTree<E>(this, bounds.x, bounds.y+hs, hs, depth + 1, maxDepth, maxElements);
      topRight = new QuadTree<E>(this, bounds.x+hs, bounds.y+hs, hs, depth + 1, maxDepth, maxElements);
      
      return true;
    }
    
    public int getCount() {
        return count;
    }
    
    public boolean move(E e) {
        QuadTree qt = e.qtParent;
//        System.out.println("e " + e);
//        System.out.println("qt " + qt);
//        System.out.println("e " + e.getBounds());
//        System.out.println("qt.bounds " + qt.bounds);
        if (!qt.bounds.overlaps(e.getBounds())) {
            qt.elements.remove(e);
            
            while(qt != null) {
                if (qt.bounds.overlaps(e.getBounds()))
                    break;
                
                qt = qt.parent;
            }
            
            if (qt == null) {
                System.err.println("The element is outside the quadtree!!! o.O");
                return false;
            }
            
            qt.insert(e);
        }
        
        return true;
    }
    
    public ArrayList<Rectangle> getAllBounds() {
        ArrayList<Rectangle> l = new ArrayList<Rectangle>();
        
        l.add(bounds);
        //System.out.println(bounds + " : " + hasChildren());
        
        if (hasChildren()) {
            l.addAll(topLeft.getAllBounds());
            l.addAll(botLeft.getAllBounds());
            l.addAll(topRight.getAllBounds());
            l.addAll(botRight.getAllBounds());
        }
        
        return l;
    }
}