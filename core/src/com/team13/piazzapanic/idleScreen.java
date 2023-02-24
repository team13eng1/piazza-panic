package com.team13.piazzapanic;

import Ingredients.Ingredient;
import Recipe.Order;
import Recipe.Recipe;
import Sprites.*;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
import Tools.chefAI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class idleScreen implements Screen {

    private final MainGame game;
    private final OrthographicCamera gamecam;
    private final Viewport gameport;
    private final HUD hud;
    private orderBar orderTimer =  new  orderBar(105,120,50,5, Color.RED);;
    private float orderTime = 1;
    private boolean isActiveOrder = false;
    private GameOver gameover;
    public final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    public final Chef chef1;
    public final Chef chef2;

    private Chef controlledChef;

    public ArrayList<Order> ordersArray;

    public PlateStation plateStation;


    public Boolean scenarioComplete;
    public Boolean createdOrder;

    public static float trayX;
    public static float trayY;

    private float timeSeconds = 0f;

    private float timeSecondsCount = 0f;
    private chefAI aiChef;

    /**
     * PlayScreen constructor initializes the game instance, sets initial conditions for scenarioComplete and createdOrder,
     * creates and initializes game camera and viewport,
     * creates and initializes HUD and orders hud, loads and initializes the map,
     * creates and initializes world, creates and initializes chefs and sets them, sets contact listener for world, and initializes ordersArray.
     * @param game The MainGame instance that the PlayScreen will be a part of.
     */

    public idleScreen(MainGame game){
        aiChef = new chefAI(this);
        this.game = game;
        gameover = new GameOver(game);

        scenarioComplete = Boolean.FALSE;
        createdOrder = Boolean.FALSE;
        gamecam = new OrthographicCamera();
        // FitViewport to maintain aspect ratio whilst scaling to screen size
        gameport = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, gamecam);
        // create HUD for score & time
        hud = new HUD(game.batch);
        // create orders hud
        Orders orders = new Orders(game.batch);
        // create map
        TmxMapLoader mapLoader = new TmxMapLoader(new InternalFileHandleResolver());
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

    }

    @Override
    public void show(){

    }


    /**
     * The handleInput method is responsible for handling the input events of the game such as movement and interaction with objects.
     *
     * It checks if the 'R' key is just pressed and both chefs have the user control, if so,
     * it switches the control between the two chefs.
     *
     * If the controlled chef does not have the user control,
     * the method checks if chef1 or chef2 have the user control and sets the control to that chef.
     *
     * If the controlled chef has the user control,
     * it checks if the 'W', 'A', 'S', or 'D' keys are pressed and sets the velocity of the chef accordingly.
     *
     * If the 'E' key is just pressed and the chef is touching a tile,
     * it checks the type of tile and sets the chef's in-hands ingredient accordingly.
     *
     * The method also sets the direction of the chef based on its linear velocity.
     *
     * @param dt is the time delta between the current and previous frame.
     */

    public void handleInput(float dt, String AIinput){
        if ((AIinput == "r"  &&
                chef1.getUserControlChef() &&
                chef2.getUserControlChef())) {

            if (controlledChef.equals(chef1)) {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef2;
            } else {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef1;
            }
        }
        if (!controlledChef.getUserControlChef()){
            if (chef1.getUserControlChef()){
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef1;
            } else if(chef2.getUserControlChef()) {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef2;
            }
        }
        if (controlledChef.getUserControlChef()) {
            float xVelocity = 0;
            float yVelocity = 0;

            if (AIinput == "w") {
                yVelocity += 0.5f;
            }
            if (AIinput == "a") {
                xVelocity -= 0.5f;
            }
            if (AIinput == "s") {
                yVelocity -= 0.5f;
            }
            if (AIinput == "d") {
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


        if(AIinput == "e"){
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
                                    //TODO UPDATE CHANGE LOG FOR THIS
                                    if (orderTime == 0){
                                        hud.decrementReps();
                                        if (hud.getRepPoints() == 0){System.out.println("game over"); game.goToGameOver();}
                                    }
                                    controlledChef.dropItemOn(tile);
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

    /**
     * The update method updates the game elements, such as camera and characters,
     * based on a specified time interval "dt".
     * @param dt time interval for the update
     */
    public void update(float dt){
        //TODO implement AI chef
        aiChef.returnKeyboardInput();
        handleInput(dt, "w");

        gamecam.update();
        renderer.setView(gamecam);
        chef1.update(dt);
        chef2.update(dt);
        world.step(1/60f, 6, 2);

    }

    /**
     * Creates the orders randomly and adds to an array, updates the HUD.
     */
    public void createOrder() {
        isActiveOrder = true;
        orderTime = 1;

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

    /**
     * Updates the orders as they are completed, or if the game scenario has been completed.
     */
    public void updateOrder(){
        if(scenarioComplete==Boolean.TRUE) {
            hud.updateScore(Boolean.TRUE, (6 - ordersArray.size()) * 35);
            hud.updateOrder(Boolean.TRUE, 0);
            return;
        }
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

    /**

     The render method updates the screen by calling the update method with the given delta time, and rendering the graphics of the game.

     It updates the HUD time, clears the screen, and renders the renderer and the hud.

     Additionally, it checks the state of the game and draws the ingredients, completed recipes, and notifications on the screen.

     @param delta The time in seconds since the last frame.
     */
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
        float period = 1f;
        if(timeSeconds > period) {
            timeSeconds -= period;
            hud.updateTime(scenarioComplete);
        }

        Gdx.gl.glClear(1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        updateOrder();
        chef1.draw(game.batch);
        chef2.draw(game.batch);
        controlledChef.drawNotification(game.batch);
        if (isActiveOrder){
            // TODO add this if statement to if report
            orderTimer.draw(game.batch, 1);
            hud.stage.addActor(orderTimer);
            if (orderTime > 0){ orderTime -= 0.01f;}
            else {orderTime = 0;}



            orderTimer.setPercentage(orderTime);
        }
        if (plateStation.getPlate().size() > 0){
            for(Object ing : plateStation.getPlate()){
                Ingredient ingNew = (Ingredient) ing;
                ingNew.create(plateStation.getX(), plateStation.getY(),game.batch);
            }
        } else if (plateStation.getCompletedRecipe() != null){
            Recipe recipeNew = plateStation.getCompletedRecipe();
            recipeNew.create(plateStation.getX(), plateStation.getY(), game.batch);
        }
        if (!chef1.getUserControlChef()) {
            if (chef1.getTouchingTile() != null && chef1.getInHandsIng() != null){
                if (chef1.getTouchingTile().getUserData() instanceof InteractiveTileObject){
                    chef1.displayIngStatic(game.batch);
                }
            }
        }
        if (!chef2.getUserControlChef()) {
            if (chef2.getTouchingTile() != null && chef2.getInHandsIng() != null) {
                if (chef2.getTouchingTile().getUserData() instanceof InteractiveTileObject) {
                    chef2.displayIngStatic(game.batch);
                }
            }
        }
        if (chef1.previousInHandRecipe != null){
            chef1.displayIngDynamic(game.batch);
        }
        if (chef2.previousInHandRecipe != null){
            chef2.displayIngDynamic(game.batch);
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
        hud.dispose();
    }
}

