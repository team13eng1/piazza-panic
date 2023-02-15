package io.team13.piazzapanic.tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class AssetTests {

    @Test
    public void StartImageExists(){
        assertTrue("This test will only pass if startImage.png exists", Gdx.files.internal("startImage.png").exists());
    }
    @Test
    public void TileSetExists(){
        assertTrue("This test will only pass if TileSet.png exists", Gdx.files.internal("TileSet.png").exists());
    }
    @Test
    public void KitchenTileSetTSXExists(){
        assertTrue("This test will only pass if KitchenTileSet.png exists", Gdx.files.internal("KitchenTileSet.tsx").exists());
    }
    @Test
    public void KitchenMapTMXExists(){
        assertTrue("This test will only pass if Kitchen.png exists", Gdx.files.internal("Kitchen.tmx").exists());
    }
    @Test
    public void ChefNormalExists(){
        assertTrue("This test will only pass if Chef_normal.png exists", Gdx.files.internal("Chef/Chef_normal.png").exists());
    }
    @Test
    public void ChefIdentifierExists(){
        assertTrue("This test will only pass if chefIdentifier.png exists", Gdx.files.internal("Chef/chefIdentifier.png").exists());
    }
    @Test
    public void ChefNormalBunExists(){
        assertTrue("This test will only pass if Chef_holding_buns.png exists", Gdx.files.internal("Chef/Chef_holding_buns.png").exists());
    }
    @Test
    public void ChefPreppedBunExists(){
        assertTrue("This test will only pass if Chef_holding_buns_toasted.png exists", Gdx.files.internal("Chef/Chef_holding_buns_toasted.png").exists());
    }
    @Test
    public void ChefNormalLettuceExists(){
        assertTrue("This test will only pass if Chef_holding_lettuce.png exists", Gdx.files.internal("Chef/Chef_holding_lettuce.png").exists());
    }
    @Test
    public void ChefPreppedLettuceExists(){
        assertTrue("This test will only pass if Chef_holding_chopped_lettuce.png exists", Gdx.files.internal("Chef/Chef_holding_chopped_lettuce.png").exists());
    }
    @Test
    public void ChefNormalOnionExists(){
        assertTrue("This test will only pass if Chef_holding_onion.png exists", Gdx.files.internal("Chef/Chef_holding_onion.png").exists());
    }
    @Test
    public void ChefPreppedOnionExists(){
        assertTrue("This test will only pass if Chef_holding_chopped_onion.png exists", Gdx.files.internal("Chef/Chef_holding_chopped_onion.png").exists());
    }
    @Test
    public void ChefNormalTomatoExists(){
        assertTrue("This test will only pass if Chef_holding_tomato.png exists", Gdx.files.internal("Chef/Chef_holding_tomato.png").exists());
    }
    @Test
    public void ChefPreppedTomatoExists(){
        assertTrue("This test will only pass if Chef_holding_chopped_tomato.png exists", Gdx.files.internal("Chef/Chef_holding_chopped_tomato.png").exists());
    }
    @Test
    public void ChefNormalMeatExists(){
        assertTrue("This test will only pass if Chef_holding_meat.png exists", Gdx.files.internal("Chef/Chef_holding_meat.png").exists());
    }
    @Test
    public void ChefNormalPatyExists(){
        assertTrue("This test will only pass if Chef_holding_patty.png exists", Gdx.files.internal("Chef/Chef_holding_patty.png").exists());
    }
    @Test
    public void ChefPreppedPatyExists(){
        assertTrue("This test will only pass if Chef_holding_burger.png exists", Gdx.files.internal("Chef/Chef_holding_burger.png").exists());
    }
    @Test
    public void ChefBurgerExists(){
        assertTrue("This test will only pass if Chef_holding_front.png exists", Gdx.files.internal("Chef/Chef_holding_front.png").exists());
    }
    @Test
    public void ChefSaladExists(){
        assertTrue("This test will only pass if Chef_holding_salad.png exists", Gdx.files.internal("Chef/Chef_holding_salad.png").exists());
    }
}
