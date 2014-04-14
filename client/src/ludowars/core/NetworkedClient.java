/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import ludowars.network.GameConnection;
import ludowars.network.Message;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import ludowars.controller.EntityController;
import ludowars.controller.PlayerController;
import ludowars.controller.PlayerDriver;
import ludowars.core.Entity;
import ludowars.model.State;
import ludowars.network.Network;
import ludowars.model.CharacterData;
import ludowars.model.EntityData;
import ludowars.view.ControlledPlayerRepresentation;

/**
 *
 * @author kjagiello
 */
public class NetworkedClient extends Client {

    public ConcurrentLinkedQueue<Object> clientMessageQueue;
    public Client client;

    public NetworkedClient() {
        super(16384, 16384);
        
        clientMessageQueue = new ConcurrentLinkedQueue<Object>();
        client = this;
        
        addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                connection.sendTCP(new Network.Connected());
            }

            @Override
            public void received(Connection c, Object object) {
                clientMessageQueue.add(object);
            }
        });
    }

    public State process(State S) {
        Object o;
        
        while ((o = clientMessageQueue.poll()) != null) {
            if (o instanceof Network.GameState) {
                Network.GameState gs = (Network.GameState)o;
                S = gs.state;
                S.init();
            }
            else if (o instanceof Network.AssignEntity) {
                Network.AssignEntity ae = (Network.AssignEntity)o;
                S.localPlayer = S.entityManager.getEntity(ae.id);
                S.localPlayer.setDriver(new PlayerDriver());
                S.localPlayer.setRepresentation(new ControlledPlayerRepresentation());
            }
            else if (o instanceof Network.UpdateEntity) {
                Network.UpdateEntity ue = (Network.UpdateEntity)o;
                Entity e = S.entityManager.getEntity(ue.data.id);
                
                if (e != null) {
                    e.setData(ue.data);
                }
            }
            else if (o instanceof Network.CreateEntity) {
                Network.CreateEntity ce = (Network.CreateEntity)o;
                S.entityManager.createEntity(ce.data.id, ce.data);
            }
            else if (o instanceof Network.UserCommand) {
                Network.UserCommand cmd = (Network.UserCommand)o;
                Entity e = S.entityManager.getEntity(cmd.id);
                
                if (e != null) {
                    System.out.println(e.driverStateQueue.size());
                    e.driverStateQueue.add(cmd.driverState);
                }
            }
        }
        
        if (S.localPlayer != null) {
            Network.UserCommand cmd = new Network.UserCommand();
            cmd.id = S.localPlayer.getID();
            cmd.driverState = S.localPlayer.getDriver().state;
            sendTCP(cmd);
        }
        
        return S;
    }
    
    public void connect() {
        new Thread("Connect") {
            public void run() {
                try {
                    client.connect(5000, "localhost", Network.port);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }
}
