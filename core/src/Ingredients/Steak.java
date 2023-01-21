package Ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Steak extends Ingredient{
    public Texture img;
    public Steak(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        img = new Texture("Food/Cooked_patty.png");
    }
}