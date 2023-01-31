package Sprites;

import Ingredients.Lettuce;
import Ingredients.Ingredient;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The `LettuceStation` class extends the `InteractiveTileObject` class.
 * It is used to represent a station for producing lettuce in the game.
 */
public class LettuceStation extends InteractiveTileObject {
    /**
     * Constructs a LettuceStation.
     *
     * @param world the world where the LettuceStation will be located
     * @param map the TiledMap that the LettuceStation is in
     * @param bdef the BodyDef for the physics of the LettuceStation
     * @param rectangle the rectangle representing the boundaries of the LettuceStation
     */
    public LettuceStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }

    /**
     * Returns the ingredient Lettuce.
     *
     * @return a Lettuce object
     */
    public Ingredient getIngredient(){
        return (new Lettuce(2,0));
    }
}


