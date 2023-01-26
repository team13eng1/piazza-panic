package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Onion extends Ingredient{

    public Texture img;
    public Onion(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/Onion.png"));
        super.tex.add(new Texture("Food/Chopped_onion.png"));
    }
}
