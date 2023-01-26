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
    private List<Ingredient> plate;
    private Recipe burgerRecipe;
    private Recipe saladRecipe;

    private Recipe recipeDone;


    public PlateStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
        this.plate = new ArrayList<>();
        this.burgerRecipe = new BurgerRecipe();
        this.saladRecipe = new SaladRecipe();
        this.recipeDone = null;
    }

    public void dropItem(Ingredient ing) {
        plate.add(ing);
        checkRecipeCreated();
    }

    public void checkRecipeCreated(){
        if (plate.size() == burgerRecipe.getIngredients().size() || plate.size() == saladRecipe.getIngredients().size()) {
            boolean burgerSame = true;
            for (int i = 0; i < plate.size(); i++) {
                if (plate.get(i).getClass().getName().equals(burgerRecipe.getIngredients().get(i).getClass().getName())) {
                    if (plate.get(i).isPrepared() && plate.get(i).isCooked()) {
                    } else {
                        burgerSame = false;
                    }
                } else {
                    burgerSame = false;
                }
            }
            if (burgerSame) {
                plate.clear();
                recipeDone = burgerRecipe;
            } else {
                boolean saladSame = true;
                for (int i = 0; i < plate.size(); i++) {
                    Ingredient ing = plate.get(i);
                    for (int j = 0; j < saladRecipe.getIngredients().size(); j++) {
                        if (ing.getClass().toString().equals(saladRecipe.getIngredients().get(j))) {
                            if (ing.isPrepared()) {
                            } else {
                                saladSame = false;
                            }
                        }
                    }
                    if (saladSame) {
                        plate.clear();
                        recipeDone = saladRecipe;
                    }
                }
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

