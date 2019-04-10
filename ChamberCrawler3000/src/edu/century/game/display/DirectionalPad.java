package edu.century.game.display;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.century.game.Game;

/**
 * A 3x3 Directional Pad
 * @author Mitchell Ford
 */
public class DirectionalPad extends JPanel
{
	private Game game;
	
	public DirectionalPad(Game game)
	{
		super();
		
		this.game = game;
		
		//3x3 grid
		setLayout(new GridLayout(3, 3));
		
		//Top row
		add(new MovementButton("NW", -1, -1, this));
		add(new MovementButton("N", 0, -1, this));
		add(new MovementButton("NE", 1, -1, this));
		
		//Middle row
		add(new MovementButton("W", -1, 0, this));
		add(new MovementButton("*", 0, 0, this));
		add(new MovementButton("E", 1, 0, this));
		
		//Bottom row
		add(new MovementButton("SW", -1, 1, this));
		add(new MovementButton("S", 0, 1, this));
		add(new MovementButton("SE", 1, 1, this));	
	}
	
	/**
	 * Sends the data from the button-press to the game's state
	 * @param xMove the x position of the calling button
	 * @param yMove the y position of the calling button
	 */
	public void sendDPadCommand(int xMove, int yMove)
	{
		game.getState().takePlayerDPadInput(xMove, yMove);
	}
	
	/**
	 * The Buttons for a 3x3 Directional Pad
	 * @author Mitchell Ford
	 */
	private class MovementButton extends JButton implements ActionListener
	{
		private DirectionalPad dPad;
		private int xMove, yMove;
		
		/**
		 * Constructor for MovementButton
		 * @param text the text that should be displayed on the button
		 * @param xMove the x position of this button on the DPad
		 * @param yMove the y position of this button on the DPad
		 * @param dPad the DPad that this button is a part of
		 */
		private MovementButton(String text, int xMove, int yMove, DirectionalPad dPad)
		{
			//Invokes JButton constructor
			super(text);
			
			this.xMove = xMove;
			this.yMove = yMove;
			this.dPad = dPad;
			
			//Uses itself as an ActionListener
			addActionListener(this);
		}
		
		@Override
		/**
		 * Sends the button-press data to the D-Pad object
		 */
		public void actionPerformed(ActionEvent e)
		{
			dPad.sendDPadCommand(xMove, yMove);
		}
	}
}
