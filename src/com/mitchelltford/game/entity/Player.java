package com.mitchelltford.game.entity;

import com.mitchelltford.game.entity.race.Race;
import com.mitchelltford.game.floor.Cell;

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
