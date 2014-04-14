package ludowars.controller;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import ludowars.controller.CharacterController;
import ludowars.controller.EntityDriverState;
//import ludowars.controller.PlayerDriver;
import ludowars.controller.ProjectileController;
import ludowars.core.Entity;
//import ludowars.core.GameListener;
import ludowars.model.EntityData;
import ludowars.model.ProjectileData;
import ludowars.model.State;
//import ludowars.view.LudoRepresentation;
//import ludowars.view.PowerRepresentation;
import ludowars.network.Network;
import ludowars.model.CharacterData;

/**
 *
 * @author Ignatio
 */
public class PlayerController extends CharacterController {
    public PlayerController() {
        super();
    }
    
    @Override
    public void update(State S, float delta) {
        EntityData data = entity.getData();
        EntityDriverState driverState = getDriver().state;

        //Calculate Angle
        data.angle = getMouseAngle();
        
        if (driverState.fireSecondary) {
            System.out.println(driverState.fireSecondary);
            for(int i = 0; i < 12; i++) {
                ProjectileData missile2 = new ProjectileData(entity.getData().position);
                missile2.velocity.set(500f, 500f);
                missile2.velocity.setAngle(entity.getData().angle + i * 30);
                //System.out.println(entity.getData().angle + i * 30);
                

                missile2.senderId = entity.getID();
                missile2.damage = 4;
                missile2.width = 20;
                missile2.height = 20;
                missile2.controller = "ludowars.controller.ProjectileController";
                missile2.representation = "ludowars.view.PowerRepresentation";
                
                S.entityManager.createEntity(missile2);
                
            }
        }
        if (driverState.fire) {
            System.out.println(driverState.fire);
            ProjectileData missile = new ProjectileData(entity.getData().position);
            missile.velocity.set(500f, 500f);
            missile.velocity.setAngle(entity.getData().angle);
            missile.velocity.add(entity.getData().velocity);
            missile.senderId = entity.getID();
            missile.damage = 10;
            missile.width = 8;
            missile.height = 8;
            missile.controller = "ludowars.controller.ProjectileController";
            missile.representation = "ludowars.view.LudoRepresentation";

            S.entityManager.createEntity(missile);

        }
        
        entity.setFlag(entity.ENTITY_MOVED);
        //System.out.println(getCollidingEntities(S));
        
        Vector2 oldPosition = data.position.cpy();
        Vector2 oldVelocity = data.velocity.cpy();
        
        applyAcceleration();
        applyVelocity(delta);
        applyDrag();
        
        // resolve collisions
        ArrayList<Entity> collidees = getCollidingEntities(S);
        
        if (!collidees.isEmpty()) {
            if (collidees.size() == 0) {
                Entity e = collidees.get(0);
                System.out.println("SINGLE COLLISION " + e.getBounds() + " " + entity.getBounds());
                
//                if (e.getBounds().x + e.getBounds().width <= data.position.x || entity.getBounds().x + entity.getBounds().width >= e.getBounds().x) {
//                    data.position.x = oldPosition.x;
//                    data.velocity.x = 0;
//                    
//                    System.out.println(data.position + ", " + data.velocity);
//                }
//                else if (e.getBounds().y + e.getBounds().height <= data.position.y || entity.getBounds().y + entity.getBounds().height >= e.getBounds().y) {
//                    data.position.y = oldPosition.y;
//                    data.velocity.y = 0;
//                    
//                    System.out.println(data.position + ", !!!, " + data.velocity);
//                }
                
//                if (data.velocity.x > 0)
//                    data.position.x = e.getBounds().x - entity.getBounds().width;
//                else if (data.velocity.x < 0)
//                    data.position.x = e.getBounds().x + e.getBounds().width;
//                else if (data.velocity.y > 0)
//                    data.position.y = e.getBounds().y - entity.getBounds().height;
//                else if (data.velocity.y < 0)
//                    data.position.y = e.getBounds().y + e.getBounds().height;

            }
            else {
                data.position = oldPosition;
                data.velocity = new Vector2(0, 0); // oldVelocity;
            }
        }
        
        if (entity.getDriver().getAccelerationVector().len() == 0f) {
            data.velocity = new Vector2(0, 0);
        }
        
        //borderControl(S);
    }

    private EntityDriver getDriver() {
        return entity.getDriver();
    }

    protected float getMouseAngle() {
        return getAngle(getDriver().state.mousePosition);
    }

    protected float getAngle(Vector2 position) {
        return entity.getData().position.cpy().sub(position).scl(-1f).angle();
    }
    private void applyAcceleration() {
        entity.getData().velocity.add(entity.getDriver().getAccelerationVector().nor().scl(75));
    }

    private void applyVelocity(float delta) {
        entity.getData().position.x += this.entity.getData().velocity.x * delta;
        entity.getData().position.y += this.entity.getData().velocity.y * delta;
    }

    private void applyDrag() {
        entity.getData().velocity.scl(0.50f);
    }

    private void borderControl(State S) {
        if (entity.getData().position.x + entity.getBounds().width > S.worldBounds.width) {
            entity.getData().position.x = S.worldBounds.width - entity.getBounds().width;
            entity.getData().velocity.x *= -1;
        } else if (entity.getData().position.x < S.worldBounds.x) {
            entity.getData().position.x = S.worldBounds.x ;
            entity.getData().velocity.x *= -1;
        }
        if (entity.getData().position.y + entity.getBounds().height > S.worldBounds.height) {
            entity.getData().position.y = S.worldBounds.height - entity.getBounds().height ;
            entity.getData().velocity.y *= -1;
        } else if (entity.getData().position.y < S.worldBounds.y) {
            entity.getData().position.y = S.worldBounds.y;
            entity.getData().velocity.y *= -1;
        }
    }
}
