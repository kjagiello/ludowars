/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import ludowars.network.GameConnection;
import ludowars.network.Message;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import ludowars.controller.controlLogic.LogicMain;
import ludowars.core.Entity;
import ludowars.core.EntityManager;
import ludowars.model.State;
import ludowars.network.Network;
import ludowars.model.CharacterData;
import ludowars.model.EntityData;

/**
 *
 * @author kjagiello
 */
public class GameServer extends Server {
    State S;
    LogicMain L;
    
    public GameServer() {
        S = new State();
        L = new LogicMain();
        initState();
        
        addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                System.out.println("CONNECTED!");
            }
            
            @Override
            public void received(Connection c, Object object) {
                GameConnection gc = (GameConnection)c;
                gc.clientMessageQueue.add(object);
            }
            
            @Override
            public void disconnected(Connection c) {
                GameConnection gc = (GameConnection)c;
                gc.clientMessageQueue.add(new Network.Disconnect());
            }
        });
    }

    @Override
    protected Connection newConnection() {
        return new GameConnection();
    }
    
    private void initState() {
        System.out.println(Gdx.files.internal("assets/maps/forest.tmx"));
        //map = new TmxMapLoader().load("assets/maps/forest.tmx");
        
//        int mapWidth = (Integer)map.getProperties().get("width") * (Integer)map.getProperties().get("tilewidth");
//        int mapHeight = (Integer)map.getProperties().get("height") * (Integer)map.getProperties().get("tileheight");
        int mapWidth = 1280;
        int mapHeight = 1024;
        int mapSize = Math.max(mapWidth, mapHeight);
        
        S.worldBounds = new Rectangle(0, 0, mapWidth, mapHeight);
        S.entityManager = new EntityManager(new Rectangle(0, 0, mapSize, mapSize));
        
        CharacterData cool = new CharacterData(new Vector2(356f, 356f), 250, 10);
        cool.width = 16;
        cool.height = 10;
        cool.controller = "ludowars.controller.CharacterController";
        cool.driver = "ludowars.controller.EntityDriver";
        cool.representation = "ludowars.view.PlayerRepresentation";
        //Entity e = S.entityManager.createEntity(cool);
        
        for (int i = 0; i < 20; i++) {
            EntityData d = new EntityData();
            d.position.x = MathUtils.random(10, S.worldBounds.width - 10);
            d.position.y = MathUtils.random(10, S.worldBounds.height - 10);
            d.width = 18;
            d.height = 16;
            d.controller = "ludowars.controller.HealthPickupController";
            d.representation = "ludowars.view.HealthPickupRepresentation";
            S.entityManager.createEntity(d);
        }
        
        // map colliders
//        Iterator it = map.getLayers().get("Colliders").getObjects().iterator();
//        while(it.hasNext()) {
//            RectangleMapObject o = (RectangleMapObject)it.next();
//
//            EntityData d = new EntityData();
//            d.position.x = o.getProperties().get("x", Integer.class);
//            d.position.y = o.getProperties().get("y", Integer.class);
//            d.width = (int)o.getRectangle().width;
//            d.height = (int)o.getRectangle().height;
//            d.controller = "ludowars.MVC.controller.EntityController";
//            d.representation = "ludowars.MVC.view.NullRepresentation";
//
//            Entity entity = entityManager.createEntity(d);
//            entity.setFlag(Entity.ENTITY_COLLIDABLE);
//        }
    }
    
    public void process() {
        for (Connection c: getConnections()) {
            GameConnection gc = (GameConnection)c;
            Object o;
            while ((o = gc.clientMessageQueue.poll()) != null) {                
                if (o instanceof Network.Connected) {
                    // create a character entity
                    CharacterData data = new CharacterData(new Vector2(256f, 256f), 500, 10);
                    data.width = 16;
                    data.height = 10;
                    data.setHealth(300);
                    data.controller = "ludowars.controller.PlayerController";
                    data.driver = "ludowars.controller.EntityDriver";
                    data.representation = "ludowars.view.PlayerRepresentation";

                    Entity playerEntity = S.entityManager.createEntity(data);
                    
                    // propagate the entity to all clients
                    Network.CreateEntity ce = new Network.CreateEntity();
                    ce.data = data;
                    sendToAllExceptTCP(gc.getID(), ce);
                    
                    // assign entity to current connection
                    gc.entity = playerEntity;

                    // send the game state
                    Network.GameState gs = new Network.GameState();
                    S.save();
                    System.out.println("entity count: " + S.entities.size());
                    gs.state = S;
                    gc.sendTCP(gs);

                    // assign created entity to player
                    Network.AssignEntity ae = new Network.AssignEntity();
                    ae.id = playerEntity.getID();
                    gc.sendTCP(ae);
                }
                else if (o instanceof Network.UpdateEntity) {
                    Network.UpdateEntity ue = (Network.UpdateEntity)o;
                    if (gc.entity.getID() == ue.data.id) {
                        gc.entity.setData(ue.data);
                        sendToAllExceptTCP(gc.getID(), ue);
                    }
                }
                else if (o instanceof Network.UserCommand) {
                    Network.UserCommand cmd = (Network.UserCommand)o;
                    gc.entity.driverStateQueue.add(cmd.driverState);
                    //gc.entity.getDriver().state = cmd.driverState;
                    sendToAllExceptTCP(gc.getID(), cmd);
                    
                    //System.out.println("fire: " + cmd.driverState.fire + ", fireSecondary: " + cmd.driverState.fireSecondary);
                }
                else if (o instanceof Network.Disconnect) {
                    S.entityManager.removeEntity(gc.entity.getID());
                    System.out.println("cleaning up");
                }
            }
        }
        
        L.update(S, (float)1000 / 128 / 1000, null);
    }
}
