package Recipe;

import Ingredients.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.MainGame;

import java.util.ArrayList;

public class Recipe extends Sprite {
    protected ArrayList<Ingredient> ingredients;
    protected Texture completedImg;

    public Recipe(){
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    public void create(float x, float y, SpriteBatch batch){
        Sprite sprite = new Sprite(completedImg);
        float adjustedX =  x - (5/ MainGame.PPM);
        float adjustedY =  y - (3.7f / MainGame.PPM);
        sprite.setBounds(adjustedX,adjustedY,10/ MainGame.PPM,10/ MainGame.PPM);
        sprite.draw(batch);
    }
}
