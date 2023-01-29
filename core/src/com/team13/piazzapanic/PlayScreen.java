package com.team13.piazzapanic;

import Ingredients.Ingredient;
import Recipe.Recipe;
import Sprites.*;
import Recipe.Order;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class PlayScreen implements Screen {

    private MainGame game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private HUD hud;

    private Orders orders;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Chef chef1;
    private Chef chef2;

    private Chef controlledChef;

    public ArrayList<Order> ordersArray;

    public PlateStation plateStation;


    public Boolean scenarioComplete;
    public Boolean createdOrder;

    public static float trayX;
    public static float trayY;

    private float timeSeconds = 0f;

    private float timeSecondsCount = 0f;

    private float period = 1f;

    public PlayScreen(MainGame game){
        this.game = game;
        scenarioComplete = Boolean.FALSE;
        createdOrder = Boolean.FALSE;
        // camera used to follow chef
        gamecam = new OrthographicCamera();
        // FitViewport to maintain aspect ratio whilst scaling to screen size
        gameport = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, gamecam);
        // create HUD for score & time
        hud = new HUD(game.batch);
        // create orders hud
        orders = new Orders(game.batch);
        // create map
        mapLoader = new TmxMapLoader(new InternalFileHandleResolver());
        map = mapLoader.load("Kitchen.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MainGame.PPM);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        new B2WorldCreator(world, map, this);

        chef1 = new Chef(this.world, 31.5F,65);
        chef2 = new Chef(this.world, 128,65);
        controlledChef = chef1;
        world.setContactListener(new WorldContactListener());
        controlledChef.notificationSetBounds("Down");

        ordersArray = new ArrayList<>();

        b2dr = new Box2DDebugRenderer();

    }

    @Override
    public void show(){

    }

    public void handleInput(float dt){
        if ((Gdx.input.isKeyJustPressed(Input.Keys.R) &&
                chef1.getUserControlChef() == true &&
                chef2.getUserControlChef() == true)) {
            if (controlledChef.equals(chef1)) {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef2;
            } else {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef1;
            }
        }
        if (controlledChef.getUserControlChef() == false){
            if (chef1.getUserControlChef() == true){
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef1;
            } else if(chef2.getUserControlChef() == true) {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef2;
            }
        }
        if (controlledChef.getUserControlChef() == true) {
                float xVelocity = 0;
                float yVelocity = 0;

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    yVelocity += 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    xVelocity -= 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    yVelocity -= 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    xVelocity += 0.5f;
                }
                controlledChef.b2body.setLinearVelocity(xVelocity, yVelocity);
            }
            else {
                controlledChef.b2body.setLinearVelocity(0, 0);
            }
        if (controlledChef.b2body.getLinearVelocity().x > 0){
            controlledChef.notificationSetBounds("Right");
        }
        if (controlledChef.b2body.getLinearVelocity().x < 0){
            controlledChef.notificationSetBounds("Left");
        }
        if (controlledChef.b2body.getLinearVelocity().y > 0){
            controlledChef.notificationSetBounds("Up");
        }
        if (controlledChef.b2body.getLinearVelocity().y < 0){
            controlledChef.notificationSetBounds("Down");
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                if(controlledChef.getTouchingTile() != null){
                    InteractiveTileObject tile = (InteractiveTileObject) controlledChef.getTouchingTile().getUserData();
                    String tileName = tile.getClass().getName();
                    if (controlledChef.getInHandsIng() == null && controlledChef.getInHandsRecipe() == null) {
                        switch (tileName) {
                            case "Sprites.TomatoStation":
                                TomatoStation tomatoTile = (TomatoStation) tile;
                                controlledChef.setInHandsIng(tomatoTile.getIngredient());
                                controlledChef.setChefSkin(controlledChef.getInHandsIng());
                                break;
                            case "Sprites.BunsStation":
                                BunsStation bunTile = (BunsStation) tile;
                                controlledChef.setInHandsIng(bunTile.getIngredient());
                                controlledChef.setChefSkin(controlledChef.getInHandsIng());
                                break;
                            case "Sprites.OnionStation":
                                OnionStation onionTile = (OnionStation) tile;
                                controlledChef.setInHandsIng(onionTile.getIngredient());
                                controlledChef.setChefSkin(controlledChef.getInHandsIng());
                                break;
                            case "Sprites.SteakStation":
                                SteakStation steakTile = (SteakStation) tile;
                                controlledChef.setInHandsIng(steakTile.getIngredient());
                                controlledChef.setChefSkin(controlledChef.getInHandsIng());
                                break;
                            case "Sprites.LettuceStation":
                                LettuceStation lettuceTile = (LettuceStation) tile;
                                controlledChef.setInHandsIng(lettuceTile.getIngredient());
                                controlledChef.setChefSkin(controlledChef.getInHandsIng());
                                break;
                            case "Sprites.PlateStation":
                                if(plateStation.getPlate().size() > 0 || plateStation.getCompletedRecipe() != null){
                                    controlledChef.pickUpItemFrom(tile);

                                }

                        }
                    } else {
                        switch (tileName) {
                            case "Sprites.Bin":
                                controlledChef.setInHandsRecipe(null);
                                controlledChef.setInHandsIng(null);
                                controlledChef.setChefSkin(null);
                                break;

                            case "Sprites.ChoppingBoard":
                                if(controlledChef.getInHandsIng() != null){
                                    if(controlledChef.getInHandsIng().prepareTime > 0){
                                        controlledChef.setUserControlChef(false);
                                    }
                                }
                               break;
                            case "Sprites.PlateStation":
                                if (controlledChef.getInHandsRecipe() == null){
                                controlledChef.dropItemOn(tile, controlledChef.getInHandsIng());
                                controlledChef.setChefSkin(null);
                            }
                                break;
                            case "Sprites.Pan":
                                if(controlledChef.getInHandsIng() != null) {
                                    if (controlledChef.getInHandsIng().isPrepared() && controlledChef.getInHandsIng().cookTime > 0){
                                        controlledChef.setUserControlChef(false);
                                    }
                                }

                                break;
                            case "Sprites.CompletedDishStation":
                                if (controlledChef.getInHandsRecipe() != null){
                                    if(controlledChef.getInHandsRecipe().getClass().equals(ordersArray.get(0).recipe.getClass())){
                                        controlledChef.dropItemOn(tile, controlledChef.getInHandsRecipe());
                                        ordersArray.get(0).orderComplete = true;
                                        controlledChef.setChefSkin(null);
                                        if(ordersArray.size()==1){
                                            scenarioComplete = Boolean.TRUE;
                                        }
                                    }
                                }
                                break;
                        }
                    }

                }
            }
        }
    public void update(float dt){
        handleInput(dt);

        gamecam.update();
        renderer.setView(gamecam);
        chef1.update(dt);
        chef2.update(dt);
        world.step(1/60f, 6, 2);

    }

    public void createOrder() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        Texture burger_recipe = new Texture("Food/burger_recipe.png");
        Texture salad_recipe = new Texture("Food/salad_recipe.png");
        Order order;

        for(int i = 0; i<5; i++){
            if(randomNum==1) {
                order = new Order(PlateStation.burgerRecipe, burger_recipe);
            }
            else {
                order = new Order(PlateStation.saladRecipe, salad_recipe);
            }
            ordersArray.add(order);
            randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        }
        hud.updateOrder(Boolean.FALSE, 1);
    }
    public void updateOrder(){
        if(scenarioComplete==Boolean.TRUE) {
            hud.updateScore(Boolean.TRUE, (6 - ordersArray.size()) * 35);
            hud.updateOrder(Boolean.TRUE, 0);
            return;
        } // end game
        if(ordersArray.size() != 0) {
            if (ordersArray.get(0).orderComplete) {
                hud.updateScore(Boolean.FALSE, (6 - ordersArray.size()) * 35);
                ordersArray.remove(0);
                hud.updateOrder(Boolean.FALSE, 6 - ordersArray.size());
                return;
            }
            ordersArray.get(0).create(trayX, trayY, game.batch);
        }
    }

    @Override
    public void render(float delta){
        update(delta);

        //Execute handleEvent each 1 second
        timeSeconds +=Gdx.graphics.getRawDeltaTime();
        timeSecondsCount += Gdx.graphics.getDeltaTime();

        if(Math.round(timeSecondsCount) == 5 && createdOrder == Boolean.FALSE){
            createdOrder = Boolean.TRUE;
            createOrder();
        }
        if(timeSeconds > period) {
            timeSeconds -= period;
            hud.updateTime(scenarioComplete);
        }

        Gdx.gl.glClear(1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        updateOrder();
        chef1.draw(game.batch);
        chef2.draw(game.batch);
        controlledChef.drawNotificaiton(game.batch);
        if (plateStation.getPlate().size() > 0){
            for(Object ing : plateStation.getPlate()){
                Ingredient ingNew = (Ingredient) ing;
                ingNew.create(plateStation.getX(), plateStation.getY(),game.batch);
            }
        } else if (plateStation.getCompletedRecipe() != null){
            Recipe recipeNew = plateStation.getCompletedRecipe();
            recipeNew.create(plateStation.getX(), plateStation.getY(), game.batch);
        }
        if (chef1.getUserControlChef() == false) {
            if (chef1.getTouchingTile() != null && chef1.getInHandsIng() != null){
                if (chef1.getTouchingTile().getUserData() instanceof InteractiveTileObject){
                    System.out.print("£HEHEEHEEFHGJFjdh");
                    chef1.displayIngStatic(game.batch);
                }
            }
        }
        if (chef2.getUserControlChef() == false) {
            if (chef2.getTouchingTile() != null && chef2.getInHandsIng() != null) {
                if (chef2.getTouchingTile().getUserData() instanceof InteractiveTileObject) {
                    chef2.displayIngStatic(game.batch);
                }
            }
        }
        if (controlledChef.previousInHandRecipe != null){
            controlledChef.displayIngDynamic(game.batch);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height){
        gameport.update(width, height);
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){
        
    }

    @Override
    public void hide(){

    }

    @Override
    public void dispose(){
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
