package Ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Lettuce extends Ingredient{
    public Texture img;
    public Lettuce(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        img = new Texture("Food/Chopped_lettuce.png");
    }
}
