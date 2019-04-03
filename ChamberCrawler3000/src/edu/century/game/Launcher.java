package edu.century.game;

import javax.swing.SwingUtilities;

public class Launcher
{
	public static void main(String[] args)
	{
		Game game = new Game("Chamber Crawler 3000", 640, 360);
		game.start();
	}
}