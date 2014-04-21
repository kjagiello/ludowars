package ludowars.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import ludowars.core.gui.Screen;
import ludowars.core.gui.FontManager;

/**
 * Super tasty spaghetti code. Should be eaten with some ketchup.
 * TODO: rebuild using our brand new GUI system
 * @author kjagiello
 */
public class StartScreen extends Screen {
    ArrayList<String> menuOptions;
    int selectedOption;
    Texture ludo;
    int ludoCounter;
    int ludoX, ludoY;
    int ludoModX, ludoModY;
    int ludoCircleModWidth, ludoCircleModHeight;
    int ludoCircleModX, ludoCircleModY;
    int ludoSuperKlonkadCounter;
    int stripeCount = 10;
    long superLudoTimer;
    BitmapFont font, bigFont, miniFont;
    Music bgMusic;
    
    public StartScreen() {
        super();
        
        menuOptions = new ArrayList<String>();
        menuOptions.add(0, "START");
        menuOptions.add(0, "SETTINGS");
        menuOptions.add(0, "EXIT");
        
        selectedOption = menuOptions.size() - 1;
        
        ludo = new Texture(Gdx.files.internal("assets/images/ludo.png"));
       
        font = FontManager.getInstance().getFont("Minecraftia.ttf", 40);
        bigFont = FontManager.getInstance().getFont("Minecraftia.ttf", 50);
        miniFont = FontManager.getInstance().getFont("Minecraftia.ttf", 12);
        
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/machine.wav"));
        bgMusic.setLooping(true);
    }
    
    @Override
    public void render() {
        super.render();
        update();
        
        ShapeRenderer s = new ShapeRenderer();
        
        // fancy background
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(new Color(255 / 255f, (146 - (stripeCount) * 10) / 255f, (238 - (stripeCount) * 10) / 255f, 1f)); //new Color(255, 146 - (stripeCount) * 10, 238 - (stripeCount) * 10, 255));
        s.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        s.end();
        
        // motherfucking stripe gradient
        for (int i = 0; i < stripeCount; i++) {
            int m = i;
            //int xPoints[] = {50 * i, 50 * (i + 1), 100 * (i + 1), 50 * (i + 1)};
            int xPoints[] = {50 * m - 50, 100 * m - 50, 100 * m - 500 - 50, 50 * m - 500 - 50};
            int yPoints[] = {0, 0, Gdx.graphics.getHeight(), Gdx.graphics.getHeight()};
            
            s.begin(ShapeRenderer.ShapeType.Filled);
            s.setColor(new Color(255 / 255f, (146 - (i + 1) * 10) / 255f, (238 - (i + 1) * 10) / 255f, 1f));
            s.identity();
            s.rotate(0f, 0f, 1f, -45f);
            s.scale(2.75f, 2.75f, 1f);
            s.rect(-200 + 50 * m, 0, 100, Gdx.graphics.getHeight());
           
            s.end();
        }
        
        // the yellow circle behind ludo
//        s.begin(ShapeRenderer.ShapeType.FilledCircle);
//
//        s.setColor(Color.YELLOW);
//        s.filledCircle(ludoX - 250, ludoY + 250, 100);
//        s.end();
        batch.begin();
        
        // ludo himself
        ludoX = Gdx.graphics.getWidth() - ludo.getWidth() + 50;
        ludoY = -200;
        batch.draw(ludo, ludoX, ludoY);
        
        // menu entries
        for (int i = 0; i < menuOptions.size(); i++) {
            font.setColor(selectedOption == i ? Color.WHITE : Color.BLACK);
            font.draw(batch, menuOptions.get(i), 80, 80 + 52 * i);
            //g.drawChars(menuOptions.get(i).toCharArray(), 0, menuOptions.get(i).length(), 80, Gdx.graphics.getHeight() - 80 - 42 * i);
        }
        
        // title
        String title = "LUDOWARS*";
        TextBounds b = bigFont.getBounds(title);
        int twidth = (int) b.width;
        
        // text shadow
        bigFont.setColor(Color.PINK);
        bigFont.draw(batch, title,  Gdx.graphics.getWidth() - 60 - twidth + 4, Gdx.graphics.getHeight() - 84);
        bigFont.draw(batch, title,  Gdx.graphics.getWidth() - 60 - twidth + 2, Gdx.graphics.getHeight() - 82);
        
        bigFont.setColor(Color.WHITE);
        bigFont.draw(batch, title, Gdx.graphics.getWidth() - 60 - twidth, Gdx.graphics.getHeight() - 80);
        
        // version
        String version = "v0.666";
        TextBounds b2 = miniFont.getBounds(version);
        int vwidth = (int) b2.width;
        miniFont.draw(batch, version, Gdx.graphics.getWidth() - 60 - vwidth - 22, Gdx.graphics.getHeight() - 135);
        batch.end();
    }
    
    public void update() {
        ludoCircleModWidth = (int) (Math.cos(Math.toRadians(ludoCounter % 360)) * 30);
        ludoCircleModHeight = (int) (Math.cos(Math.toRadians(ludoCounter % 360)) * 20);
        
        ludoCircleModX = (int) (Math.cos(Math.toRadians((ludoCounter + 80) % 360)) * -15.0);
        ludoCircleModY = (int) (Math.sin(Math.toRadians((ludoCounter + 80) % 360)) * -15.0);
        
        ludoCounter += 3;
        
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            selectedOption = ++selectedOption >= menuOptions.size() ? 0 : selectedOption;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            selectedOption = --selectedOption < 0 ? menuOptions.size() - 1 : selectedOption;
        }
        
        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            manager.push(new GameScreen());
        }
    }
    
    @Override
    public void hide() {
        bgMusic.pause();
    }
    
    @Override
    public void show() {
        bgMusic.setVolume(0.4f);
        bgMusic.play();
    }
}
