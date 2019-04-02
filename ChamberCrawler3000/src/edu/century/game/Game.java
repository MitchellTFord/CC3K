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
	
	public void run()
	{
		init();
		
		//Desired FPS
		int fps = 60;
		
		//Maximum time in nanoseconds between updates to meet desired FPS
		double timePerTick = 1000000000 / fps;
		
		
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
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		bs = display.getFloorDisplay().getCanvas().getBufferStrategy();
		if(bs == null)
		{	//Happens on launch
			display.getFloorDisplay().getCanvas().createBufferStrategy(3);
			return; //Skip the rest of render();
		}
		
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
		display = new Display(title, width, height);
		
		Assets.init();
		
		floor = new SampleFloor();
	}
}
