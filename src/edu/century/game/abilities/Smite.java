package edu.century.game.abilities;

import edu.century.game.entity.Creature;
import edu.century.game.floor.Cell;

public class Smite extends Ability
{

	public Smite(String abilityName) {
		super(abilityName);
	}

	@Override
	public boolean validTargetCell(Creature caster, Cell cell) 
	{
		if(caster.getCurrentCell().isAdjecentWithCorners(cell))
		{
			return true;
		}
		
		return false;
	}

}
