/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludowars.network;

/**
 *
 * @author kjagiello
 */
abstract public class Message {
    abstract public void process(GameConnection c);
}
