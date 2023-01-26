package Recipe;

import Ingredients.*;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class SaladRecipe extends Recipe {
    public SaladRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new Lettuce(0,0));
        ingredients.add(new Tomato(0,0));
        ingredients.add(new Onion(0,0));
        completedImg = new Texture("Food/Salad.png");
    }
}
