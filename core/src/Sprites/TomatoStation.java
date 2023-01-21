package Sprites;

import Ingredients.Ingredient;
import Ingredients.Tomato;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class TomatoStation extends InteractiveTileObject {

private Ingredient tomato;

    public TomatoStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }

    public Ingredient getIngredient(){
        return new Tomato(2,0);
    }
}

