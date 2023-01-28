package com.team13.piazzapanic;

import com.badlogic.gdx.Gdx;
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

    private Integer worldTimerM;
    private Integer worldTimerS;

    private Integer addScore;
    private Integer score;
    private Integer currentTime;
    private Float fontX;
    private Float fontY;
    private BitmapFont font;

    public String timeStr;

    public Table table;

    Label timeLabelT;
    Label timeLabel;

    Label scoreLabel;
    Label scoreLabelT;
    Label orderNumL;
    Label orderNumLT;

    public HUD(SpriteBatch sb){
        worldTimerM = 0;
        worldTimerS = 0;
        score = 0;
        timeStr = String.format("%d", worldTimerM) + " : " + String.format("%d", worldTimerS);
        fontX = 0.5F;
        fontY = 0.3F;

        font = new BitmapFont();
        font.getData().setScale(fontX, fontY);
        viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        table.left().top();
        table.setFillParent(true);

        timeLabel = new Label(String.format("%d", worldTimerM, ":", "%i", worldTimerS), new Label.LabelStyle(font, Color.WHITE));
        timeLabelT = new Label("TIME", new Label.LabelStyle(font, Color.BLACK));
        orderNumLT = new Label("ORDER", new Label.LabelStyle(font, Color.BLACK));
        orderNumL = new Label(String.format("%d", 0), new Label.LabelStyle(font, Color.WHITE));

        scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(font, Color.WHITE));
        scoreLabelT = new Label("SCORE", new Label.LabelStyle(font, Color.BLACK));


        table.add(timeLabelT).padTop(2).padLeft(2);
        table.add(scoreLabelT).padTop(2).padLeft(2);
        table.add(orderNumLT).padTop(2).padLeft(2);
        table.row();
        table.add(timeLabel).padTop(2).padLeft(2);
        table.add(scoreLabel).padTop(2).padLeft(2);
        table.add(orderNumL).padTop(2).padLeft(2);

        table.left().top();
        stage.addActor(table);
    }

    public void updateTime(Boolean scenarioComplete){
        if(scenarioComplete){
            timeLabel.setColor(Color.GREEN);
            timeStr = String.format("%d", worldTimerM) + ":" + String.format("%d", worldTimerS);
            timeLabel.setText(String.format("TIME: " + timeStr + " SCORE: %d", score));
            timeLabelT.setText("SCENARIO COMPLETE");
            table.center().top();
            stage.addActor(table);
            return;
        }
        else {
            if (worldTimerS == 59) {
                worldTimerM += 1;
                worldTimerS = 0;
            } else {
                worldTimerS += 1;
            }
        }
        table.left().top();
        timeStr = String.format("%d", worldTimerM) + ":" + String.format("%d", worldTimerS);
        timeLabel.setText(timeStr);
        stage.addActor(table);

    }

    public void updateScore(Boolean scenarioComplete, Integer expectedTime){
        currentTime = (worldTimerM * 60) + worldTimerS;
        if (currentTime <= expectedTime) {
            addScore = 100;
        }
        else{
            addScore = 100 - (5 * (currentTime-expectedTime));
            if(addScore < 0){
                addScore = 0;
            }
        }
        score += addScore;

        if(scenarioComplete==Boolean.TRUE){
            scoreLabel.setColor(Color.GREEN);
            scoreLabel.setText("");
            scoreLabelT.setText("");
            scoreLabelT.remove();
            scoreLabel.remove();
            table.center().top();
            stage.addActor(table);
            return;
        }

        table.left().top();
        scoreLabel.setText(String.format("%d", score));
        stage.addActor(table);

    }

    public void updateOrder(Boolean scenarioComplete, Integer orderNum){
        if(scenarioComplete==Boolean.TRUE){
            orderNumL.remove();
            orderNumLT.remove();
            table.center().top();
            stage.addActor(table);
            return;
        }

        table.left().top();
        orderNumL.setText(String.format("%d", orderNum));
        orderNumLT.setText("ORDER");
        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
