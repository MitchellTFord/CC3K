package edu.century.game.entity;

import java.awt.image.BufferedImage;

import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;
import edu.century.game.graphics.Assets;

public class Enemy extends Creature
{	
	public Enemy(Cell currentCell, Race race)
	{
		super(currentCell, race);
	}
}
