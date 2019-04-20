package edu.century.game.entity.race.enemy_races;

import java.awt.image.BufferedImage;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Elf extends Race
{
	public Elf()
	{
		super("Elf", 15, 5, -15);
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//TODO: double attack effect
		return null;
	}
	
	@Override
	public BufferedImage getRaceSprite() 
	{
		return Assets.missingSprite;
	}
}