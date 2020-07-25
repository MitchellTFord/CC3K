package com.mitchelltford.game.ai;

import com.mitchelltford.game.entity.Creature;
import com.mitchelltford.game.floor.Cell;

public abstract class Behavior
{
	protected Creature creature;
	
	public abstract void takeTurn();
	
	public Behavior(Creature creature)
	{
		this.creature = creature;
	}
	
	public static int manhattanDistance(Cell cell1, Cell cell2)
	{
		return Math.abs(cell1.getGridX() - cell2.getGridX()) + Math.abs(cell1.getGridY() - cell2.getGridY());
	}
}