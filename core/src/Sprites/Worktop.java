package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Represents a Worktop in the game in which blocks the chefs movement around the kitchen to stop him from
 * escaping the bounds
 */
public class Worktop extends InteractiveTileObject {
    public Worktop(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);

    }
}