package edu.century.game.abilities;

import edu.century.game.Cell;

public abstract class Ability
{
	protected Character target;
	protected Character caster;
	protected String abilityName;
	
	public Ability(String abilityName)
	{
		this.abilityName = abilityName;
	}
	
	public abstract boolean validTargetCell(Cell cell);
}