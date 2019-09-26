package edu.century.game.entity.race.enemy_races;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import edu.century.game.effect.*;
import edu.century.game.entity.Creature;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Dragon extends Race
{
	public Dragon()
	{
		super("Dragon", 25, -5, 5, null);
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		return null;
	}
	
	@Override
	public BufferedImage getRaceSprite() 
	{
		//return bootlegSpyroTheDragon
		return Assets.raceSprites.getSprite(4, 1);
	}
	
	@Override
	public ImageIcon getRaceIcon() 
	{
		return Assets.raceSprites.getSpriteAsIcon(4, 1);
	}
}