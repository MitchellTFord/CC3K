package edu.century.testfinal;

public class Race
{
	
	private String raceName;
	private double healthMod;
	private double attackMod;
	private double defenceMod;
	private Effect raceEffect;
	private double effectMagnitude;
	
	private Race(String raceName, double healthMod, double attackMod, double defenceMod, 
			Effect raceEffect, double effectMagnitude)
	{
		this.raceName = raceName;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
		this.raceEffect = raceEffect;
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
	public Effect getRaceEffect()
	{
		return raceEffect;
	}
	public double getEffectMagnitude()
	{
		return effectMagnitude;
	}

	Race shade = new Race("Shade", 0, 0, 0, null, 0);
	Race drow = new Race("Drow", 25, 0, -10, null, 0);
	Race vampire = new Race("Vampire", -75, 0, 0, null, 0);
	Race troll = new Race("Troll", -5, 0, 0, null, 0);
	Race goblin = new Race("Goblin", -10, 0, 0, null, 0);
	  
	/*
	0	Shade:		125 HP, 25 Atk,	25 Def
	1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
	2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
	3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
	4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
	*/
}