package edu.century.game.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Display
{
	private String title;
	private int width, height;
	
	private JFrame frame;
	private FloorDisplay floorPanel;
	
	public Display(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init()
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Actually exit on close
		frame.setResizable(false); //Not resizable
		frame.setLocationRelativeTo(null); //Centered on screen
		frame.setVisible(true);
		
		frame.setLayout(new BorderLayout());
		
		floorPanel = new FloorDisplay(width, height);
		frame.add(floorPanel, BorderLayout.CENTER);
		floorPanel.setVisible(true);
		
		frame.pack();
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}
	
	public FloorDisplay getFloorPanel()
	{
		return this.floorPanel;
	}
}