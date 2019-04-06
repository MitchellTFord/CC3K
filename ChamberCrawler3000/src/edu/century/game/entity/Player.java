package edu.century.game.entity;

import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;

public class Player extends Character
{
	public Player(Cell currentCell, Race race)
	{
		super(currentCell, race);
	}
}
