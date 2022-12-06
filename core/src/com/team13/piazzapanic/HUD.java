package com.team13.piazzapanic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;

public class HUD {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label scoreLabelT;
    Label scoreLabel;
    Label timeLabelT;
    Label timeLabel;

    public HUD(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //Table table = new Table();
        //table.top();
        //table.setFillParent(true);

        //timeLabel = new Label(String.format("%06d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //timeLabelT = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //scoreLabelT = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        //table.add(timeLabelT).expandX().padTop(10);
        //table.add(scoreLabelT).expandX().padTop(10);
        //table.row();
        //table.add(timeLabel).expandX().padTop(10);
        //table.add(scoreLabel).expandX().padTop(10);


        //stage.addActor(table);
    }

}
