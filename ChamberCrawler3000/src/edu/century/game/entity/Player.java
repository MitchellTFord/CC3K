package edu.century.game.entity;

import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;
import edu.century.game.graphics.Assets;

public class Player extends Creature
{	
	public static final double PLAYER_EVASION_CHANCE = 0.30;
	
	public Player(Cell currentCell, Race race, String name)
	{
		super(currentCell, race);
		
		this.name = name;
	}
	
	@Override
	protected void die()
	{
		turnController.getGame().endGame();
	}
}
