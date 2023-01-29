package Sprites;

import Ingredients.*;
import Recipe.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class PlateStation extends InteractiveTileObject {
    private final List<Ingredient> plate;
    public static Recipe burgerRecipe;
    public static Recipe saladRecipe;

    private Recipe recipeDone;


    public PlateStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
        this.plate = new ArrayList<>();
        burgerRecipe = new BurgerRecipe();
        saladRecipe = new SaladRecipe();
        this.recipeDone = null;
    }

    public void dropItem(Ingredient ing) {
        plate.add(ing);
        checkRecipeCreated();
    }

    public void checkRecipeCreated(){
        if (plate.size() == burgerRecipe.getIngredients().size()) {
            boolean burgerSame = true;
            boolean burgerIngFound;
            for (Ingredient ing : plate) {
                burgerIngFound = false;
                for (int j = 0; j < burgerRecipe.getIngredients().size(); j++) {
                    if (ing.getClass().toString().equals(burgerRecipe.getIngredients().get(j).getClass().toString())) {
                        if (ing.isCooked()) {
                            burgerIngFound = true;
                        }
                    }
                }
                if (!burgerIngFound) {
                    burgerSame = false;
                }
            }
            if (burgerSame) {
                plate.clear();
                recipeDone = burgerRecipe;
            }
        }
        if (plate.size() == saladRecipe.getIngredients().size()) {
            boolean saladSame = true;
            boolean saladIngFound;
            for (Ingredient ing : plate) {
                saladIngFound = false;
                for (int j = 0; j < saladRecipe.getIngredients().size(); j++) {
                    if (ing.getClass().toString().equals(saladRecipe.getIngredients().get(j).getClass().toString())) {
                        if (ing.isPrepared()) {
                            saladIngFound = true;
                        }
                    }
                }
                if (!saladIngFound) {
                    saladSame = false;
                }
            }
            if (saladSame) {
                plate.clear();
                recipeDone = saladRecipe;
            }
        }
    }

    public ArrayList getPlate(){
        return (ArrayList) this.plate;
    }
    public Recipe getCompletedRecipe(){
        return recipeDone;
    }
    public float getX(){
        return super.bdefNew.position.x;
    }

    public float getY(){
        return super.bdefNew.position.y;
    }

    public Object pickUpItem() {
        if (recipeDone != null){
            Recipe temp = recipeDone;
            recipeDone = null;
            return temp;
        } else {
            Ingredient item = plate.get(plate.size()-1);
            plate.remove(plate.size()-1);
            return item;
        }
    }
}

