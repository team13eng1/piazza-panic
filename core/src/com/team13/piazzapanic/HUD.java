package com.team13.piazzapanic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HUD implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private String worldTimer;
    private Float fontX;
    private Float fontY;
    private BitmapFont font;

    Label timeLabelT;
    Label timeLabel;

    public HUD(SpriteBatch sb){
        worldTimer = "0:00";
        fontX = 0.8F;
        fontY = 0.5F;

        font = new BitmapFont();
        font.getData().setScale(fontX, fontY);
        viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label(String.format(worldTimer), new Label.LabelStyle(font, Color.WHITE));
        timeLabelT = new Label("TIME", new Label.LabelStyle(font, Color.WHITE));


        table.add(timeLabelT).expandX().padTop(1);
        table.row();
        table.add(timeLabel).expandX().padTop(1);


        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
