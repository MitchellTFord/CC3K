package edu.century.game.effect;

import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;

//This class is intended to serve as an template for creating new Effects
public class WoundStat extends Effect
{
	
	Stat stat;
	
	public WoundStat(Creature affectedCharacter, Creature caster, String effectName, double magnitude, int duration, Stat stat)
	{
		super(affectedCharacter, caster, "Wound Stat", magnitude, duration, false);
		this.stat = stat;
		
		//A generic effect name will be assigned to the effect if another isn't assigned
		if(effectName == null)
		{
			switch(stat)
			{
			case ATTACK:
				effectName = "Boost Attack";
				break;
			case DEFENCE:
				effectName = "Boost Defence";
				break;
			case MAX_HEALTH:
				effectName = "Boost Max Health";
				break;
			case POTION_POWER:
				effectName = "Boost Potion Power";
				break;
			case HEALTH_ON_KILL:
				effectName = "Boost Health on Kill";
				break;
			case GOLD_ON_KILL:
				effectName = "Boost Gold on Kill";
				break;
			default:
				//Shouldn't ever happen
				break;
			}
		}
	}
	
	@Override
	public void applyStatChange()
	{
		affectedCreature.modStat(-magnitude, stat);
	}
	
	@Override
	public void applyEffect()
	{
		
	}
}