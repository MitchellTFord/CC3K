package edu.century.game.race;

import edu.century.game.effect.Effect;
import edu.century.game.entity.Character;

public class Goblin extends Race
{
	//public static Race goblin = new Race("Goblin", -15, -5, 0, EffectType.GOLD_ON_KILL, 5);
	public Goblin()
	{
		raceName = "Goblin";
		
		healthMod = -15;
		attackMod = -5;
		defenceMod = 0;
	}
	
	@Override
	public Effect getEffect(Character character)
	{
		//Gains 5 gold on kill
		
		//TODO: implement goblin racial effect
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
		return 0;
	}

	@Override
	public double getAttackMod()
	{
		return 0;
	}

	@Override
	public double getDefenceMod()
	{
		return 0;
	}
}

/*
0	Shade:		125 HP, 25 Atk,	25 Def
1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
*/