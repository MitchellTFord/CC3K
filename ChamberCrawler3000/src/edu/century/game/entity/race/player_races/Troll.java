package edu.century.game.entity.race.player_races;

import edu.century.game.effect.Effect;
import edu.century.game.entity.Creature;
import edu.century.game.entity.race.Race;

public class Troll extends Race
{
	public Troll()
	{
		raceName = "Troll";
		
		healthMod = -5;
		attackMod = 0;
		defenceMod = 0;
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//Regenerates 5 health per turn
		//Maximum health is permanently capped at 120
		
		//TODO: implement troll racial effect
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