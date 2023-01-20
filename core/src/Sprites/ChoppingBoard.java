package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class ChoppingBoard extends InteractiveTileObject {
    public ChoppingBoard(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);

    }

    @Override
    public void whenTouched() {
        Gdx.app.log("ChoppingBoard", "Collision");
    }
}
