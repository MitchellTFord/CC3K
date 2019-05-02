package edu.century.game.entity;

import java.awt.Graphics;

import edu.century.game.floor.Cell;

/**
 * Enumerator indicating what type of Item this object is
 * @author Mitchell Ford
 */
enum ItemType
{
	WEAPON, ARMOR, POTION;
}

/**
 * Items are non-Creature Entities that can be picked up by Creatures
 * @author Mitchell Ford
 */
public class Item extends Entity
{
	//indicating whether this item is a weapon, armor, or potion
	ItemType itemType;
	
	//The changes in stats this Item causes
	double healthMod, attackMod, defenceMod;

	/**
	 * Item constructor
	 * @param currentCell the Cell this Item occupies
	 * @param itemType the type of Item this is
	 * @param healthMod the amount a Creature's max health is modified by this item
	 * @param attackMod the amount a Creature's attack is modified by this item
	 * @param defenceMod the amount a Creature's defense is modified by this item
	 */
	public Item(Cell currentCell, ItemType itemType, double healthMod, double attackMod, double defenceMod)
	{
		//Invoke Entity's constructor
		super(currentCell);

		this.itemType = itemType;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
	}
	
	public void render(Graphics g, double offsetX, double offsetY)
	{
		
	}
	
	public int getPosX()
	{
		return 0;
	}
	
	public int getPosY()
	{
		return 0;
	}
}