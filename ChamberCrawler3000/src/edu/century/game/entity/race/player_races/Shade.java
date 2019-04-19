package edu.century.game.entity.race.player_races;

import java.awt.image.BufferedImage;

import edu.century.game.effect.Effect;
import edu.century.game.entity.Creature;
import edu.century.game.entity.race.Race;
import edu.century.game.graphics.Assets;

public class Shade extends Race
{
	public Shade()
	{
		//Shade is the baseline race
		super("Shade", 0, 0, 0);
	}
	
	@Override
	public Effect getEffect(Creature character)
	{
		//Shade has no racial effect
		return null;
	}

	@Override
	public String getRaceName()
	{
		return raceName;
	}
	
	@Override
	public BufferedImage getRaceSprite() 
	{
		return Assets.raceSprites.getSprite(0, 2);
	}
}

/*
0	Shade:		125 HP, 25 Atk,	25 Def
1	Drow:		150 HP, 25 Atk, 15 Def, all potions have their effect magnified by 1.5
2	Vampire:	50 HP,	25 Atk, 25 Def, gains 5 HP every successful attack and has no maximum HP
3	Troll: 		120 HP, 25 Atk, 15 Def, regains 5 HP every turn; HP is capped at 120 HP
4	Goblin:		110 HP, 15 Atk, 20 Def, steals 5 gold from every slain enemy
*/