package Recipe;

import Ingredients.Bun;
import Ingredients.Steak;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class BurgerRecipe extends Recipe{

    public BurgerRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new Bun(0,0));
        ingredients.add(new Steak(0,0));
        ingredients.add(new Bun(0,0));
        completedImg = new Texture("Food/Burger.png");
    }
}
