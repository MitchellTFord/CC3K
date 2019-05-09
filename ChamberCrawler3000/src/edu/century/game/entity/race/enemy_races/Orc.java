package edu.century.game.entity.race.enemy_races;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Orc extends Race
{
	public Orc()
	{
		super("Orc", 55, 5, 0, null);
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//TODO: double attack against goblins effect
		return null;
	}
	
	@Override
	public BufferedImage getRaceSprite() 
	{
		return Assets.raceSprites.getSprite(3, 1);
	}
	
	@Override
	public ImageIcon getRaceIcon() 
	{
		return Assets.raceSprites.getSpriteAsIcon(3, 1);
	}
}