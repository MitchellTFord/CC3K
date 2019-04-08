package edu.century.game.entity.race.player_races;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;
import edu.century.game.entity.race.Race;

public class Drow extends Race
{
	public Drow()
	{
		raceName = "Drow";

		healthMod = 25;
		attackMod = 0;
		defenceMod = -10;
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//Potion power is increased by 50%
		return new BoostStat(character, "Drow Potion Proficiency", 0.5, -1, Stat.POTION_POWER);
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