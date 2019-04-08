package edu.century.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import edu.century.game.display.Display;
import edu.century.game.entity.race.Race;
import edu.century.game.entity.race.player_races.Shade;
import edu.century.game.floor.Floor;
import edu.century.game.graphics.Assets;
import edu.century.game.state.GameState;
import edu.century.game.state.State;
import edu.century.game.entity.*;

public class Game implements Runnable
{
	//The AWT Display object the game is being displayed on
	private Display display;
	
	//The width/height in pixels of the game window
	private int width, height;
	
	//The title of the game window
	private String title;
	
	//Whether or not a D-Pad should be used
	private boolean useDPad;
	
	//Desired FPS
	public static final int fps = 60;
	
	//Maximum time in nanoseconds between updates to meet desired FPS
	private final double timePerTick = 1000000000 / fps;
	
	//Status of the game
	private boolean running = false;
	
	//The thread the game is running on
	private Thread thread;
	
	//The BufferStrategy used by the game
	private BufferStrategy bs;
	
	//The AWT Graphics objects used by the game
	private Graphics g;
	
	//The current state
	private State state;
	
	//Temp
	public Floor testFloor;
	public Creature testPlayer;
	
	/**
	 * Game constructor
	 * @param title the title of the game window
	 * @param width the width of the game window
	 * @param height the height of the game window
	 */
	public Game(String title, int width, int height, boolean useDPad)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		this.useDPad = useDPad;
	}
	
	/**
	 * Called when the game thread is opened
	 */
	public void run()
	{
		System.out.println("Thread Created");
		
		init();
		
		double delta = 0;
		
		//Current system time
		long now;
		
		//Time of last update
		long lastTime = System.nanoTime();
		
		//For FPS counter
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render();
				ticks++;
				delta--;
			}
			
			//Console FPS counter
			if(timer >= 1000000000)
			{
				//System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		//End the program
		stop();
	}
	
	public void update()
	{
		
	}
	
	/**
	 * Renders the display
	 */
	public void render()
	{
		bs = display.getFloorPanel().getCanvas().getBufferStrategy();
		if(bs == null)
		{	//Happens on launch
			display.getFloorPanel().getCanvas().createBufferStrategy(3);
			System.out.println("Buffer Strategy Created");
			return; //Skip the rest of render();
		}
		
		//Set g to the draw object of the BufferStrategy
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		
		//Start Drawing
		
		state.render(g);
		
		//End Drawing
		
		bs.show();
		g.dispose();
	}
	
	/**
	 * Starts the game's thread
	 */
	public synchronized void start()
	{
		if(running)
		{	//Shouldn't happen
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); //Automatically calls run()
	}
	
	/**
	 * Ends the game's thread
	 */
	public synchronized void stop()
	{
		if(!running)
		{	//Shouldn't happen
			return;
		}
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the display and other objects
	 */
	private void init()
	{
		//Load image assets into memory
		Assets.init();
		
		//Temp
		testFloor = new Floor(6, 5, null);
		System.out.println("Test Floor Created");
		
		//Temp
		testPlayer = new Creature(testFloor.getCell(2, 2), new Shade());
		System.out.println("Test Player Created");
		
		//Create the display
		display = new Display(this, title, width, height, useDPad);
		System.out.println("Display Created");
		
		//Create a game state
		state = new GameState(this, testPlayer, g, testFloor);
		System.out.println("GameState Created");
	}
	
	public State getState()
	{
		return state;
	}
}
