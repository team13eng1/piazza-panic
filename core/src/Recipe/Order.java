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
        float adjustedX =  x - (5/ MainGame.PPM);
        float adjustedY =  y - (3.7f / MainGame.PPM);
        sprite.setBounds(adjustedX,adjustedY,10/ MainGame.PPM,10/ MainGame.PPM);
        sprite.draw(batch);
    }
}