package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * The JPanel for displaying the Floor
 * @author Mitchell Ford
 */
public class FloorDisplay extends JPanel
{
	//The width/height of the panel
	private int width, height;
	
	//The Canvas in this panel
	private Canvas canvas;
	
	/**
	 * Constructor for FloorDisplay
	 * @param width the width of the panel
	 * @param height the height of the panel
	 */
	public FloorDisplay(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		//Build the panel
		init();
	}
	
	private void init()
	{
		//Temp
		setBackground(Color.cyan);
		
		//Create the Canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);
	}
	
	/**
	 * @return this object's canvas
	 */
	public Canvas getCanvas()
	{
		return this.canvas;
	}
}
