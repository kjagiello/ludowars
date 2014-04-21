/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.core.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import java.util.HashMap;

/**
 * Font cache
 * @author kjagiello
 */
public class FontManager {
    private static FontManager instance = null;
    HashMap<String, HashMap<Integer, BitmapFont>> fonts;

    protected FontManager() {
        fonts = new HashMap<String, HashMap<Integer, BitmapFont>>();
    }
    
    public BitmapFont getFont(String family, int size) {
        HashMap<Integer, BitmapFont> sizes = fonts.get(family);
        
        if (sizes == null) {
            sizes = new HashMap<Integer, BitmapFont>();
            fonts.put(family, sizes);
        }
        
        BitmapFont font = sizes.get(size);
        
        if (font == null) {
            FreeTypeFontGenerator fgenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/" + family));
            font = fgenerator.generateFont(size);
            sizes.put(size, font);
        }
        
        return font;
    }
    
    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }
}
