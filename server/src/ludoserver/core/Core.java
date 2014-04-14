/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.backends.lwjgl.LwjglNativesLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import ludowars.controller.controlLogic.LogicMain;
import ludowars.network.Network;
import ludowars.model.CharacterData;
//import ludowars.network.Network.RegisterName;

/**
 *
 * @author kjagiello
 */
public class Core {

    GameServer server;
    LogicMain L;
   
    int idCounter = 100;

    public Core() throws IOException, InterruptedException {
        // haxXx
        LwjglNativesLoader.load();
        Gdx.files = new LwjglFiles();
        
        server = new GameServer();
        server.bind(Network.port);
        server.start();
        
        Network.register(server);
        
        int N = 48;
        long taskTime = 0;
        long sleepTime = 1000 / N;
        while (true) {
            taskTime = System.currentTimeMillis();
            server.process();
            taskTime = System.currentTimeMillis() - taskTime;
            if (sleepTime - taskTime > 0) {
                Thread.sleep(sleepTime - taskTime);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //Log.set(Log.LEVEL_DEBUG);
        new Core();
    }

}
