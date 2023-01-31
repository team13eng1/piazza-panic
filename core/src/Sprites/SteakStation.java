package Sprites;

import Ingredients.Ingredient;
import Ingredients.Steak;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * SteakStation is a concrete class that extends the {@link InteractiveTileObject} class.
 * Represents a SteakStation in the game where the chef can pick up a steak
 */
public class SteakStation extends InteractiveTileObject {

    public SteakStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }
    public Ingredient getIngredient(){
       return new Steak(2,3);
    }
}

