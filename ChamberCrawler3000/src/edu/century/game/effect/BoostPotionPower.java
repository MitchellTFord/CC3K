package edu.century.game.effect;

import edu.century.game.entity.Character;

public class BoostPotionPower extends Effect
{

	public BoostPotionPower(Character affectedCharacter, String effectName, double magnitude, int duration)
	{
		super(affectedCharacter, "Boost Potion Power", magnitude, duration);
	}

	@Override
	public void applyEffect(Character target)
	{
		
	}
}