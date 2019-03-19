package edu.century.game.entity;

import edu.century.game.Cell;

enum ItemType
{
	WEAPON, ARMOR, POTION;
}

public class Item extends Entity
{
	ItemType itemType;
	double healthMod, attackMod, defenceMod;

	public Item(Cell currentCell, ItemType itemType, double healthMod, double attackMod, double defenceMod)
	{
		super(currentCell);

		this.itemType = itemType;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
}