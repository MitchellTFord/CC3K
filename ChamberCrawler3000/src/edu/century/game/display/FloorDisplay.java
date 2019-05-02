package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * The JPanel for displaying the Floor
 * @author Mitchell Ford
 */
public class FloorDisplay extends JPanel implements MouseMotionListener
{
	//The width/height of the panel
	private int width, height;

	//The Canvas in this panel
	private Canvas canvas;
	
	private BorderLayout layout;
	
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
		setBackground(Color.black);
		
		layout = new BorderLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		//Create the Canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return this object's canvas
	 */
	public Canvas getCanvas()
	{
		return this.canvas;
	}
}
