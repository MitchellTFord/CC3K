package edu.century.game.display;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.century.game.entity.Player;

public class DirectionalPad extends JPanel
{
	public Player player;
	
	public DirectionalPad()
	{
		super();
		setLayout(new GridLayout(3, 3));
		
		add(new MovementButton("NW", -1, -1, this));
		add(new MovementButton("N", 0, -1, this));
		add(new MovementButton("NE", 1, -1, this));
		
		add(new MovementButton("W", -1, 0, this));
		add(new MovementButton("*", 0, 0, this));
		add(new MovementButton("E", 1, 0, this));
		
		add(new MovementButton("SW", -1, 1, this));
		add(new MovementButton("S", 0, 1, this));
		add(new MovementButton("SE", 1, 1, this));	
	}
	
	public void sendMoveCommand(int xMove, int yMove)
	{
		player.move(player.getGridX() + xMove, player.getGridY() + yMove);
	}
	
	private class MovementButton extends JButton implements ActionListener
	{
		private DirectionalPad dPad;
		private int xMove, yMove;
		
		private MovementButton(String text, int xMove, int yMove, DirectionalPad dPad)
		{
			super(text);
			this.xMove = xMove;
			this.yMove = yMove;
			this.dPad = dPad;
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			dPad.sendMoveCommand(xMove, yMove);
		}
	}
}
