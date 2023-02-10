package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tomato extends Ingredient{

    /**
     * The Tomato class represents a specific type of ingredient in the game, specifically tomatoes.
     * It extends the {@link Ingredient} class and has a preparation time and cooking time.
     * The Tomato class sets up an ArrayList of textures for its different skins.
     */

    public Tomato(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        tex.add(new Texture("Food/Tomato.png"));
        tex.add(new Texture("Food/Chopped_tomato.png"));

    }
}
