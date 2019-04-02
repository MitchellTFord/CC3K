package edu.century.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import edu.century.game.display.Display;
import edu.century.game.floor.Floor;
import edu.century.game.floor.SampleFloor;
import edu.century.game.graphics.Assets;

public class Game implements Runnable
{
	private Display display;
	private int width, height;
	private String title;
	
	//Desired FPS
	private final int fps = 60;
	
	//Maximum time in nanoseconds between updates to meet desired FPS
	private final double timePerTick = 1000000000 / fps;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//Temp
	public Floor floor;
	
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
		
		floor.render(g, 0, 0);
		
		//End Drawing
		
		bs.show();
		g.dispose();
	}
	
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
	
	private void init()
	{
		//Create the display
		display = new Display(title, width, height);
		
		Assets.init();
		
		//Temp
		floor = new Floor(64, 64, null);
		System.out.println("Test Floor created");
	}
}
