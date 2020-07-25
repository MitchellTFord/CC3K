package com.mitchelltford.game.entity.race.player_races;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.mitchelltford.game.effect.*;
import com.mitchelltford.game.entity.Creature;
import com.mitchelltford.game.entity.Stat;
import com.mitchelltford.game.entity.race.Race;
import com.mitchelltford.game.graphics.Assets;

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