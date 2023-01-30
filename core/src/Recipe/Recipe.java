package Recipe;

import Ingredients.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.MainGame;

import java.util.ArrayList;

/**
 * The Recipe class is a subclass of Sprite that represents a completed dish in the kitchen game.
 * It holds an ArrayList of {@link Ingredients.Ingredient}s and a Texture of the completed dish image.
 *
 */
public class Recipe extends Sprite {
    protected ArrayList<Ingredient> ingredients;
    protected Texture completedImg;

    /**
     * Constructor for the Recipe class.
     */
    public Recipe(){
    }

    /**
     * Returns the ArrayList of ingredients used in the recipe.
     * @return ingredients The list of ingredients used in the recipe.
     */
    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    /**
     * Creates the completed dish sprite and draws it to the screen.
     * @param x The x-coordinate of the dish's location.
     * @param y The y-coordinate of the dish's location.
     * @param batch The SpriteBatch used to draw the sprite.
     * @see com.badlogic.gdx.graphics.g2d.SpriteBatch
     */
    public void create(float x, float y, SpriteBatch batch){
        Sprite sprite = new Sprite(completedImg);
        float adjustedX =  x - (5/ MainGame.PPM);
        float adjustedY =  y - (4.5f / MainGame.PPM);
        sprite.setBounds(adjustedX,adjustedY,10/ MainGame.PPM,10/ MainGame.PPM);
        sprite.draw(batch);
    }
}

