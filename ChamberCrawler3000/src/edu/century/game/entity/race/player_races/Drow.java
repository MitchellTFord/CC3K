package edu.century.game.entity.race.player_races;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Drow extends Race
{
	public Drow()
	{
		super("Drow", Assets.tempPlayer, 25, 0, -10);
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//Potion power is increased by 50%
		return new BoostStat(character, "Drow Potion Proficiency", 0.5, -1, Stat.POTION_POWER);
	}
}