package Sprites;

import Ingredients.Bun;
import Ingredients.Ingredient;
import Ingredients.Steak;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class SteakStation extends InteractiveTileObject {

    private Ingredient steak;
    public SteakStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }
    public Ingredient getIngredient(){
       return new Steak(2,3);
    }
}

