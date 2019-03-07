package edu.century.game.race;

import edu.century.game.effect.Effect;
import edu.century.game.entity.Character;

public class Drow extends Race
{
	//public static Race drow = new Race("Drow", 25, 0, -10, EffectType.POTION_POWER, 0.5);
	public Drow()
	{
		raceName = "Drow";
		
		//Shade is the baseline class
		healthMod = 0;
		attackMod = 0;
		defenceMod = 0;
	}
	
	@Override
	public Effect getEffect(Character character)
	{
		//Potion power is increased by 50%
		
		//TODO: implement drow racial effect
		return null;
	}

	@Override
	public String getRaceName()
	{
		return raceName;
	}

	@Override
	public double getHealthMod()
	{
		return healthMod;
	}

	@Override
	public double getAttackMod()
	{
		return attackMod;
	}

	@Override
	public double getDefenceMod()
	{
		return defenceMod;
	}
}

/*
0	Shade:		125 HP, 25 Atk,	25 Def
1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
*/