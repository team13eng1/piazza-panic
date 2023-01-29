package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Steak extends Ingredient{

    /**
     * The Steak class represents a specific type of ingredient in the game, specifically steak.
     * It extends the {@link Ingredient} class and has a preparation time and cooking time.
     * The Steak class sets up an ArrayList of textures for its different skins.
     */

    public Steak(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/Meat.png"));
        super.tex.add(new Texture("Food/Patty.png"));
        super.tex.add(new Texture("Food/Cooked_patty.png"));
    }
}