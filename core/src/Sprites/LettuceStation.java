package Sprites;

import Ingredients.Lettuce;
import Ingredients.Ingredient;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class LettuceStation extends InteractiveTileObject {

    private Ingredient lettuce;
    public LettuceStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);

    }
    public Ingredient getIngredient(){
        return (new Lettuce(2,0));
    }
}

