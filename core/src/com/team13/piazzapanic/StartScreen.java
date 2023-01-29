package com.team13.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.*;

public class StartScreen implements Screen {
    private final MainGame game;
    private final Texture backgroundImage;
    private final Sprite backgroundSprite;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public StartScreen(MainGame game) {
        this.game = game;
        backgroundImage = new Texture("startImage.png");
        backgroundSprite = new Sprite(backgroundImage);
        camera = new OrthographicCamera();
        viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, camera);
    }

    @Override
    public void show() {
        backgroundSprite.setSize(MainGame.V_WIDTH, MainGame.V_HEIGHT);
        backgroundSprite.setPosition(0, 0);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        backgroundSprite.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }
}
