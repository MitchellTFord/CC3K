package edu.century.game.entity.race;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;

public abstract class Race
{
	// The name of this Race
	protected String raceName;

	// The changes in stats the Race causes
	protected double healthMod, attackMod, defenceMod;

	// The Effect applied to Characters of this Race
	protected Effect raceEffect;

	/**
	 * @return this Race's name
	 */
	public abstract String getRaceName();

	/**
	 * @return this Race's healthMod
	 */
	public abstract double getHealthMod();

	/**
	 * @return this Race's attackMod
	 */
	public abstract double getAttackMod();

	/**
	 * @return this Race's defenceMod
	 */
	public abstract double getDefenceMod();

	/**
	 * @param character
	 *            the Character this Effect is being applied to
	 * @return a new Effect, implemented differently for each Race, character is
	 *         the character effected
	 */
	public abstract Effect getEffect(Creature character);

	/*
	 * Races before being redone as seperate classes public static Race shade =
	 * new Race("Shade", 0, 0, 0, null, 0); public static Race drow = new
	 * Race("Drow", 25, 0, -10, EffectType.POTION_POWER, 0.5); public static
	 * Race vampire = new Race("Vampire", -75, 0, 0, EffectType.VAMPIRE_RACIAL,
	 * 0); public static Race troll = new Race("Troll", -5, 0, 0,
	 * EffectType.REGENERATION, 5); public static Race goblin = new
	 * Race("Goblin", -15, -5, 0, EffectType.GOLD_ON_KILL, 5);
	 */

	/*
	 * Races as defined in the CC3K document 0 Shade: 125 HP, 25 Atk, 25 Def 1
	 * Drow: 150 HP, 25 Atk, 15 Def, all potions have their effect magnified by
	 * 1.5 2 Vampire: 50 HP, 25 Atk, 25 Def, gains 5 HP every successful attack
	 * and has no maximum HP 3 Troll: 120 HP, 25 Atk, 15 Def, regains 5 HP every
	 * turn; HP is capped at 120 HP 4 Goblin: 110 HP, 15 Atk, 20 Def, steals 5
	 * gold from every slain enemy
	 */
}