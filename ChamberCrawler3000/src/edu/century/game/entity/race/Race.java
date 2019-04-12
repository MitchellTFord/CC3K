package edu.century.game.entity.race;

import java.awt.image.BufferedImage;

import edu.century.game.effect.Effect;
import edu.century.game.entity.Creature;
import edu.century.game.graphics.Assets;

public abstract class Race
{
	//The names of each of the implemented Races for use in character creation
	public static String[] playerRaceStrings =
		{"Shade", "Drow", "Goblin", "Troll", "Vampire"};
	
	//Descriptions of each of the implemented Races for use in character creation
	public static final String[] playerRaceDescriptions = 
		{"Shade: 125 HP, 25 Atk, 25 Def", 
			"Drow: 150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5", 
			"Goblin: 110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy", 
			"Troll: 120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120", 
			"Vampire: 50 HP, 25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP "};
	
	// The name of this Race
	protected String raceName;
	
	//Sprite to render creatures of this Race as
	protected BufferedImage raceSprite;

	// The changes in stats the Race causes
	protected double healthMod, attackMod, defenseMod;

	// The Effect applied to Characters of this Race
	protected Effect raceEffect;

	/**
	 * Race constructor
	 * @param raceName the name of this race
	 * @param raceSprite the sprite to render creatures of this Race with
	 * @param healthMod the amount this Race modifies a Creature's max health
	 * @param attackMod the amount this Race modifies a Creature's attack
	 * @param defenceMod the amount this Race modifies a Creature's defense
	 */
	public Race(String raceName, BufferedImage raceSprite, double healthMod, double attackMod, double defenceMod)
	{
		this.raceName = raceName;
		this.raceSprite = raceSprite;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenseMod = defenceMod;
	}
	
	/**
	 * @return this Race's name
	 */
	public String getRaceName()
	{
		return raceName;
	}

	/**
	 * @return this Race's healthMod
	 */
	public double getHealthMod()
	{
		return healthMod;
	}

	/**
	 * @return this Race's attackMod
	 */
	public double getAttackMod()
	{
		return attackMod;
	}

	/**
	 * @return this Race's defenceMod
	 */
	public double getDefenseMod()
	{
		return defenseMod;
	}

	public BufferedImage getRaceSprite()
	{
		return raceSprite;
	}
	
	/**
	 * @param character
	 *            the Character this Effect is being applied to
	 * @return a new Effect, implemented differently for each Race, character is
	 *         the character effected
	 */
	public abstract Effect getEffect(Creature character);

	/*
	 * Races before being redone as separate classes 
	 * public static Race shade = new Race("Shade", 0, 0, 0, null, 0); public static Race drow = new
	 * Race("Drow", 25, 0, -10, EffectType.POTION_POWER, 0.5); public static
	 * Race vampire = new Race("Vampire", -75, 0, 0, EffectType.VAMPIRE_RACIAL,
	 * 0); public static Race troll = new Race("Troll", -5, 0, 0,
	 * EffectType.REGENERATION, 5); public static Race goblin = new
	 * Race("Goblin", -15, -5, 0, EffectType.GOLD_ON_KILL, 5);
	 */

	/*
	 * Races as defined in the CC3K document 
	 * 
	 * 0 Shade: 125 HP, 25 Atk, 25 Def 
	 * 
	 * 1 Drow: 150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
	 * 
	 * 2 Vampire: 50 HP, 25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP 
	 * 
	 * 3 Troll: 120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 
	 * 
	 * 4 Goblin: 110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
	 */
}