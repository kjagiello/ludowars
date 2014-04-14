package ludowars.network;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import ludowars.controller.EntityDriver;
import ludowars.controller.EntityDriverState;
import ludowars.core.Entity;
import ludowars.core.EntityManager;
import ludowars.model.CharacterData;
import ludowars.model.EntityData;
import ludowars.model.State;

/**
 *
 * @author kjagiello
 */
public class Network {

    static public final int port = 6666;

    // This registers objects that are going to be sent over the network.
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

        kryo.register(Integer.class);
        kryo.register(java.util.HashMap.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(com.badlogic.gdx.math.Vector2.class);
        kryo.register(Rectangle.class);
        kryo.register(CharacterData.class);
        kryo.register(EntityData.class);
        kryo.register(State.class);
        kryo.register(GameState.class);
        kryo.register(UpdateEntity.class);
        kryo.register(AssignEntity.class);
        kryo.register(Connected.class);
        kryo.register(CreateEntity.class);
        kryo.register(EntityDriverState.class);
        kryo.register(UserCommand.class);
        kryo.register(Disconnect.class);
    }

    static public class UpdateEntity {
        public EntityData data;
    }
    
    static public class CreateEntity {
        public EntityData data;
    }
    
    static public class AssignEntity {
        public int id;
    }
    
    static public class GameState {
        public State state;
    }
    
    static public class Connected {
    }
    
    static public class Disconnect {
    }
    
    static public class UserCommand {
        // entity id
        public int id;
        public EntityDriverState driverState;
        public long duration;
    }
    
    static public class StateSnapshot {
        public long time;
        public State state;
    }
}
