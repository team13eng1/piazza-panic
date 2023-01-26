package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Bun extends Ingredient{


    public Bun(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.setPrepared(true);
        super.tex = new ArrayList<>();
        super.tex.add(null);
        super.tex.add(new Texture("Food/Burger_Buns.png"));
        super.tex.add(new Texture("Food/Toasted_burger_buns.png"));
    }
}
