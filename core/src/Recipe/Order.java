package Recipe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.MainGame;

/**
 * The `Order` class extends the `Sprite` class and represents a recipe order
 * in the game.
 */
public class Order extends Sprite {
    /** The `Recipe` object associated with this order. */
    public Recipe recipe;
    /** A flag indicating whether the order has been completed. */
    public Boolean orderComplete;
    /** The image representing this order. */
    public Texture orderImg;

    /**
     * Constructor for the `Order` class.
     *
     * @param recipe The `Recipe` object associated with this order.
     * @param orderImg The image representing this order.
     */
    public Order(Recipe recipe, Texture orderImg) {
        this.recipe = recipe;
        this.orderImg = orderImg;
        this.orderComplete = false;
    }

    /**
     * Creates the order image and adds it to the given `SpriteBatch`.
     *
     * @param x The x coordinate of the order image.
     * @param y The y coordinate of the order image.
     * @param batch The `SpriteBatch` to add the order image to.
     */
    public void create(float x, float y, SpriteBatch batch) {
        Sprite sprite = new Sprite(orderImg);
        float adjustedX = x - (8 / MainGame.PPM);
        float adjustedY = y + (7 / MainGame.PPM);
        if (orderImg.toString().equals("Food/salad_recipe.png")) {
            sprite.setBounds(adjustedX, adjustedY, 53 / MainGame.PPM, 28 / MainGame.PPM);
            sprite.draw(batch);
        } else {
            sprite.setBounds(adjustedX, adjustedY, 33 / MainGame.PPM, 28 / MainGame.PPM);
            sprite.draw(batch);
        }
    }
}
