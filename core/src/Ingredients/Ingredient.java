package Ingredients;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.MainGame;

import java.util.ArrayList;

public abstract class Ingredient extends Sprite {
    public float prepareTime;
    public float cookTime;
    private boolean amICooked;
    private boolean amIPrepared;

    public ArrayList<Texture> tex;

    public Ingredient(float prepareTime, float cookTime) {
        this.prepareTime = prepareTime;
        this.cookTime = cookTime;
        this.amICooked = false;
        this.amIPrepared = false;
        this.tex = null;
    }

    public void setPrepared(boolean isPrepared) {
        amIPrepared = true;
    }
    public boolean isPrepared() {
        return amIPrepared;
    }

    public void setCooked(boolean isCooked) {
        amICooked= true;
    }
    public boolean isCooked() {
        return amICooked;
    }

    public void create(float x, float y, SpriteBatch batch){
        Sprite sprite = new Sprite(tex.get(findCorrectSkin()));
        float adjustedX =  x - (5/MainGame.PPM);
        float adjustedY =  y - (4.95f / MainGame.PPM);
        sprite.setBounds(adjustedX,adjustedY,10/ MainGame.PPM,10/ MainGame.PPM);
        sprite.draw(batch);
    }

    private int findCorrectSkin(){
        if (isPrepared() && isCooked()){
            return 2;
        } else if (isPrepared()){
            return 1;
        } else {
            return 0;
        }
    }
}