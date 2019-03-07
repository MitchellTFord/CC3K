package edu.century.game;

import edu.century.game.entity.Character;
import edu.century.game.entity.DamageType;

enum EffectType
{
	//Implemented
	BURN,
	BOOST_MAX_HEALTH, BOOST_ATK, BOOST_DEF, WOUND_ATK, WOUND_DEF, WOUND_MAX_HEALTH,
	POTION_POWER,
	
	//Not Implemented
	REGENERATION, GOLD_ON_KILL,
	VAMPIRE_RACIAL;
}

public class Effect
{
	Character affectedCharacter;
	Character caster;
	String name;
	EffectType baseEffect;
	double magnitude;
	int duration;
	boolean hasNonStatEffect, isPermanent;
	
	double potionPowerMod;
	double attackMod, defenceMod, healthMod;
	
	public Effect(Character affectedCharacter, Character caster, String name, EffectType baseEffect, double magnitude, int duration)
	{
		this.affectedCharacter = affectedCharacter;
		this.caster = caster;
		this.name = name;
		this.baseEffect = baseEffect;
		this.magnitude = magnitude;
		this.duration = duration;
		
		this.attackMod = 0;
		this.defenceMod = 0;
		this.healthMod = 0;
		this.potionPowerMod = 0;
		
		if(duration == -1)
		{	//A duration of -1 signifies that an effect is permanent
			isPermanent = true;
		}
		
		//Can be set to true in the switch below
		hasNonStatEffect = false;
		
		switch(baseEffect)
		{
		case BURN:
			hasNonStatEffect = true;
			//Character.damage(this.caster, this.affectedCharacter, DamageType.ELEMENTAL, magnitude);
			break;
		case POTION_POWER:
			potionPowerMod = magnitude;
			break;
		case BOOST_ATK:
			attackMod = magnitude;
			break;
		case BOOST_DEF:
			defenceMod = magnitude;
			break;
		case BOOST_MAX_HEALTH:
			healthMod = magnitude;
			break;
		case WOUND_ATK:
			attackMod = -magnitude;
			break;
		case WOUND_DEF:
			defenceMod = -magnitude;
			break;
		case WOUND_MAX_HEALTH:
			healthMod = -magnitude;
			break;
		
		//TODO: implement these cases
		case GOLD_ON_KILL:
			break;
		case REGENERATION:
			break;
		case VAMPIRE_RACIAL:
			break;
		default:
			break;
		}
	}
	
	public void applyEffect()
	{
		switch(baseEffect)
		{
		case BURN:
			Character.damage(this.caster, this.affectedCharacter, DamageType.ELEMENTAL, magnitude);
			break;
		}
	}
	
	public void decrementDuration()
	{
		if(!isPermanent)
		{
			duration--;
		}
	}
	
	public int getDuration()
	{
		return duration;
	}

	public boolean isPermanent()
	{
		return isPermanent;
	}

	public double getPotionPowerMod()
	{
		return potionPowerMod;
	}

	public double getAttackMod()
	{
		return attackMod;
	}

	public double getDefenceMod()
	{
		return defenceMod;
	}

	public double getHealthMod()
	{
		return healthMod;
	}
	
	public boolean getHasNonStatEffect()
	{
		return hasNonStatEffect;
	}
}