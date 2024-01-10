package gameobjects;

import java.awt.event.KeyEvent;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Paddle extends GameObject {

    private static final float MOVEMENT_SPEED = 500f;
    private UserInputListener inputListener;
    private static final int MIN_DISTANCE_FROM_SCREEN_EDGE = 1;
    private final Vector2 windowDimensions;
    public Paddle(Vector2 topLeftCorner,
     Vector2 dimensions,
      Renderable renderable,
       UserInputListener inputListener,
        Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener =inputListener;
        this.windowDimensions = windowDimensions;

    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        Vector2 moveDiraction = Vector2.ZERO;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)&& 
        (this.getCenter().x() - super.getDimensions().x()/2) - Paddle.MIN_DISTANCE_FROM_SCREEN_EDGE > 0){
            moveDiraction = moveDiraction.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)&&((this.getCenter().x() + super.getDimensions().x()/2) +
        MIN_DISTANCE_FROM_SCREEN_EDGE < this.windowDimensions.x())){
            moveDiraction = moveDiraction.add(Vector2.RIGHT);
        }
        setVelocity(moveDiraction.mult(MOVEMENT_SPEED));
       
    }
    
    
}
