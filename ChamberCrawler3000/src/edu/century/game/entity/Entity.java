package edu.century.game.entity;

import edu.century.game.Tile;

enum EntityType
{
	CHARACTER, ITEM;
}

public abstract class Entity
{
	Tile currentTile;
	EntityType entityType;
	
	public Tile getCurrentTile()
	{
		return this.currentTile;
	}
	public void setCurrentTile(Tile currentTile)
	{
		this.currentTile = currentTile;
	}
	
	public int getGridX()
	{
		return this.currentTile.getGridX();
	}
	public int getGridY()
	{
		return this.currentTile.getGridY();
	}
}