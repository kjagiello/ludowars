package ludowars.controller.controlLogic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;
import java.util.Map;
import ludowars.core.Entity;
import ludowars.model.EntityData;
import ludowars.model.State;

/**
 *
 * @author Ignatio
 */
public class LogicMain {
    public void update(State S, float delta, OrthographicCamera camera) {
        S.entityManager.handleMovement();
        //System.out.println("count: " + S.entityManager.getCount());
        Iterator it = S.entityManager.iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            Entity e = (Entity) pairs.getValue();
            
            if (e.getDriver() != null) {
                if (!e.driverStateQueue.isEmpty()) {
                    e.getDriver().state = e.driverStateQueue.poll();
                }
                e.getDriver().refresh(camera);
            }
            
            e.getController().update(S, delta);
        }
        
        S.entityManager.updateDepthBuffer();
    }
}
