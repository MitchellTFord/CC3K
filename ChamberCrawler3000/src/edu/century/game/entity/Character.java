package edu.century.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.century.game.effect.Effect;
import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;
import edu.century.game.graphics.Assets;
import edu.century.game.tiles.Tile;

public class Character extends Entity
{
	// this character's various stats
	protected double health, maxHealth, attack, defence, potionPower, healthOnKill;
	protected int goldOnKill;

	// the amount of gold held by this character
	protected int gold;

	// this Character�s equipped armor/weapon Item
	protected Item armor, weapon;

	// this Character's Race
	protected Race race;

	// the array of effects this Character is affected by
	protected Effect[] effects = new Effect[64];

	// the sprite representing this Character
	protected BufferedImage characterSprite;

	public Character(Cell currentCell, Race race)
	{
		super(currentCell);

		this.race = race;

		this.health = this.maxHealth = 125 + this.race.getHealthMod();
		this.attack = 25 + this.race.getAttackMod();
		this.defence = 25 + this.race.getDefenceMod();

		this.addEffect(race.getEffect(this));
	}

	@Override
	public void render(Graphics g, int x, int y)
	{
		//Temp
		g.drawImage(Assets.tempPlayer, (int) (x * Tile.TILE_SCALE), (int) (y * Tile.TILE_SCALE), 64, 64, null);
	}

	/**
	 * Triggered at the start of a turn, calls updateStats() and applyEffects()
	 */
	public void startTurn(/* TurnController turnController */)
	{
		this.updateStats();
		this.applyEffects();
	}

	/**
	 * Triggered at the end of a turn, decrements the duration of all current
	 * Effects, tells indicates to the appropriate controller that this
	 * Character's turn is over
	 */
	public void endTurn()
	{
		this.decrementEffectDurations(this.effects);

		// Indicate to controller object that this character's turn is over
		// turnController.NextCharactersTurn()
	}

	public void move(Cell destCell)
	{
		if (currentCell.isAdjecent(destCell) && destCell.getSpaceOpen())
		{
			Cell prevCell = currentCell;
			
			//Assign this Character to its destination Cell
			//currentCell is automatically updated by setOccupant()
			if(destCell.setOccupant(this))
			{
				//Remove this Character from its current Cell
				prevCell.setOccupant(null);
			}
		}
	}

	/**
	 * Allows calling of move() using coordinates as parameters rather than a
	 * Cell object
	 * 
	 * @param gridX the x index of the destination Cell in the floor's cells array
	 * @param gridY the y index of the destination Cell in the floor's cells array
	 */
	public void move(int gridX, int gridY)
	{
		if(gridX >= 0 && gridX < currentCell.getFloor().getGridWidth()
				&& gridY >= 0 && gridY < currentCell.getFloor().getGridHeight())
		move(currentCell.getFloor().getCell(gridX, gridY));
	}

	/**
	 * Handles the dealing of damage between characters, passes different values
	 * into takeDamage() depending on damageType when it�s called
	 * 
	 * @param caster
	 *            the Character doing the damage
	 * @param target
	 *            the Character taking the damage
	 * @param damageType
	 *            the damageType of the attack
	 * @param damage
	 *            the amount of damage before armor is taken into account
	 */
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

	/**
	 * decrements health by the amount parameter, if this kills the character,
	 * attempt to call targetKilled(damager)
	 * 
	 * @param amount
	 *            the amount of health to remove
	 * @param damager
	 *            the Character that did the damage
	 */
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

	/**
	 * Increments health by the amount parameter, up to maxHealth
	 * 
	 * @param amount
	 *            the amount of health to gain
	 */
	public void takeHeal(double amount)
	{
		this.health = Math.min(this.health + amount, this.maxHealth);
	}

	/**
	 * Adds the given effect Effect to this character�s effects array
	 * 
	 * @param effect
	 */
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

	/**
	 * update this character�s stats based on base values, racial bonuses, item
	 * bonuses, and stat-changing effects, calls effects[i].applyStatChange()
	 * for all i in effects
	 */
	public void updateStats()
	{
		// Set all stats to base values
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

	/**
	 * Calls effects[i].applyEffect() for all i in effects
	 */
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

	/**
	 * Call effects[i].decrementDuration() for all i in effects, destroy effects
	 * with duration 0
	 * 
	 * @param effects
	 */
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

	/**
	 * Apply this character�s xxxOnKill variables
	 * 
	 * @param target
	 *            the Character that was killed, needed for vampire/dwarf rule
	 */
	public void targetKilled(Character target)
	{
		// On-kill effects are triggered here

		// TODO: implement vampire/dwarf rule
		// Doesn't account for vampires being allergic to dwarves
		this.takeHeal(healthOnKill);

		this.gold += goldOnKill;
	}

	/**
	 * Increment the given Stat by the given amount
	 * 
	 * @param amount
	 *            the amount to modify the given Stat by
	 * @param stat
	 *            the Stat to be modified
	 */
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

	/**
	 * @return this Character's attack
	 */
	public double getAttack()
	{
		return attack;
	}

	/**
	 * @return this Character's defence
	 */
	public double getDefence()
	{
		return defence;
	}
}