package Sprites;

import Ingredients.*;
import Recipe.BurgerRecipe;
import Recipe.Recipe;
import Recipe.SaladRecipe;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private Texture saladRecipe;
    private Texture burgerRecipe;
    private Texture saladChef;

    public enum State {UP, DOWN, LEFT, RIGHT}

    public State currentState;
    private TextureRegion currentSkin;

    private Texture skinNeeded;

    private Fixture whatTouching;

    private Ingredient inHandsIng;
    private Recipe inHandsRecipe;

    private Boolean userControlChef;

    public boolean notification;

    private Sprite circleSprite;

    private float notificationX;
    private float notificationY;
    private float notificationWidth;
    private float notificationHeight;

    public boolean completedRecipePlaced;

    public int nextOrderAppearTime;
    public Recipe previousInHandRecipe;

    public Chef(World world, float startX, float startY) {
        initialX = startX / MainGame.PPM;
        initialY = startY / MainGame.PPM;

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
        saladChef = new Texture("Chef/Chef_holding_salad.png");
        burgerRecipe = new Texture("Food/burger_recipe.png");
        saladRecipe = new Texture("Food/salad_recipe.png");


        skinNeeded = normalChef;

        this.world = world;
        currentState = State.DOWN;

        defineChef();

        chefWidth = 13 / MainGame.PPM;
        chefHeight = 20 / MainGame.PPM;
        setBounds(0, 0, chefWidth, chefHeight);
        chefOnChefCollision = false;
        waitTimer = 0;
        startVector = new Vector2(0, 0);
        whatTouching = null;
        inHandsIng = null;
        inHandsRecipe = null;
        userControlChef = true;
        Texture circleTexture = new Texture("Chef/chefIdentifier.png");
        circleSprite = new Sprite(circleTexture);
        nextOrderAppearTime = 3;
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        currentSkin = getSkin(dt);
        setRegion(currentSkin);
        switch (currentState) {
            case UP:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    notificationX = b2body.getPosition().x - (1.75f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (7.7f / MainGame.PPM);
                } else {
                    notificationX = b2body.getPosition().x - (0.67f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (7.2f / MainGame.PPM);
                }
                break;
            case DOWN:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    notificationX = b2body.getPosition().x + (0.95f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    notificationX = b2body.getPosition().x + (0.55f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (5.3f / MainGame.PPM);
                }
                break;
            case LEFT:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    notificationX = b2body.getPosition().x;
                    notificationY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    notificationX = b2body.getPosition().x - (1.92f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (4.6f / MainGame.PPM);
                }
                break;
            case RIGHT:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    notificationX = b2body.getPosition().x + (0.5f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    notificationX = b2body.getPosition().x + (0.17f / MainGame.PPM);
                    notificationY = b2body.getPosition().y - (4.63f / MainGame.PPM);
                }
                break;
        }


        if (userControlChef == false && chefOnChefCollision == true) {
            waitTimer += dt;
            b2body.setLinearVelocity(new Vector2(startVector.x * -1, startVector.y * -1));
            if (waitTimer > 0.3f) {
                b2body.setLinearVelocity(new Vector2(0,0));
                chefOnChefCollision = false;
                userControlChef = true;
                waitTimer = 0;
                if (inHandsIng != null){
                    setChefSkin(inHandsIng);
                }
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
        } else if (userControlChef == false && chefOnChefCollision == false && getInHandsIng().isPrepared() && inHandsIng.cookTime > 0) {
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

    public void notificationSetBounds(String direction) {
        switch (direction) {
            case "Left":
            case "Right":
                notificationWidth = 1.5f / MainGame.PPM;
                notificationHeight = 1.5f / MainGame.PPM;
                break;
            case "Up":
                notificationWidth = 4 / MainGame.PPM;
                notificationHeight = 4 / MainGame.PPM;
                break;
            case "Down":
                notificationWidth = 2 / MainGame.PPM;
                notificationHeight = 2 / MainGame.PPM;
                break;
        }
    }

    public void drawNotificaiton(SpriteBatch batch) {
        if (this.getUserControlChef() == true){
            circleSprite.setBounds(notificationX, notificationY, notificationWidth, notificationHeight);
            circleSprite.draw(batch);
        }
    }

    private TextureRegion getSkin(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
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
        if (b2body.getLinearVelocity().y > 0)
            return State.UP;
        if (b2body.getLinearVelocity().y < 0)
            return State.DOWN;
        if (b2body.getLinearVelocity().x > 0)
            return State.RIGHT;
        if (b2body.getLinearVelocity().x < 0)
            return State.LEFT;
        else
            return currentState;
    }

    public void defineChef() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(initialX, initialY);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MainGame.PPM);
        shape.setPosition(new Vector2(shape.getPosition().x + (0.5f / MainGame.PPM), shape.getPosition().y - (5.5f / MainGame.PPM)));


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
            } else if (inHandsIng.isPrepared()) {
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
        } else if (item instanceof BurgerRecipe) {
            skinNeeded = completedBurgerChef;
        } else if (item instanceof SaladRecipe) {
            skinNeeded = saladChef;
        }
    }

    public void displayIng(SpriteBatch batch) {
        if (whatTouching != null && chefOnChefCollision == false){
            InteractiveTileObject tile = (InteractiveTileObject) whatTouching.getUserData();
            if (tile instanceof ChoppingBoard) {
                ChoppingBoard tileNew = (ChoppingBoard) tile;
                inHandsIng.create(tileNew.getX() - (0.5f / MainGame.PPM), tileNew.getY() - (0.2f / MainGame.PPM), batch);
                setChefSkin(null);
            } else if (tile instanceof Pan) {
                Pan tileNew = (Pan) tile;
                inHandsIng.create(tileNew.getX(), tileNew.getY() - (0.01f / MainGame.PPM), batch);
                setChefSkin(null);
            } else if (tile instanceof CompletedDishStation) {
                CompletedDishStation tileNew = (CompletedDishStation) tile;
                waitTimer += 1/60f;
                previousInHandRecipe.create(tileNew.getX(), tileNew.getY() - (0.01f / MainGame.PPM), batch);
                if (waitTimer > nextOrderAppearTime) {
                    previousInHandRecipe = null;
                    waitTimer = 0;

                }
            }
        }
        }

        public void chefsColliding () {
            userControlChef = false;
            chefOnChefCollision = true;
            setStartVector();
        }

        public void setStartVector () {
            startVector = new Vector2(b2body.getLinearVelocity().x, b2body.getLinearVelocity().y);

        }
        public void setTouchingTile (Fixture obj){
            this.whatTouching = obj;
        }

        public Fixture getTouchingTile () {
            if (whatTouching == null) {
                return null;
            } else {
                return whatTouching;
            }
        }
        public Ingredient getInHandsIng () {
            return inHandsIng;
        }
        public Recipe getInHandsRecipe () {
            return inHandsRecipe;
        }

        public void setInHandsIng (Ingredient ing){
            inHandsIng = ing;
            inHandsRecipe = null;
        }

        public void setInHandsRecipe (Recipe recipe){
            inHandsRecipe = recipe;
            inHandsIng = null;
        }

        public void setUserControlChef ( boolean value){
            userControlChef = value;

        }
        public boolean getUserControlChef () {
            if (userControlChef == null) {
                return false;
            } else {
                return userControlChef;
            }
        }

        public void dropItemOn (InteractiveTileObject station, Ingredient ing){
            if (station instanceof PlateStation) {
                ((PlateStation) station).dropItem(ing);
            }
            setInHandsRecipe(null);
        }

        public void dropItemOn (InteractiveTileObject station, Recipe recipe){
            if (station instanceof CompletedDishStation) {
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



