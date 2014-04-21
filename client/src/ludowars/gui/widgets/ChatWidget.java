/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.gui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import ludowars.core.gui.FontManager;

/**
 *
 * @author kjagiello
 */
public class ChatWidget extends Widget {
    static int MAX_LINES = 3;
    static int FONT_SIZE = 20;
    static float PADDING = 10f;
    
    private ArrayList<String> lines;
    private BitmapFont font;
    private boolean inputActive = false;
    private String inputBuffer;

    public ChatWidget() {
        super();
        bounds.width = 300;
        bounds.height = PADDING * 2 + (MAX_LINES) * FONT_SIZE - 4;
        
        inputBuffer = "";
        lines = new ArrayList<String>();
        font = FontManager.getInstance().getFont("bebasneue.ttf", FONT_SIZE);
        
        inputHandler = new ChatInputHandler(this);
    }
    
    public BitmapFont getFont() {
        return font;
    }
    
    public boolean isInputActive() {
        return inputActive;
    }
    
    public void setInputActive(boolean active) {
        inputActive = active;
    }
    
    public String getInputBuffer() {
        return inputBuffer;
    }
    
    public void setInputBuffer(String buffer) {
        inputBuffer = buffer;
    }
    
    public void addLine(String text) {
        lines.add(0, text);
        
        if (lines.size() > MAX_LINES) {
            lines.remove(MAX_LINES);
        }
    }
    
    @Override
    public void render(SpriteBatch batch, ShapeRenderer sr, float delta) {
        if (inputActive) {
            batch.end();
            Gdx.gl.glEnable(GL10.GL_BLEND);
            Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            sr.begin(ShapeRenderer.ShapeType.Filled);

            // chat background
            sr.setColor(0f, 0f, 0f, 0.4f);
            sr.rect(bounds.x, bounds.y + 30, getWidth(), getHeight());

            // chat input
            sr.setColor(0f, 0f, 0f, 0.8f);
            sr.rect(bounds.x, bounds.y, getWidth(), 30);

            sr.end();
            Gdx.gl.glDisable(GL10.GL_BLEND);
            batch.begin();
        }
        
        // chat lines
        int i = 1;
        float offsetY = bounds.y + FONT_SIZE - 4;
        for (String line : lines) {
            font.draw(batch, line, bounds.x + PADDING, offsetY + 24 * i + PADDING);
            i++;
        }
        
        if (inputActive) {
            font.draw(batch, inputBuffer, bounds.x + PADDING, offsetY + 6);
        }
    }
}
