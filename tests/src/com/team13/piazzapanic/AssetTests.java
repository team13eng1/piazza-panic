package com.team13.piazzapanic;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
@RunWith(GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testBurgerAssetExists(){
        assertTrue("This test will only pass when Burger.png asset exists.", Gdx.files.internal("Food/Burger.png").exists());
    }
    @Test
    public void testBurgerBunsAssetExists(){
        assertTrue("This test will only pass when Burger.png asset exists.", Gdx.files.internal("Food/Burger_buns.png").exists());
    }
}
