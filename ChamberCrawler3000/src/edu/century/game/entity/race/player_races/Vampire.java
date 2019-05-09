package edu.century.game.entity.race.player_races;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import edu.century.game.effect.BoostStat;
import edu.century.game.effect.Effect;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Stat;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Vampire extends Race
{
	public Vampire()
	{
		super("Vampire", -75, 0, 0, "gains 5 HP every successful attack and has no maximum HP");
	}
	
	@Override
	public Effect getEffect(Creature creature)
	{
		creature.setHealthHardCap(-1);
		
		//Gains 5 health on kill, no maximum health
		return new BoostStat(creature, null, "Vampirism (health steal)", 5, -1, Stat.MAX_HEALTH_ON_HIT);
	}
	
	@Override
	public BufferedImage getRaceSprite() 
	{
		return Assets.raceSprites.getSprite(4, 0);
	}
	
	@Override
	public ImageIcon getRaceIcon() 
	{
		return Assets.raceSprites.getSpriteAsIcon(4, 0);
	}
}

/*
0	Shade:		125 HP, 25 Atk,	25 Def
1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
*/