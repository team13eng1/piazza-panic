package com.team13.piazzapanic;

import Recipe.Order;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Orders implements Disposable {
    public Stage stage;

    private Order[] orders[];

    Label timeLabelT;
    Label timeLabel;

    /**
     * Constructor for the class, initialises an area for the orders to be placed.
     *
     * @param sb The spritebatch to be able to draw to the screen.
     */
    public Orders(SpriteBatch sb){
        Viewport viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
