package edu.century.game.entity.race;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

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
	
	public static final ImageIcon[] playerRaceIcons =
		{Assets.raceSprites.getSpriteAsIcon(0, 2), 
				Assets.raceSprites.getSpriteAsIcon(0, 3),
				Assets.raceSprites.getSpriteAsIcon(0, 6),
				Assets.raceSprites.getSpriteAsIcon(0, 5),
				Assets.raceSprites.getSpriteAsIcon(0, 4)};
	
	// The name of this Race
	protected String raceName;
	
	//Sprite to render creatures of this Race as
	protected BufferedImage raceSprite;

	// The changes in stats the Race causes
	protected double healthMod, attackMod, defenseMod;

	// The Effect applied to Characters of this Race
	protected Effect raceEffect;

	/**
	 * Must be implemented by Race derived classes
	 * @return the sprite to render creatures of this Race as
	 */
	public abstract BufferedImage getRaceSprite();
	
	/**
	 * Race constructor
	 * @param raceName the name of this race
	 * @param healthMod the amount this Race modifies a Creature's max health
	 * @param attackMod the amount this Race modifies a Creature's attack
	 * @param defenceMod the amount this Race modifies a Creature's defense
	 */
	public Race(String raceName, double healthMod, double attackMod, double defenceMod)
	{
		this.raceName = raceName;
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
	
	/**
	 * @param character
	 *            the Character this Effect is being applied to
	 * @return a new Effect, implemented differently for each Race, character is
	 *         the character effected
	 */
	public abstract Effect getEffect(Creature character);

	/*
	 * Player Races as defined in the CC3K document 
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
	
	/*
	 * Enemy Races as defined in the CC3K document 
	 * 
	 * 0 Human: 140 HP, 20 Atk, 20 Def, drops 2 normal gold piles
	 * 
	 * 1 Dwarf: 100 HP, 20 Atk, 30 Def, Vampires lose rather than gain 5 health on kill
	 * 
	 * 2 Elf: 140 HP, 30 Atk, 10 Def, gets 2 attacks against every race except Drow 
	 * 
	 * 3 Orc: 180 HP, 30 Atk, 25 Def, does 50% more damage to goblins
	 * 
	 * 4 Dragon: 150 HP, 20 Atk, 29 Def, only spawns next to treasure hoards
	 * 
	 * 5 Halfling: 100 HP, 15 Atk, 20 Def, 50% evasion
	 */
	
	/*
	 * ? Merchant: 30 HP, 70 Atk, 5 Def, can trade with the player
	 */
}