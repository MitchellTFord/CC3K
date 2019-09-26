package edu.century.game.display;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.century.game.Game;
import edu.century.game.graphics.Assets;

/**
 * A 3x3 Directional Pad
 * @author Mitchell Ford
 */
public class DirectionalPad extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;
	
	private GridLayout layout;
	
	public DirectionalPad(Game game, int width)
	{
		super();
		
		this.game = game;
		
		//3x3 grid
		layout = new GridLayout(3, 3);
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		setPreferredSize(new Dimension(width, width));
		setMinimumSize(new Dimension(width, width));
		setMaximumSize(new Dimension(width, width));
		
		//Top row
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(0, 0), -1, -1, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(1, 0), 0, -1, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(2, 0), 1, -1, this));
		
		//Middle row
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(0, 1), -1, 0, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(1, 1), 0, 0, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(2, 1), 1, 0, this));
		
		//Bottom row
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(0, 2), -1, 1, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(1, 2), 0, 1, this));
		add(new MovementButton(Assets.dPadButtons.getSpriteAsIcon(2, 2), 1, 1, this));	
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private DirectionalPad dPad;
		private int xMove, yMove;
		
		/**
		 * Constructor for MovementButton
		 * @param text the text that should be displayed on the button
		 * @param xMove the x position of this button on the DPad
		 * @param yMove the y position of this button on the DPad
		 * @param dPad the DPad that this button is a part of
		 */
		private MovementButton(ImageIcon image, int xMove, int yMove, DirectionalPad dPad)
		{
			//Invokes JButton constructor
			super(image);
			
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
