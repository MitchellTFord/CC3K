package edu.century.game.entity;

import java.awt.image.BufferedImage;

import edu.century.game.Cell;
import edu.century.game.effect.Effect;
import edu.century.game.entity.race.*;
import edu.century.game.entity.race.player_races.*;

public class Character extends Entity
{
	protected double health, maxHealth, attack, defence;

	protected int gold;
	protected Item armor, weapon;
	protected Race race;

	protected Effect[] effects = new Effect[64];
	protected double potionPower, healthOnKill;
	protected int goldOnKill;

	protected BufferedImage sprite;

	public Character(Cell currentCell, Race race)
	{
		super(currentCell);

		this.race = race;

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

	public void startTurn(/* TurnController turnController */)
	{
		this.updateStats();
		this.applyEffects();
	}

	public void endTurn()
	{
		this.decrementEffectDurations(this.effects);

		// Indicate to controller object that this character's turn is over
		// turnController.NextCharactersTurn()
	}

	public static void doDamage(Character caster, Character target, DamageType damageType, double damage)
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

	

	public void takeDamage(double amount, Character damager)
	{
		this.health -= amount;

		// Check if dead, handle it
		if (health <= 0)
		{
			// Tell the killer that they killed you
			try
			{
				// It's possible that the caster of an effect has
				// died by the time the effect kills someone
				damager.targetKilled(this);
			} catch(Exception e)
			{
				// No handling is needed
			}

			// TODO: destroy instance
			// TODO: handle player death
		}
	}

	public void takeHeal(double amount)
	{
		this.health = Math.min(this.health + amount, this.maxHealth);
	}
	
	public void addEffect(Effect effect)
	{
		if (effect != null)
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

	// To be called whenever a stat change is expected
	public void updateStats()
	{
		maxHealth = 125 + race.getHealthMod();
		attack = race.getAttackMod();
		defence = race.getDefenceMod();

		potionPower = 1;
		healthOnKill = 0;
		goldOnKill = 0;

		for(int i = 0; i < effects.length; i++)
		{
			if (effects[i] != null)
			{
				effects[i].applyStatChange();
			}
		}
	}

	// Called at the start of the turn
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
		// On-kill effects are triggered here
		
		//TODO: implement vampire/dwarf rule
		// Doesn't account for vampires being allergic to dwarves
		this.takeHeal(healthOnKill);
	}

	public double getAttack()
	{
		return attack;
	}

	public double getDefence()
	{
		return defence;
	}

	public void modStat(double amount, Stat stat)
	{
		switch(stat)
		{
		case MAX_HEALTH:
			maxHealth += amount;
			break;
		case ATTACK:
			attack += amount;
			break;
		case DEFENCE:
			defence += amount;
			break;
		case POTION_POWER:
			potionPower += amount;
			break;
		case HEALTH_ON_KILL:
			healthOnKill += amount;
			break;
		case GOLD_ON_KILL:
			goldOnKill += amount;
			break;
		default:
			// Shouldn't ever happen
			break;
		}
	}

	// Called at the end of this character's turn
	public void decrementEffectDurations(Effect[] effects)
	{
		for(int i = 0; i < effects.length; i++)
		{
			effects[i].decrementDuration();

			if (effects[i].getDuration() == 0)
			{
				// TODO: Destroy Effect object

				// Remove object from effects array
				effects[i] = null;
			}
		}
	}
}