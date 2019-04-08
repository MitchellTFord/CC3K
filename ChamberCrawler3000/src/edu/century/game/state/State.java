package edu.century.game.state;

import java.awt.Graphics;

import edu.century.game.Game;

public abstract class State
{
	protected Game game;
	
	public State(Game game)
	{
		this.game = game;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);

	public abstract void takePlayerDPadInput(int xMove, int yMove);
}