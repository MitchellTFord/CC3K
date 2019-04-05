package edu.century.game.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Display extends JFrame
{
	private int width, height;
	
	private FloorDisplay floorPanel;
	
	public Display(String title, int width, int height)
	{
		super(title);
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init()
	{
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Actually exit on close
		setResizable(false); //Not resizable
		setLocationRelativeTo(null); //Centered on screen
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		floorPanel = new FloorDisplay(width * 2/3, height);
		add(floorPanel, BorderLayout.CENTER);
		
		pack();
	}
	
	public FloorDisplay getFloorPanel()
	{
		return this.floorPanel;
	}
}