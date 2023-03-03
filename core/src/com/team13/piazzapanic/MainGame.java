package com.team13.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.TimeUtils;

public class MainGame extends Game {

	/**
	 * MainGame class is the central class of the game that creates and manages the two screens, PlayScreen and StartScreen.
	 *
	 * Class Members:
	 *     V_WIDTH (int): Width of the view.
	 *     V_HEIGHT (int): Height of the view.
	 *     TILE_SIZE (int): Size of the tile.
	 *     PPM (float): Pixels per meter.
	 *     batch (SpriteBatch): Instance of SpriteBatch.
	 *     isPlayScreen (bool): Flag indicating whether the PlayScreen is displayed or not.
	 *     playScreen (PlayScreen): Instance of PlayScreen.
	 *     startScreen (StartScreen): Instance of StartScreen.
	 *
	 * Methods:
	 *     __init__: Initializes the MainGame class.
	 *     create: Creates the instances of StartScreen and PlayScreen and initializes the SpriteBatch instance.
	 *     render: Renders the StartScreen or PlayScreen based on the value of isPlayScreen flag.
	 * 	   dispose: Releases resources used by the MainGame class.
	 */
	public static final int V_WIDTH = 160;
	public static final int V_HEIGHT = 160;
	public static final int TILE_SIZE = 16;

	public static final float PPM = 100;
	public SpriteBatch batch;
	public boolean isPlayScreen;
	private PlayScreen playScreen;
	private StartScreen startScreen;
	private idleScreen idleGame;
	private GameOver gameover;
	private boolean isGameOver = false;
	private boolean goToIdle = false;


	public MainGame(){
		isPlayScreen = false;
	}
	@Override
	public void create() {
		batch = new SpriteBatch();
		startScreen = new StartScreen(this);
		playScreen = new PlayScreen(this);
		gameover = new GameOver(this);
		idleGame = new idleScreen(this);


	}

	@Override
	public void render() {


		super.render();
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
			isPlayScreen = !isPlayScreen;
		}
		if (isPlayScreen) {
			setScreen(playScreen);

		} else {
			setScreen(startScreen);
		}

	if (isGameOver){
		setScreen(gameover);
	}
	if (goToIdle){
		setScreen(idleGame);
	}

	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
	public void goToGameOver(){
		System.out.println("i also  ran");
		isGameOver = true;
	}
	public void goToIdle(){
		goToIdle = true;

	}
	public void disableIdle(){
		goToIdle = false;
		playScreen.setTAimer();
	}
}