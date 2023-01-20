package Tools;

import Sprites.Bin;
import Sprites.Chef;
import Sprites.InteractiveTileObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    
    @Override
    public void beginContact(Contact contact) {

        Gdx.app.log("Begin contact", "");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Chef || fixB.getUserData() instanceof Chef) {
            Gdx.app.log(" | Instance of chef found | ", "");
            Fixture chefFixt = fixA.getUserData() instanceof Chef ? fixA : fixB;
            Fixture objectFixt = fixA.getUserData() instanceof Chef ? fixB: fixA;

            if(fixA.getUserData() instanceof Chef && fixB.getUserData() instanceof Chef){ // if both are chef
                Gdx.app.log(" | Chef on chef contact | ", "");
                Chef chef = ((Chef) fixA.getUserData());
                Chef chefb = ((Chef) fixB.getUserData());
                System.out.printf(chef.getClass().getName());
                chef.chefsColliding();
                chefb.chefsColliding();

            }
            else if(objectFixt.getUserData() != null && objectFixt.getUserData() instanceof InteractiveTileObject){ // if chef interacts with tile
                Gdx.app.log(" | Chef on tile contact | ", "");
                System.out.printf(objectFixt.getUserData().getClass().getName());
                if (objectFixt.getUserData().getClass().getName().equals("Sprites.Worktop")){
                    System.out.printf("hello");
                }
            }
        }
    }


    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End contact", "");

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub
        
    }
}
