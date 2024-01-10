package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class CollisionStrategy {
    private final GameObjectCollection gameObject;
    private final Counter remainingBricksCounter;
    public CollisionStrategy(GameObjectCollection gameObject,Counter remainingBricksCounter ) {
        this.gameObject = gameObject;
        this.remainingBricksCounter = remainingBricksCounter;
    }
     public void onCollision(GameObject thisObj, GameObject otherObj) {
       // System.out.println("collision with brick detected");
        gameObject.removeGameObject(thisObj,Layer.STATIC_OBJECTS);
        remainingBricksCounter.decrement();
        
    }
}
