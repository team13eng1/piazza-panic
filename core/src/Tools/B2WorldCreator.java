package Tools;

import Sprites.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.team13.piazzapanic.MainGame;
import com.team13.piazzapanic.PlayScreen;

/**
 * B2WorldCreator is a class used to create Box2D World objects from a TiledMap.
 * This class uses the map objects to create various objects like worktop, plates,
 * chopperboard, bin, etc. based on the name assigned to the objects in the TiledMap.
 *
 * The class is instantiated with a World object, TiledMap object and PlayScreen object.
 * It then uses the first layer of the TiledMap to create the objects and assign their
 * positions. The objects are created as BodyDef objects and are passed to different sprite
 * classes, where they are further defined and added to the world.
 *
 */
public class B2WorldCreator {

/**
 * Constructor method for B2WorldCreator. It accepts a World, TiledMap and PlayScreen
 * objects. The method then iterates over the cells in the first layer of the TiledMap and
 * uses the map objects to create various objects like worktop, plates, chopperboard,
 * bin, etc. based on the name assigned to the objects in the TiledMap.
 *
 * The objects are created as BodyDef objects and are passed to different sprite classes,
 * where they are further defined and added to the world.
 *
 * @param world The Box2D World object.
 * @param map The TiledMap object.
 * */

    public B2WorldCreator(World world, TiledMap map, PlayScreen screen) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell == null) {
                    continue;
                }

                MapObjects cellObjects = cell.getTile().getObjects();
                if (cellObjects.getCount() != 1)
                    continue;

                MapObject mapObject = cellObjects.get(0);
                RectangleMapObject rectangleObject = (RectangleMapObject) mapObject;
                Rectangle rectangle = rectangleObject.getRectangle();

                BodyDef bdef = new BodyDef();
                float position_x = x * MainGame.TILE_SIZE + MainGame.TILE_SIZE / 2f + rectangle.getX()
                        - (MainGame.TILE_SIZE - rectangle.getWidth()) / 2f;
                float position_y = y * MainGame.TILE_SIZE + MainGame.TILE_SIZE / 2f + rectangle.getY()
                        - (MainGame.TILE_SIZE - rectangle.getHeight()) / 2f;
                bdef.position.set(position_x / MainGame.PPM, position_y / MainGame.PPM);
                bdef.type = BodyDef.BodyType.StaticBody;

                if (mapObject.getName().equals("bin")) {
                    new Bin(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("worktop")) {
                    new Worktop(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("chopping_board")) {
                    new ChoppingBoard(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("plate")) {
                    screen.plateStation = new PlateStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("tomato")) {
                    new TomatoStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("lettuce")) {
                    new LettuceStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("buns")) {
                    new BunsStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("onion")) {
                    new OnionStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("pan1")) {
                    new Pan(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("steak")) {
                    new SteakStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("pan2")) {
                    new Pan(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("completed_dish")) {
                    new CompletedDishStation(world, map, bdef, rectangle);
                } else if (mapObject.getName().equals("order_top")) {
                    PlayScreen.trayX = rectangle.x;
                    PlayScreen.trayY = rectangle.y;
                }

            }
        }
    }
}
