package edu.century.game.entity.race.player_races;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Drow extends Race
{
	public Drow()
	{
		super("Drow", 25, 0, -10, "all potions have their effect magnified by 1.5");
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//Potion power is increased by 50%
		return new BoostStat(character, null, "Drow Potion Proficiency", 0.5, -1, Stat.POTION_POWER);
	}

	@Override
	public BufferedImage getRaceSprite() 
	{
		return Assets.raceSprites.getSprite(3, 0);
	}
	
	@Override
	public ImageIcon getRaceIcon() 
	{
		return Assets.raceSprites.getSpriteAsIcon(3, 0);
	}
}