package edu.century.game.effect;

import edu.century.game.entity.Character;

public abstract class Effect
{
	//The character that this character acts on
	protected Character affectedCharacter;
	
	//The Character that applied this Effect
	protected Character caster;
	
	//The name of this Effect
	protected String effectName;
	
	//The magnitude of this Effect
	protected double magnitude;
	
	//The duration of this Effect in turns
	protected int duration;
	
	//Boolean for whether this Effect is permanent or not
	protected boolean isPermanent;
	
	//The amount this Effect will modify various stats
	protected double attackMod, defenceMod, healthMod, potionPowerMod;
	
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
	
	/**
	 * Apply non-stat-change portions of this Effect to affectedCharacter
	 */
	public abstract void applyEffect();
	
	/**
	 * Apply stat-change portions of this Effect to affectedCharacter
	 */
	public abstract void applyStatChange();
	
	/**
	 * Decrements this Effect's duration by one
	 */
	public void decrementDuration()
	{
		//Won't decrement the duration of permanent Effects
		if(!isPermanent)
		{
			duration--;
		}
	}
	
	/**
	 * @return the remaining duration of this Effect
	 */
	public int getDuration()
	{
		return duration;
	}

	/**
	 * @return this Effect's isPermanent boolean
	 */
	public boolean getIsPermanent()
	{
		return isPermanent;
	}
	
	/**
	 * @return this Effect's potionPowerMod
	 */
	public double getPotionPowerMod()
	{
		return potionPowerMod;
	}
	
	/**
	 * @return this Effect's attackMod
	 */
	public double getAttackMod()
	{
		return attackMod;
	}

	/**
	 * @return this Effect's defenceMod
	 */
	public double getDefenceMod()
	{
		return defenceMod;
	}

	/**
	 * @return this Effect's healthMod
	 */
	public double getHealthMod()
	{
		return healthMod;
	}
}