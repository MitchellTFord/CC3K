package com.mitchelltford.game.state;

import java.awt.Graphics;

import com.mitchelltford.game.Game;

public abstract class State
{
	//The Game object running this program
	protected Game game;
	
	/**
	 * State constructor
	 * @param game the Game object running this program
	 */
	public State(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Derived classes must implement an update() method
	 */
	public abstract void update();
	
	/**
	 * Derived classes must implement a render() method
	 * @param g the Graphics object to render things with
	 */
	public abstract void render(Graphics g);

	/**
	 * Derived classes must implement a method that processes D-Pad input from the player
	 * @param xMove the x component of the button that created this ActionEvent
	 * @param yMove the y component of the button that created this ActionEvent
	 */
	public abstract void takePlayerDPadInput(int xMove, int yMove);
	
	public Game getGame()
	{
		return game;
	}
}