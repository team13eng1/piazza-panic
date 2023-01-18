package Tools;

import Sprites.Chef;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() != null && fixB.getUserData() != null) {

            if (Chef.class.isAssignableFrom(fixA.getUserData().getClass()) && Chef.class.isAssignableFrom(fixB.getUserData().getClass())) {
                System.out.printf(fixA.getUserData().getClass().getName());
                ((Chef) fixA.getUserData()).chefsColliding();
                ((Chef) fixB.getUserData()).chefsColliding();
            }




        }
    }
    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub
        
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
