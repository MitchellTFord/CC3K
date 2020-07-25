package com.mitchelltford.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mitchelltford.game.floor.Cell;
import com.mitchelltford.game.graphics.Assets;
import com.mitchelltford.game.tiles.Tile;

enum ItemIDs
{
	ARMOR1, WEAPON1;
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
	
	//The name of this Item
	String name;
	
	//The sprite representing this item
	BufferedImage itemSprite;

	/**
	 * Item constructor
	 * @param currentCell the Cell this Item occupies
	 * @param itemType the type of Item this is
	 * @param healthMod the amount a Creature's max health is modified by this item
	 * @param attackMod the amount a Creature's attack is modified by this item
	 * @param defenceMod the amount a Creature's defense is modified by this item
	 * @param name TODO
	 */
	public Item(Cell currentCell, ItemType itemType, String name, double healthMod, double attackMod, double defenceMod)
	{
		//Invoke Entity's constructor
		super(currentCell);

		this.itemType = itemType;
		this.name = name;
		this.healthMod = healthMod;
		this.attackMod = attackMod;
		this.defenceMod = defenceMod;
	}
	
	public Item(Cell currentCell, int id)
	{
		//Invoke Entity's constructor
		super(currentCell);
		
		switch(id)
		{
			case 0:
				this.itemType = ItemType.ARMOR;
				this.defenceMod = 15;
				this.itemSprite = Assets.itemSprites.getSprite(0, 0);
				this.name = "Iron Armor";
				break;
			case 1:
				this.itemType = ItemType.WEAPON;
				this.attackMod = 15;
				this.itemSprite = Assets.itemSprites.getSprite(0, 1);
				this.name = "Iron Sword";
				break;
			case 2:
				this.itemType = ItemType.POTION;
				this.itemSprite = Assets.itemSprites.getSprite(0, 2);
				this.name = "Health Potion";
				break;
			default:
				break;
			
		}
	}
	
	public void render(Graphics g, double offsetX, double offsetY)
	{
		g.drawImage(itemSprite,
				(int) (offsetX + currentCell.getGridX() * Tile.TILE_WIDTH * Tile.TILE_SCALE),
				(int) (offsetY + currentCell.getGridY() * Tile.TILE_HEIGHT * Tile.TILE_SCALE),
				(int) (Tile.TILE_WIDTH * Tile.TILE_SCALE), (int) (Tile.TILE_HEIGHT * Tile.TILE_SCALE), null);
	}
	
	public double getHealthMod()
	{
		return healthMod;
	}

	public double getAttackMod()
	{
		return attackMod;
	}

	public double getDefenceMod()
	{
		return defenceMod;
	}

	public String getName()
	{
		return name;
	}

	public ItemType getItemType()
	{
		return itemType;
	}
	
	public int getPosX()
	{
		return (int) (currentCell.getGridX() * Tile.TILE_WIDTH * Tile.TILE_SCALE);
	}
	
	public int getPosY()
	{
		return (int) (currentCell.getGridY() * Tile.TILE_HEIGHT * Tile.TILE_SCALE);
	}
}