package edu.century.game.effect;

import edu.century.game.entity.Character;
import edu.century.game.entity.Stat;

public class WoundStat extends Effect
{
	Stat stat;
	
	public WoundStat(Character affectedCharacter, String effectName, double magnitude, int duration, Stat stat)
	{
		super(affectedCharacter, "Wound Stat", magnitude, duration);
		this.stat = stat;
		
		switch(stat)
		{
		case ATTACK:
			effectName = "Wound Attack";
			break;
		case DEFENCE:
			effectName = "Wound Defence";
			break;
		case MAX_HEALTH:
			effectName = "Wound Max Health";
			break;
		case POTION_POWER:
			effectName = "Wound Potion Power";
			break;
		case HEALTH_ON_KILL:
			effectName = "Wound Health on Kill";
			break;
		case GOLD_ON_KILL:
			effectName = "Wound Gold on Kill";
			break;
		default:
			//Shouldn't ever happen
			break;
		}
	}
	
	public void applyStatChange()
	{
		affectedCharacter.modStat(-magnitude, stat);
	}
	
	@Override
	public void applyEffect()
	{
		
	}
}