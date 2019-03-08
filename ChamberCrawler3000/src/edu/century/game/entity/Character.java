package edu.century.game.entity;

import edu.century.game.Tile;
import edu.century.game.effect.Effect;
import edu.century.game.race.*;
import edu.century.game.race.Races.*;


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
		this.entityType = EntityType.CHARACTER;

		this.currentTile = currentTile;
		this.race = new Shade();

		this.health = this.maxHealth = 125 + this.race.getHealthMod();
		this.attack = 25 + this.race.getAttackMod();
		this.defence = 25 + this.race.getDefenceMod();
		
		this.addEffect(race.getEffect(this));
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
	
	public void startTurn()
	{
		this.updateStats();
		this.applyEffects();
	}
	
	public void endTurn()
	{
		this.decrementEffectDurations(this.effects);
		
		//Indicate to controller object that this character's turn is over
	}
	
	public static void damage(Character caster, Character target, DamageType damageType, double damage)
	{
		switch(damageType)
		{
		case PHYSICAL:
			// Damage(defender) =
			// ceiling((100/(100+Def(defender)))*Atk(Attacker))
			target.takeDamage(Math.ceil((100 / (100 + target.getDefence())) * damage), caster);
			break;
		case ELEMENTAL:
			target.takeDamage(damage, caster);
			// Elemental damage goes through armor for now
			break;
		}
	}

	public void addEffect(Effect effect)
	{
		if(effect != null)
		{
			for(int i = 0; i < effects.length; i++)
			{
				if (effects[i] == null)
				{
					effects[i] = effect;
					return;
				}
			}
			// TODO: handle the case where effects[] is full
		}
	}

	public void takeDamage(double amount, Character damager)
	{
		this.health -= amount;
		
		// Check if dead, handle it
		if(health <= 0)
		{
			
		}
	}

	public void takeHeal(double amount)
	{
		this.health = Math.min(this.health + amount, this.maxHealth);
	}

	public void updateStats()
	{
		potionPower = 1;
		maxHealth = 100 + race.getHealthMod();
		attack = race.getAttackMod();
		defence = race.getDefenceMod();
		for(int i = 0; i < effects.length; i++)
		{
			if (effects[i] != null)
			{
				effects[i].applyStatChange();
			}
		}
	}
	
	//Called at the start of the turn
	public void applyEffects()
	{
		for(int i = 0; i < effects.length; i++)
		{
			if (effects[i] != null)
			{
				effects[i].applyEffect();
			}
		}
	}
	
	public void targetKilled(Character target)
	{
		//On-kill effects are triggered here
	}

	public double getAttack()
	{
		return attack;
	}

	public double getDefence()
	{
		return defence;
	}
	
	public void modMaxHealth(double amount)
	{
		potionPower += amount;
	}
	
	public void modAttack(double amount)
	{
		attack += amount;
	}
	
	public void modDefence(double amount)
	{
		defence += amount;
	}
	
	public void modPotionPower(double amount)
	{
		potionPower += amount;
	}
	
	//Called at the end of this character's turn
	public void decrementEffectDurations(Effect[] effects)
	{
		for(int i = 0; i < effects.length; i++)
		{
			if (effects[i].getDuration() == 0)
			{
				//TODO: Destroy Effect object

				// Remove object from effects array
				effects[i] = null;
			}
		}
	}
}