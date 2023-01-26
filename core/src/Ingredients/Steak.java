package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Steak extends Ingredient{
    public Steak(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/Meat.png"));
        super.tex.add(new Texture("Food/Patty.png"));
        super.tex.add(new Texture("Food/Cooked_patty.png"));
    }
}