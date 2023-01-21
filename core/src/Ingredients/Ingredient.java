package Ingredients;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ingredient {
    public float prepareTime;
    public float cookTime;

    private boolean amICooked;
    private boolean amIPrepared;

    public Ingredient(float prepareTime, float cookTime) {
        this.prepareTime = prepareTime;
        this.cookTime = cookTime;
        this.amICooked = false;
        this.amIPrepared = false;
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
}