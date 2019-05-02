package edu.century.game.effect;

import edu.century.game.entity.Creature;
import edu.century.game.entity.DamageType;

//This class is intended to serve as an template for creating new Effects
public class HealPerTurn extends Effect
{
	DamageType damageType;
	
	public HealPerTurn(Creature affectedCharacter, String effectName, double magnitude, int duration, DamageType damageType) 
	{
		super(affectedCharacter, null, effectName, magnitude, duration, true);
		this.damageType = damageType;
	}
	
	@Override
	public void applyEffect() 
	{
		affectedCreature.takeHeal(magnitude);
	}

	@Override
	public void applyStatChange() 
	{
		
	}
}
