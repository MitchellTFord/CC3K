package edu.century.game;

enum EffectType
{
	BURN,
	BOOST_MAX_HEALTH, BOOST_ATK, BOOST_DEF, WOUND_ATK, WOUND_DEF, WOUND_MAX_HEALTH,
	POTION_POWER;
}

public class Effect
{
	Character affectedCharacter;
	Character caster;
	String name;
	EffectType baseEffect;
	double magnitude;
	int duration;
	
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
		
		switch(baseEffect)
		{
		case BURN:
			Character.damage(this.caster, this.affectedCharacter, DamageType.ELEMENTAL, magnitude);
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
		}
	}
}