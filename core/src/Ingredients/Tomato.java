package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tomato extends Ingredient{

    public Texture img;
    public Tomato(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        tex.add(new Texture("Food/Tomato.png"));
        tex.add(new Texture("Food/Chopped_tomato.png"));

    }
}
