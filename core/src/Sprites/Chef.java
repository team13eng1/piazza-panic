package Sprites;

import Ingredients.*;
import Recipe.BurgerRecipe;
import Recipe.Recipe;
import Recipe.SaladRecipe;
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
    private float waitTimer;
    public boolean chefOnChefCollision;
    private float chefWidth;
    private float chefHeight;
    private Texture normalChef;
    private Texture bunsChef;
    private Texture bunsToastedChef;
    private Texture burgerChef;
    private Texture lettuceChef;
    private Texture onionChef;
    private Texture tomatoChef;
    private Texture choppedLettuceChef;
    private Texture choppedOnionChef;
    private Texture choppedTomatoChef;
    private Texture pattyChef;
    private Texture completedBurgerChef;
    private Texture meatChef;
    private Texture saladChef;
    public enum State {UP, DOWN, LEFT, RIGHT}
    public State currentState;
    private TextureRegion currentSkin;

    private Texture skinNeeded;

    private Fixture whatTouching;

    private Ingredient inHandsIng;
    private Recipe inHandsRecipe;

    private Boolean userControlChef;
    public Chef(World world, float startX, float startY) {
        initialX = startX;
        initialY = startY;

        normalChef = new Texture("Chef/Chef_normal.png");
        bunsChef = new Texture("Chef/Chef_holding_buns.png");
        bunsToastedChef = new Texture("Chef/Chef_holding_buns_toasted.png");
        burgerChef = new Texture("Chef/Chef_holding_burger.png");
        lettuceChef = new Texture("Chef/Chef_holding_lettuce.png");
        onionChef = new Texture("Chef/Chef_holding_onion.png");
        tomatoChef = new Texture("Chef/Chef_holding_tomato.png");
        choppedLettuceChef = new Texture("Chef/Chef_holding_chopped_lettuce.png");
        choppedOnionChef = new Texture("Chef/Chef_holding_chopped_onion.png");
        choppedTomatoChef = new Texture("Chef/Chef_holding_chopped_tomato.png");
        pattyChef = new Texture("Chef/Chef_holding_patty.png");
        completedBurgerChef = new Texture("Chef/Chef_holding_front.png");
        meatChef = new Texture("Chef/Chef_holding_meat.png");
        saladChef = new Texture("Chef/Chef_holding_salad.png");

        skinNeeded = normalChef;

        this.world = world;
        currentState = State.DOWN;

        defineChef();

        chefWidth =  13/MainGame.PPM;
        chefHeight =  20/MainGame.PPM;
        setBounds(0,0,chefWidth, chefHeight);
        chefOnChefCollision = false;
        waitTimer = 0;
        startVector = new Vector2(0,0);
        whatTouching = null;
        inHandsIng = null;
        inHandsRecipe = null;
        userControlChef = true;
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        currentSkin = getSkin(dt);
        setRegion(currentSkin);
        if (userControlChef == false && chefOnChefCollision == true) {
            waitTimer += dt;
            b2body.setLinearVelocity(new Vector2(startVector.x * -1, startVector.y * -1));
            if (waitTimer > 0.3f) {
                chefOnChefCollision = false;
                userControlChef = true;
                waitTimer = 0;
            }
        } else if (userControlChef == false && chefOnChefCollision == false && getInHandsIng().prepareTime > 0) {
            waitTimer += dt;
            if (waitTimer > inHandsIng.prepareTime) {
                inHandsIng.prepareTime = 0;
                inHandsIng.setPrepared(true);
                userControlChef = true;
                waitTimer = 0;
                setChefSkin(inHandsIng);
            }
        } else if (userControlChef == false && chefOnChefCollision == false && getInHandsIng().isPrepared() && inHandsIng.cookTime > 0){
            waitTimer += dt;
            if (waitTimer > inHandsIng.cookTime) {
                inHandsIng.cookTime = 0;
                inHandsIng.setCooked(true);
                userControlChef = true;
                waitTimer = 0;
                setChefSkin(inHandsIng);
            }
        }
    }

    private TextureRegion getSkin(float dt) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case UP:
                region = new TextureRegion(skinNeeded, 0, 0, 33, 46);
                break;
            case DOWN:
                region = new TextureRegion(skinNeeded, 33, 0, 33, 46);
                break;
            case LEFT:
                region = new TextureRegion(skinNeeded, 64, 0, 33, 46);
                break;
            case RIGHT:
                region = new TextureRegion(skinNeeded, 96, 0, 33, 46);
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
        bdef.position.set(initialX / MainGame.PPM,initialY/ MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(4/MainGame.PPM);
        shape.setPosition(new Vector2(shape.getPosition().x + (0.5f / MainGame.PPM),shape.getPosition().y - (6 / MainGame.PPM)));


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void setChefSkin(Object item) {
        if (item == null) {
            skinNeeded = normalChef;
        } else if (item instanceof Lettuce) {
            if (inHandsIng.isPrepared()) {
                skinNeeded = choppedLettuceChef;
            } else {
                skinNeeded = lettuceChef;
            }
        } else if (item instanceof Tomato) {
            if (inHandsIng.isPrepared()) {
                skinNeeded = choppedTomatoChef;
            } else {
                skinNeeded = tomatoChef;
            }
        } else if (item instanceof Steak) {
            if (inHandsIng.isPrepared() && inHandsIng.isCooked()) {
                skinNeeded = burgerChef;
            } else if (inHandsIng.isPrepared()){
                skinNeeded = pattyChef;
                } else {
                skinNeeded = meatChef;
            }
            } else if (item instanceof Onion) {
            if (inHandsIng.isPrepared()) {
                skinNeeded = choppedOnionChef;
            } else {
                skinNeeded = onionChef;
            }
        } else if (item instanceof Tomato) {
            if (inHandsIng.isPrepared()) {
                skinNeeded = choppedTomatoChef;
            } else {
                skinNeeded = tomatoChef;
            }
        } else if (item instanceof Bun) {
            if (inHandsIng.isCooked()) {
                skinNeeded = bunsToastedChef;
            } else {
                skinNeeded = bunsChef;
            }
        } else if (item instanceof BurgerRecipe){
            skinNeeded = completedBurgerChef;
        } else if (item instanceof SaladRecipe){
            skinNeeded = saladChef;
        }
    }

    public void chefsColliding(){
        userControlChef = false;
        chefOnChefCollision = true;
        setStartVector();
    }

    public void setStartVector() {
        startVector = new Vector2(b2body.getLinearVelocity().x,b2body.getLinearVelocity().y);

    }

    public void setTouchingTile(Fixture obj){
        this.whatTouching = obj;
    }

    public Fixture getTouchingTile() {
        if (whatTouching == null) {
            return null;
        } else {
            return whatTouching;
        }
    }
    public Ingredient getInHandsIng(){
        return inHandsIng;
    }
    public Recipe getInHandsRecipe(){
        return inHandsRecipe;
    }

    public void setInHandsIng(Ingredient ing){
        inHandsIng = ing;
        inHandsRecipe = null;
    }

    public void setInHandsRecipe(Recipe recipe){
        inHandsRecipe = recipe;
        inHandsIng = null;
    }

    public void setUserControlChef(boolean value){
        userControlChef = value;

    }
    public boolean getUserControlChef(){
        return userControlChef;
    }

    public void dropItemOn(InteractiveTileObject station,Ingredient ing){
        if (station instanceof PlateStation){
            ((PlateStation) station).dropItem(ing);
        }
        setInHandsRecipe(null);
    }

    public void pickUpItemFrom(InteractiveTileObject station){
        if (station instanceof PlateStation){
            PlateStation pStation = (PlateStation) station;
            Object item = pStation.pickUpItem();
            if (item instanceof Ingredient){
                setInHandsIng((Ingredient) item);
                setChefSkin(item);
            } else if (item instanceof Recipe){
                setInHandsRecipe(((Recipe) item));
                setChefSkin(item);
            }
        }
    }
}



