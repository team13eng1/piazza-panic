package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.team13.piazzapanic.MainGame;
public class Chef extends Sprite {
    public World world;
    public Body b2body;

    private float initialX;
    private float initialY;


    public Vector2 startVector;
    private float chefCollisionTimer;
    public boolean chefCollision;
    private float chefWidth;
    private float chefHeight;
    private Texture wholeImage;
    public enum State {UP, DOWN, LEFT, RIGHT}
    public State currentState;
    public TextureRegion currentSkin;
    public Chef(World world, float startX, float startY) {
        initialX = startX;
        initialY = startY;

        wholeImage = new Texture("Chef_skins.png");
        this.world = world;
        currentState = State.DOWN;

        defineChef();
        currentSkin = new TextureRegion(wholeImage, 32, 0, 29 ,46);

        chefWidth =  13/MainGame.PPM;
        chefHeight =  20/MainGame.PPM;
        setBounds(0,0,chefWidth, chefHeight);
        chefCollision = false;
        chefCollisionTimer = 0;
        startVector = new Vector2(0,0);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        currentSkin= getSkin(dt);
        setRegion(currentSkin);

        if(chefCollision) {
            chefCollisionTimer += dt;
            b2body.setLinearVelocity(new Vector2(startVector.x * -1, startVector.y * -1));
            if (chefCollisionTimer > 0.3f) {
                chefCollision = false;
                chefCollisionTimer = 0;
            }
        }
    }

    private TextureRegion getSkin(float dt) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case UP:
                region = new TextureRegion(wholeImage, 0, 0, 29, 46);
                break;
            case DOWN:
                region = new TextureRegion(wholeImage, 32, 0, 29, 46);
                break;
            case LEFT:
                region = new TextureRegion(wholeImage, 64, 0, 29, 46);
                break;
            case RIGHT:
                region = new TextureRegion(wholeImage, 96, 0, 29, 46);
                break;
            default:
                region = currentSkin;
        }
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0)
            return State.UP;
        if(b2body.getLinearVelocity().y < 0)
            return State.DOWN;
        if(b2body.getLinearVelocity().x > 0)
            return State.RIGHT;
        if(b2body.getLinearVelocity().x < 0)
            return State.LEFT;
        else
            return currentState;
    }

    public void defineChef(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(initialX / MainGame.PPM,initialY / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        chefWidth =  5/MainGame.PPM;
        chefHeight =  10/MainGame.PPM;
        shape.setAsBox(chefWidth, chefHeight);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void chefsColliding(){
        chefCollision = true;
        setStartVector();
    }

    public void setStartVector() {
        startVector = new Vector2(b2body.getLinearVelocity().x,b2body.getLinearVelocity().y);

    }
}
