package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Represents a station where completed dishes can be placed.
 */
public class CompletedDishStation extends InteractiveTileObject {
    /**
     * Creates a new instance of CompletedDishStation
     *
     * @param world The world where the object is located
     * @param map The TiledMap the object belongs to
     * @param bdef Body definition for the object
     * @param rectangle The rectangle that defines the object's size and location
     */
    public CompletedDishStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }

    /**
     * Gets the x position of the object.
     *
     * @return The x position of the object
     */
    public float getX(){
        return super.bdefNew.position.x;
    }

    /**
     * Gets the y position of the object.
     *
     * @return The y position of the object
     */
    public float getY(){
        return super.bdefNew.position.y;
    }
}

