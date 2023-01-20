package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.team13.piazzapanic.MainGame;


public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;

    protected Fixture fixture;
    public InteractiveTileObject(World world, TiledMap map, BodyDef bdef, Rectangle rectangle){
        this.world = world;
        this.map = map;

        Body b2body = world.createBody(bdef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((rectangle.getWidth() / 2f) / MainGame.PPM, (rectangle.getHeight() / 2f) / MainGame.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fixture = b2body.createFixture(fdef);
    }

    public abstract void whenTouched();
}
