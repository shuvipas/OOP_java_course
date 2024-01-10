//package bricker;

import java.awt.Color;
import java.util.Random;

import brick_strategies.CollisionStrategy;
import danogl.GameManager;
import danogl.util.Vector2;
import gameobjects.Ball;
import gameobjects.Brick;
import gameobjects.Paddle;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;

public class BrickerGameManager extends GameManager {
    private static final String BALL_IMAGE_PATH = "assets/ball.png"; // i put the bricker in a package/dir so we need to
                                                                     // go up a dir
    private static final String PADDLE_IMAGE_PATH = "assets/paddle.png";
    private static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";
    private static final String COLLISION_SOUND_PATH = "assets/blop.wav";
    private static final String BRICKS_IMAGE_PATH = "assets/brick.png";
    private static final String HEART_IMAGE_PATH = "assets/heart.png";

    private static final float BORDER_WIDTH = 20.0f;
    private static final float BALL_SPEED = 250;
    private static final int BRICK_ROWS =  2;//5;
    private static final int BRICK_COL = 1;//8;
    private static final int BRICK_BORDER_DIST = 10;
    private static final int BRICKS_DIST = 1;
    private static final int BRICK_HEIGHT = 15;

    private Counter remainingBricksCounter;

    private final Vector2 windowDimensions;

    private WindowController windowController;
    private GameObject ball;

    public BrickerGameManager(String windowTitle,
            Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);

        this.windowDimensions = windowDimensions;
    }

    private void createBricks(ImageReader imageReader) {
        Renderable BrickImage = imageReader.readImage(BRICKS_IMAGE_PATH, false);// new RectangleRenderable(new
                                                                                // Color(5));//
        // Vector2 brickDim = new Vector2(300,300);
        // GameObject brick = new Brick(brickDim, brickDim, BrickImage, new
        // CollisionStrategy(super.gameObjects()));
        // gameObjects().addGameObject(brick);
        int brickWidth = (int) (this.windowDimensions.x() - BORDER_WIDTH * 2 -
                (BRICKS_DIST * BRICK_COL)) / BRICK_COL;
        Vector2 brickDimensions = new Vector2(brickWidth, BRICK_HEIGHT);
        // int brickNum = BRICK_COL * BRICK_ROWS;
        for (int r = 0; r < BRICK_ROWS; r++) {
            for (int c = 0; c < BRICK_COL; c++) {
                int xCord = (int) BORDER_WIDTH + c * (brickWidth + BRICKS_DIST);
                // System.out.println(xCord);
                int yCord = (int) BORDER_WIDTH + BRICK_BORDER_DIST + r * (BRICK_HEIGHT + BRICKS_DIST);
                System.out.println(yCord);
                Vector2 brickCord = new Vector2(xCord, yCord);

                GameObject brick = new Brick(brickCord, brickDimensions, BrickImage,
                        new CollisionStrategy(super.gameObjects(), remainingBricksCounter));
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                remainingBricksCounter.increment();
                System.out.println(remainingBricksCounter.value());
            }
        }
       // System.out.println(remainingBricksCounter.value());

    }

    private void createBorder() {
        Renderable border = null; // new RectangleRenderable(BORDER_COLOR);

        // left border
        gameObjects().addGameObject(new GameObject(Vector2.ZERO,
                new Vector2(BORDER_WIDTH, windowDimensions.y()),
                border));
        // top border
        gameObjects().addGameObject(new GameObject(Vector2.ZERO,
                new Vector2(windowDimensions.x(), BORDER_WIDTH),
                border));
        // right border
        gameObjects().addGameObject(new GameObject(new Vector2(windowDimensions.x() - BORDER_WIDTH, 0),
                new Vector2(BORDER_WIDTH, windowDimensions.y()),
                border));
    }

    @Override
    public void initializeGame(ImageReader imageReader,
            SoundReader soundReader,
            UserInputListener inputListener,
            WindowController windowController) {
        this.windowController = windowController;

        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        remainingBricksCounter = new Counter(0);

        // create background
        GameObject background = new GameObject(Vector2.ZERO, windowController.getWindowDimensions(),
                imageReader.readImage(BACKGROUND_IMAGE_PATH, false));
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);

        // create ball
        Renderable ballImage = imageReader.readImage(BALL_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(COLLISION_SOUND_PATH);
        this.ball = new Ball(Vector2.ZERO, new Vector2(20, 20), ballImage, collisionSound);
        gameObjects().addGameObject(ball);

        float ballVelx = BALL_SPEED;
        float ballVely = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelx *= -1;
        }
        if (rand.nextBoolean()) {
            ballVely *= -1;
        }

        // Vector2 ballInitSpeed = new Vector2(ballVelx, ballVely);
        // ball.setVelocity(Vector2.DOWN.mult(200));

        ball.setVelocity(new Vector2(ballVelx, ballVely));
        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5f));

        // create paddle
        Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
        GameObject paddle = new Paddle(Vector2.ZERO, new Vector2(100, 15), paddleImage, inputListener,
                windowDimensions);
        gameObjects().addGameObject(paddle);

        // ball.setVelocity(Vector2.DOWN.mult(100));
        // Vector2 windowDimensions= windowController.getWindowDimensions();
        paddle.setCenter(new Vector2(windowDimensions.x() / 2, windowDimensions.y() - 30));
        createBricks(imageReader);
        createBorder();

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkIfGameEnded();

    }

    private void checkIfGameEnded() {
        String prompt = "";
        if (ball.getCenter().y() > windowDimensions.y()) {
            
            prompt = "You lost!\n Play again?";
        }
        if (remainingBricksCounter.value() == 0) {
            prompt = "You won!\n Play again?";
        }
        if (!prompt.isEmpty()) {
            //prompt += " play again?";
            if (windowController.openYesNoDialog(prompt)) {
                windowController.resetGame();
            } else {
                windowController.closeWindow();
            }

        }
    }

    public static void main(String[] args) {
        new BrickerGameManager("Bricker", new Vector2(700, 500)).run();

    }

}
