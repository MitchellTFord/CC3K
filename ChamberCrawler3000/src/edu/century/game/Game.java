package edu.century.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import edu.century.game.display.Display;
import edu.century.game.floor.Floor;
import edu.century.game.floor.SampleFloor;
import edu.century.game.graphics.Assets;

public class Game implements Runnable
{
	//The AWT Display object the game is being displayed on
	private Display display;
	
	//The width/height in pixels of the game window
	private int width, height;
	
	//The title of the game window
	private String title;
	
	//Desired FPS
	private final int fps = 60;
	
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
	
	//Temp
	public Floor testFloor;
	
	/**
	 * Game constructor
	 * @param title the title of the game window
	 * @param width the width of the game window
	 * @param height the height of the game window
	 */
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
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
				System.out.println("FPS: " + ticks);
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
		
		testFloor.render(g, 0, 0);
		
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
		//Create the display
		display = new Display(title, width, height);
		
		
		//Load image assets into memory
		Assets.init();
		
		//Temp
		testFloor = new Floor(32, 32, null);
		System.out.println("Test Floor created");
	}
}
