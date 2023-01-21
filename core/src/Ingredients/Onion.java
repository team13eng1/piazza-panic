package Ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Onion extends Ingredient{

    public Texture img;
    public Onion(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        img = new Texture("Food/Chopped_onion.png");
    }
}
