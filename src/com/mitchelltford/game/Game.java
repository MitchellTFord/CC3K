package com.mitchelltford.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.mitchelltford.game.display.Display;
import com.mitchelltford.game.display.EndGameScreen;
import com.mitchelltford.game.entity.Player;
import com.mitchelltford.game.floor.Floor;
import com.mitchelltford.game.graphics.Camera;
import com.mitchelltford.game.state.GameState;
import com.mitchelltford.game.state.State;

public class Game implements Runnable
{
	// The AWT Display object the game is being displayed on
	private Display display;

	// The width/height in pixels of the game window
	private int width, height;

	// The title of the game window
	private String title;

	// Whether or not a D-Pad should be used
	private boolean useDPad;

	// Desired FPS, 30 by default
	public static int fps = 30;

	// Maximum time in nanoseconds between renders to meet desired FPS
	public static double timePerRender = 1000000000 / fps;

	// Maximum time in nanoseconds between updates to meet 30 updates per second
	public static double timePerUpdate = 1000000000 / 30;

	// Status of the game
	private boolean running = false;

	// The thread the game is running on
	private Thread thread;

	// The BufferStrategy used by the game
	private BufferStrategy bs;

	// The AWT Graphics objects used by the game
	private Graphics g;

	// The current state
	private State state;
	
	//The game Camera
	private Camera camera;

	private Player player;

	private Floor floor;

	/**
	 * Game constructor
	 * 
	 * @param title   the title of the game window
	 * @param width   the width of the game window
	 * @param height  the height of the game window
	 * @param useDPad whether of a DPad should be used
	 * @param fps	  the desired rendering framerate
	 * @param floor	  the chosen Floor
	 */
	public Game(String title, int width, int height, boolean useDPad, Player player, int fps, Floor floor)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		this.useDPad = useDPad;
		this.player = player;
		Game.fps = fps;
		Game.timePerRender = (double) 1000000000 / fps;
		this.floor = floor;
	}

	/**
	 * Called when the game thread is opened
	 */
	public void run()
	{
		System.out.println("Thread Created");

		init();

		double updateDelta = 0;
		double renderDelta = 0;

		// Current system time
		long now;

		// Time of last update
		long lastTime = System.nanoTime();

		// For FPS counter
		long timer = 0;
		int updateTicks = 0;
		int renderTicks = 0;

		while(running)
		{
			now = System.nanoTime();
			updateDelta += (now - lastTime) / timePerUpdate;
			renderDelta += (now - lastTime) / timePerRender;
			timer += now - lastTime;
			lastTime = now;

			if(updateDelta >= 1)
			{
				update();
				updateTicks++;
				updateDelta--;
			}

			if(renderDelta >= 1)
			{
				render();
				renderTicks++;
				renderDelta--;
			}

			// Console FPS counter
			if(timer >= 1000000000)
			{
				// System.out.println("Updates / Sec: " + updateTicks);
				// System.out.println("Renders / Sec: " + renderTicks);

				updateTicks = 0;
				renderTicks = 0;
				timer = 0;
			}
		}

		// End the program
		stop();
	}

	public void update()
	{
		state.update();
	}

	/**
	 * Renders the display
	 */
	public void render()
	{
		// Get the Canvas's BufferStrategy
		bs = display.getFloorPanel().getCanvas().getBufferStrategy();

		// Create new BufferStrategy if one hasn't been made, happens on launch
		if(bs == null)
		{
			display.getFloorPanel().getCanvas().createBufferStrategy(3);
			System.out.println("Buffer Strategy Created");
			return; // Skip the rest of render();
		}

		// Set g to the draw object of the BufferStrategy
		g = bs.getDrawGraphics();

		// Clear Screen
		g.clearRect(0, 0, width, height);

		// Start Drawing

		state.render(g);

		// End Drawing

		// Show what was drawn
		bs.show();

		// Get rid of Graphics object, new one is made next frame
		g.dispose();
	}

	/**
	 * Starts the game's thread
	 */
	public synchronized void start()
	{
		if(running)
		{ // Shouldn't happen
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); // Automatically calls run()
	}

	/**
	 * Ends the game's thread
	 */
	public synchronized void stop()
	{
		if(!running)
		{ // Shouldn't happen
			return;
		}
		try
		{
			thread.join();
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the display and other objects
	 */
	private void init()
	{
		// Load image assets into memory
		//Assets.init();

//		// Temp
//		testFloor = new Floor(8, 8);
//		System.out.println("Test Floor Created");

		// Create the display
		display = new Display(this, title, width, height, useDPad);
		System.out.println("Display Created");

		//Create the Camera
		camera = new Camera(display.getFloorPanel().getWidth(), display.getFloorPanel().getHeight(), true);
		
		// Create a game state
		state = new GameState(this, player, floor, camera);
		System.out.println("GameState Created");
	}
	
	public void endGame()
	{
		new EndGameScreen(player, display.getLogText());
		display.setVisible(false);
	}

	public State getState()
	{
		return state;
	}

	public Display getDisplay()
	{
		return display;
	}
}