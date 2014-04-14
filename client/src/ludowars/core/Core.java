/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 *
 * @author Petter
 */
public class Core {
    public static final int WIDTH = 768;
    public static final int HEIGHT = 768;

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.vSyncEnabled = true;
        cfg.useGL20 = true;
        cfg.width = WIDTH;
        cfg.height = HEIGHT;
        cfg.title = "LUDOWARS";
        cfg.foregroundFPS = 61;
        cfg.samples = 0;

        new LwjglApplication(new GameListener(), cfg);
    }
}
