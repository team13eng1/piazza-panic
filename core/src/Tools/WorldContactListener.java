package Tools;

import Sprites.Chef;
import Sprites.InteractiveTileObject;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Chef || fixB.getUserData() instanceof Chef) {
            Fixture chefFixt = fixA.getUserData() instanceof Chef ? fixA : fixB;
            Fixture objectFixt = fixA.getUserData() instanceof Chef ? fixB: fixA;

            if(fixA.getUserData() instanceof Chef && fixB.getUserData() instanceof Chef){ // if both are chef
                Chef chef = ((Chef) fixA.getUserData());
                Chef chefb = ((Chef) fixB.getUserData());
                chef.chefsColliding();
                chefb.chefsColliding();

            }
            else if(objectFixt.getUserData() != null && objectFixt.getUserData() instanceof InteractiveTileObject){ // if chef interacts with tile
                ((Chef) chefFixt.getUserData()).setTouchingTile(objectFixt);

            }
        }
    }


    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Chef || fixB.getUserData() instanceof Chef) {
            Fixture chefFixt = fixA.getUserData() instanceof Chef ? fixA : fixB;
            Fixture objectFixt = fixA.getUserData() instanceof Chef ? fixB: fixA;

            if(objectFixt.getUserData() != null && objectFixt.getUserData() instanceof InteractiveTileObject){ // if chef interacts with tile
                System.out.printf(objectFixt.getUserData().getClass().getName());
                ((Chef) chefFixt.getUserData()).setTouchingTile(null);
            }
        }
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
