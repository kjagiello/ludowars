/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import ludowars.core.gui.Layer;
import ludowars.gui.widgets.ImageView;
import ludowars.gui.widgets.InputHandler;
import ludowars.gui.widgets.SpriteSheetView;
import ludowars.model.State;
import ludowars.model.CharacterData;
import ludowars.view.SpriteSheet;

/**
 *
 * @author kjagiello
 */
public class PlayerLayer extends Layer {

    SpriteSheet misc;
    ImageView heart, weapon, backpack, backpack_open;
    BitmapFont font;
    String health = new String();
    float oldHealth, currentHealth, targetHealth;
    long healthTimer;
    float animationTime;
    boolean bpClickedOut = false;
    float pos = 0;
    long last = 0;
    long current = 0;
    Vector2 backpackPosition = new Vector2(0, 0);

    static final float HEALTH_ANIMATION_TIME = 2f;

    public PlayerLayer() {
    }

    @Override
    public void create() {
        setZIndex(1);

        addWidget(heart = new ImageView("assets/images/heart.png"));
        addWidget(weapon = new ImageView("assets/images/weapon.png"));
        addWidget(backpack = new SpriteSheetView("assets/images/misc.png", 32, 32));
        addWidget(backpack_open = new SpriteSheetView("assets/images/backpack.png", 144, 288));
        
        backpack_open.setPosition(0 - backpack_open.getWidth(), Gdx.graphics.getHeight() - backpack_open.getHeight());
        backpackPosition.x = backpack_open.getBounds().x;
        backpackPosition.y = backpack_open.getBounds().y;

        backpack.inputHandler = new InputHandler() {

            @Override
            public boolean touchUp(float screenX, float screenY, int pointer, int button) {
                Sound s = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/backpack.wav"));
                s.play(2.0f);
                backpackPosition.x = backpackPosition.x >= 0 ? -backpack_open.getWidth() : 0;
                return true;
            }
        };

        FreeTypeFontGenerator fg = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/Minecraftia.ttf"));
        font = fg.generateFont(36);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        super.render(batch, delta);

        // animated health display
        if (animationTime <= HEALTH_ANIMATION_TIME) {
            animationTime = Math.min(animationTime + delta, HEALTH_ANIMATION_TIME);
            currentHealth = oldHealth + (targetHealth - oldHealth) * Interpolation.exp5Out.apply(animationTime / HEALTH_ANIMATION_TIME);
            health = "" + (int) currentHealth;
        }

        float textHeight = font.getBounds("100").height;

        font.setColor(Color.BLACK);
        font.draw(batch, health, 20 + heart.getWidth() + 20 + 2, 20 + textHeight + 8 - 2);

        font.setColor(Color.WHITE);
        font.draw(batch, health, 20 + heart.getWidth() + 20, 20 + textHeight + 8);
        
        float lerp = 0.1f;
        float newX = backpack_open.getBounds().x + (backpackPosition.x - backpack_open.getBounds().x) * lerp;
        float newY = backpack_open.getBounds().y + (backpackPosition.y - backpack_open.getBounds().y) * lerp;
        backpack_open.setPosition(newX, newY);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        heart.setPosition(20, 20);
        weapon.setPosition(Gdx.graphics.getWidth() - 20 - weapon.getWidth(), 20);
        backpack.setPosition(20, Gdx.graphics.getHeight() - 20 - backpack.getHeight());
        backpack_open.setPosition(0 - backpack_open.getWidth(), Gdx.graphics.getHeight() - backpack_open.getHeight());
    }

    @Override
    public void update(State S) {
        if (S.localPlayer != null) {
            CharacterData p = (CharacterData) S.localPlayer.getData();

            if (targetHealth != p.getHealth()) {
                animationTime = 0;
                targetHealth = p.getHealth();
                oldHealth = currentHealth;
            } else if (animationTime == HEALTH_ANIMATION_TIME) {
                oldHealth = currentHealth;
            }
        }
    }
}
