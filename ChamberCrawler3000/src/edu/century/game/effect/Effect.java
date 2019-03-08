package edu.century.game.effect;

import edu.century.game.entity.Character;

public abstract class Effect
{
	protected Character affectedCharacter;
	protected Character caster; //Used by damaging effects
	protected String effectName;
	protected double magnitude;
	protected int duration;
	protected boolean isPermanent;
	
	protected double attackMod, defenceMod, healthMod;
	protected double potionPowerMod;
	
	public Effect(Character affectedCharacter, String effectName, double magnitude, int duration)
	{
		this.affectedCharacter = affectedCharacter;

		this.effectName = effectName;
		this.magnitude = magnitude;
		
		if(duration == -1)
		{	//A duration of -1 signifies that an effect is permanent
			isPermanent = true;
		}
	}
		
		//Character.damage(this.caster, this.affectedCharacter, DamageType.ELEMENTAL, magnitude);
	
	public abstract void applyEffect();
	public abstract void applyStatChange();
	
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

	public boolean getIsPermanent()
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
}