package edu.century.game.ai;

import edu.century.game.entity.Creature;

public abstract class Behavior
{
	protected Creature creature;
	
	public abstract void takeTurn();
	
	public Behavior(Creature creature)
	{
		this.creature = creature;
	}
}
