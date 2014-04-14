package ludowars.network;

import com.esotericsoftware.kryonet.Connection;
import java.util.concurrent.ConcurrentLinkedQueue;
import ludowars.core.Entity;
import ludowars.model.CharacterData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kjagiello
 */
public class GameConnection extends Connection {
    public ConcurrentLinkedQueue<Object> clientMessageQueue;
    public Entity entity;
    
    public GameConnection() {
        clientMessageQueue = new ConcurrentLinkedQueue<>();
    }
}