package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Lettuce extends Ingredient{
    public Lettuce(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/Lettuce.png"));
        super.tex.add(new Texture("Food/Chopped_lettuce.png"));
    }
}
