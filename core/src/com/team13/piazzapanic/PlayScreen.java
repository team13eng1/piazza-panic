package com.team13.piazzapanic;

import Ingredients.Ingredient;
import Recipe.Recipe;
import Sprites.*;
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

public class PlayScreen implements Screen {

    private MainGame game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Chef chef1;
    private Chef chef2;

    private Chef controlledChef;

    public PlateStation plateStation;


    public PlayScreen(MainGame game){
        this.game = game;

        // camera used to follow chef
        gamecam = new OrthographicCamera();
        // FitViewport to maintain aspect ratio whilst scaling to screen size
        gameport = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, gamecam);
        // create HUD for score & time
        hud = new HUD(game.batch);
        // create map
        mapLoader = new TmxMapLoader(new InternalFileHandleResolver());
        map = mapLoader.load("Kitchen.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MainGame.PPM);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(world, map, this);

        chef1 = new Chef(this.world, 31.5F,65);
        chef2 = new Chef(this.world, 128,65);
        controlledChef = chef1;
        world.setContactListener(new WorldContactListener());

    }

    @Override
    public void show(){

    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            if (controlledChef.equals(chef1)) {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef2;
            } else {
                controlledChef.b2body.setLinearVelocity(0, 0);
                controlledChef = chef1;
            }
        }
        if (controlledChef.getUserControlChef() == true) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) ||
                    Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    controlledChef.b2body.setLinearVelocity(0, 0.5f);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    controlledChef.b2body.setLinearVelocity(0.5f, 0);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    controlledChef.b2body.setLinearVelocity(-0.5f, 0);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    controlledChef.b2body.setLinearVelocity(0, -0.5f);
                }
            } else {
                controlledChef.b2body.setLinearVelocity(0, 0);
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
                               if(controlledChef.getInHandsIng().prepareTime > 0){
                                    controlledChef.setUserControlChef(false);
                                }
                               break;
                            case "Sprites.PlateStation":
                                if (controlledChef.getInHandsRecipe() == null){
                                controlledChef.dropItemOn(tile, controlledChef.getInHandsIng());
                                controlledChef.setChefSkin(null);
                            }
                                break;
                            case "Sprites.Pan":
                                if (controlledChef.getInHandsIng().isPrepared() && controlledChef.getInHandsIng().cookTime > 0){
                                    controlledChef.setUserControlChef(false);
                                }
                        }
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

    @Override
    public void render(float delta){
        update(delta);

        Gdx.gl.glClear(1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        chef1.draw(game.batch);
        chef2.draw(game.batch);
        if (plateStation.getPlate().size() > 0){
            for(Object ing : plateStation.getPlate()){
                Ingredient ingNew = (Ingredient) ing;
                ingNew.create(plateStation.getX(), plateStation.getY(),game.batch);
            }
        } else if (plateStation.getCompletedRecipe() != null){
            Recipe recipeNew = plateStation.getCompletedRecipe();
            recipeNew.create(plateStation.getX(), plateStation.getY(), game.batch);
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
