package Ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Tomato extends Ingredient{

    public Texture img;
    public Tomato(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        img = new Texture("Food/Chopped_tomato.png");
    }
}
