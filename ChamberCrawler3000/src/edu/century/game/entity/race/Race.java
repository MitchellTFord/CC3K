package edu.century.game.race;

import edu.century.game.effect.*;
import edu.century.game.entity.Character;

public abstract class Race
{
	protected String raceName;
	protected double healthMod;
	protected double attackMod;
	protected double defenceMod;
	protected Effect raceEffect;
	
	public abstract String getRaceName();
	
	//To be called for stat updates
	public abstract double getHealthMod();
	public abstract double getAttackMod();
	public abstract double getDefenceMod();

	//To be called at character creation
	public abstract Effect getEffect(Character character);
	
	/*
	Races before being redone as seperate classes
	public static Race shade = new Race("Shade", 0, 0, 0, null, 0);
	public static Race drow = new Race("Drow", 25, 0, -10, EffectType.POTION_POWER, 0.5);
	public static Race vampire = new Race("Vampire", -75, 0, 0, EffectType.VAMPIRE_RACIAL, 0);
	public static Race troll = new Race("Troll", -5, 0, 0, EffectType.REGENERATION, 5);
	public static Race goblin = new Race("Goblin", -15, -5, 0, EffectType.GOLD_ON_KILL, 5);
	*/
	
	/*
	0	Shade:		125 HP, 25 Atk,	25 Def
	1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
	2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
	3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
	4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
	*/
}