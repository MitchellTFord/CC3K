package edu.century.game.effect;

import edu.century.game.entity.Creature;

public abstract class Effect
{
	//The character that this character acts on
	protected Creature affectedCreature;
	
	//The Character that applied this Effect
	protected Creature caster;
	
	//The name of this Effect
	protected String effectName;
	
	//The magnitude of this Effect
	protected double magnitude;
	
	//The duration of this Effect in turns
	protected int duration;
	
	//Boolean for whether this Effect is permanent or not
	protected boolean isPermanent;

	//Boolean for whether this Effect does something other than modify stats
	protected boolean hasNonStatEffect;
	
	//The amount this Effect will modify various stats
	protected double attackMod, defenceMod, healthMod, potionPowerMod;
	
	/**
	 * Effect constructor
	 * @param affectedCreature the character to apply this effect to
	 * @param caster the creature that casted this effect, if applicable
	 * @param effectName the name of this effect
	 * @param magnitude the magnitude of this effect
	 * @param duration the number of turns this effect should last
	 * @param hasNonStatEffect indicates that this effect does something other than modify base stats
	 */
	public Effect(Creature affectedCreature, Creature caster, String effectName, double magnitude, int duration, boolean hasNonStatEffect)
	{
		this.affectedCreature = affectedCreature;
		this.effectName = effectName;
		this.magnitude = magnitude;
		this.hasNonStatEffect = hasNonStatEffect;
		this.duration = duration;
		
		//A duration of -1 signifies that an effect is permanent
		isPermanent = duration == -1;
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

	@Override
	public String toString()
	{
		String str = effectName + ":\n";
		str	+= "    Magnitude: " + magnitude +  "\n";
		if(isPermanent)
		{
			str += "    Duration: permanent\n";
		}
		else
		{
			str += "    Duration: " + duration + " turns";
		}	
		if(caster != null)
		{
			str += "    Caster: " + caster.getName();
		}
		return str;
	}
	
	
}