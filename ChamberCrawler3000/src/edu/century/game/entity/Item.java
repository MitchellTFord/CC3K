package edu.century.game;

enum ItemType
{
	WEAPON, ARMOR, POTION;
}

public class Item extends Entity
{
	ItemType itemType;
	double healthMod, attackMod, defenceMod;

	public Item(Tile currentTile, ItemType itemType, double healthMod, double attackMod, double defenceMod)
	{
		super();

		this.entityType = EntityType.ITEM;

		this.currentTile = currentTile;
		this.itemType = itemType;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
	}
}