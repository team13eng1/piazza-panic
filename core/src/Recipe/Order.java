package Recipe;
import Recipe.Recipe;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.MainGame;


public class Order extends Sprite {
    public Recipe recipe;
    public Boolean orderComplete;
    public Texture orderImg;
    public Order(Recipe recipe, Texture orderImg){
        this.recipe = recipe;
        this.orderImg = orderImg;
        this.orderComplete = false;
    }
    public void create(float x, float y, SpriteBatch batch){
        Sprite sprite = new Sprite(orderImg);
        float adjustedX =  x - (8 / MainGame.PPM);
        float adjustedY =  y + (7 / MainGame.PPM);
        if(orderImg.toString().equals("Food/salad_recipe.png")){
            sprite.setBounds(adjustedX,adjustedY,53/ MainGame.PPM,28/ MainGame.PPM);
            sprite.draw(batch);
        } else{
            sprite.setBounds(adjustedX,adjustedY,33/ MainGame.PPM,28/ MainGame.PPM);
            sprite.draw(batch);
        }
    }
}