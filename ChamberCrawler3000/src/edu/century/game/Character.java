package edu.century.game;

enum DamageType
{
	PHYSICAL, ELEMENTAL;
}

public class Character extends Entity
{
	private double health, maxHealth, attack, defence;
	private Item armor, weapon;
	private Race race;
	private Tile currentTile;
	private Effect[] effects = new Effect[64];
	private double potionPower;
	
	public Character(Tile currentTile, Race race)
	{
		super();
		
		this.currentTile = currentTile;
		this.race = race;
		
		this.health = this.maxHealth = 125 + this.race.getHealthMod();
		this.attack = 25 + this.race.getAttackMod();
		this.defence = 25 + this.race.getDefenceMod();
		
		if(race.getRaceEffectType() != null)
		{
			this.effects[0] = new Effect(this, this, race.getRaceName() + " Racial", race.getRaceEffectType(), race.getEffectMagnitude(), -1);
		}	
	}
	
	public static void damage(Character caster, Character target, DamageType damageType, double damage)
	{
		switch(damageType)
		{
		case PHYSICAL:
			//Damage(defender) = ceiling((100/(100+Def(defender)))*Atk(Attacker))
			target.health -= Math.ceil((100/(100+target.defence))*damage);
			break;
		case ELEMENTAL:
			target.health -= damage;
		}
	}
	
	public void updateStats()
	{
		potionPower = 1;
		maxHealth = 100 + race.getHealthMod();
		attack = race.getAttackMod();
		defence = race.getDefenceMod();
		for(int i = 0; i < effects.length; i++)
		{
			if(effects[i] != null)
			{
				potionPower += effects[i].potionPowerMod;
				maxHealth += effects[i].healthMod;
				maxHealth += effects[i].healthMod;
				maxHealth += effects[i].healthMod;
			}
		}	
	}
}