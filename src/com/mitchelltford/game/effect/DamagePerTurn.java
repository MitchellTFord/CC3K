package com.mitchelltford.game.effect;

import com.mitchelltford.game.entity.Creature;
import com.mitchelltford.game.entity.DamageType;

//This class is intended to serve as an template for creating new Effects
public class DamagePerTurn extends Effect
{
	DamageType damageType;
	
	public DamagePerTurn(Creature affectedCharacter, String effectName, double magnitude, int duration, DamageType damageType, Creature caster) 
	{
		super(affectedCharacter, null, effectName, magnitude, duration, true);
		this.damageType = damageType;
		this.caster = caster;
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
