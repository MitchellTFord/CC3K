package edu.century.game.entity;

import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;

public class Player extends Creature
{	
	public Player(Cell currentCell, Race race, String name)
	{
		super(currentCell, race);
		this.name = name;
	}
}
