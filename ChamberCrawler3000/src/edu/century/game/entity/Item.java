package edu.century.game.entity;

import edu.century.game.floor.Cell;

enum ItemType
{
	WEAPON, ARMOR, POTION;
}

public class Item extends Entity
{
	//indicating whether this item is a weapon, armor, or potion
	ItemType itemType;
	
	//The changes in stats this Item causes
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
	
	public void render(int xOffset, int offsetY)
	{
		
	}
}