package Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class orderBar extends Actor {
    private Texture texture;
    private Texture texture2;
    private float percentage = 1f;

    public orderBar(float x, float y, float width, float height, Color color){
        setColor(color);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        makeTexture((int)width, (int)height, color);

    }
    private void makeTexture(int width, int height, Color color){
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,0,width,height);
        texture = new Texture(pixmap);
        pixmap.dispose();


        Pixmap pixmap2 = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.GREEN);
        pixmap2.fillRectangle(0,0,(int)( width * percentage) ,height);
        texture2 = new Texture(pixmap2);
        pixmap2.dispose();
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        Color newCol = Color.GREEN;
        //batch.setColor(newCol.r, newCol.g, newCol.b, color.a * parentAlpha);
         if (percentage > 0) {
             batch.draw(texture2, getX(), getY(), getWidth() * percentage, getHeight());
         }

    }

    public void setPercentage(float percent){
        percentage = percent;
    }

    public float getPercentage() {
        return percentage;
    }
}
