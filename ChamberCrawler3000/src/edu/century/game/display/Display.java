package edu.century.game.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Display extends JFrame
{
	private String title;
	private int width, height;
	
	private FloorDisplay floorDisplay;
	
	public Display(String title, int width, int height)
	{
		super(title);
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init()
	{
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null); //Centered on screen
		this.setVisible(true);
		
		setLayout(new BorderLayout());
		
		add(floorDisplay, BorderLayout.CENTER);
		floorDisplay.setVisible(true);
		
		pack();
	}
	
	public FloorDisplay getFloorDisplay()
	{
		return this.floorDisplay;
	}
}