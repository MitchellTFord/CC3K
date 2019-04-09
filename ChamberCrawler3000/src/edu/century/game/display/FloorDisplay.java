package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FloorDisplay extends JPanel
{
	private int width, height;
	private Canvas canvas;
	
	public FloorDisplay(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		init();
	}
	
	private void init()
	{
		setBackground(Color.cyan);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);
	}
	
	public Canvas getCanvas()
	{
		return this.canvas;
	}
}
