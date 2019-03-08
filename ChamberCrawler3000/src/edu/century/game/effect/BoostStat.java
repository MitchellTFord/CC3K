package edu.century.game.effect;

import edu.century.game.entity.Character;

public class BoostStat extends Effect
{
	Stat stat;
	
	public BoostStat(Character affectedCharacter, String effectName, double magnitude, int duration, Stat stat)
	{
		super(affectedCharacter, "Boost Stat", magnitude, duration);
		this.stat = stat;
		
		switch(stat)
		{
		case ATTACK:
			effectName = "Boost Attack";
			break;
		case DEFENCE:
			effectName = "Boost Defence";
			break;
		case MAXHEALTH:
			effectName = "Boost Max Health";
			break;
		case POTION_POWER:
			effectName = "Boost Potion Power";
			break;
		default:
			//Shouldn't ever happen
			break;
		}
	}
	
	public void applyStatChange()
	{
		switch(stat)
		{
		case ATTACK:
			affectedCharacter.modAttack(magnitude);
			break;
		case DEFENCE:
			affectedCharacter.modDefence(magnitude);
			break;
		case MAXHEALTH:
			affectedCharacter.modMaxHealth(magnitude);
			break;
		case POTION_POWER:
			affectedCharacter.modPotionPower(magnitude);
			break;
		default:
			//Shouldn't ever happen
			break;
		}
	}
	
	@Override
	public void applyEffect()
	{

	}
}