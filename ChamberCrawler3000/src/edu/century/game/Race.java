package edu.century.game;

public class Race
{
	
	private String raceName;
	private double healthMod;
	private double attackMod;
	private double defenceMod;
	private EffectType raceEffectType;
	private double effectMagnitude;
	
	private Race(String raceName, double healthMod, double attackMod, double defenceMod, 
			EffectType raceEffectType, double effectMagnitude)
	{
		this.raceName = raceName;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
		this.raceEffectType = raceEffectType;
	}
	
	public String getRaceName()
	{
		return raceName;
	}
	public double getHealthMod()
	{
		return healthMod;
	}
	public double getAttackMod()
	{
		return attackMod;
	}
	public double getDefenceMod()
	{
		return defenceMod;
	}
	public EffectType getRaceEffectType()
	{
		return raceEffectType;
	}
	public double getEffectMagnitude()
	{
		return effectMagnitude;
	}

	public static Race shade = new Race("Shade", 0, 0, 0, null, 0);
	public static Race drow = new Race("Drow", 25, 0, -10, EffectType.POTION_POWER, 0);
	public static Race vampire = new Race("Vampire", -75, 0, 0, null, 0);
	public static Race troll = new Race("Troll", -5, 0, 0, null, 0);
	public static Race goblin = new Race("Goblin", -15, -5, 0, null, 0);
	  
	/*
	0	Shade:		125 HP, 25 Atk,	25 Def
	1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
	2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
	3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
	4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
	*/
}