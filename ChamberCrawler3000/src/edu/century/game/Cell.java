package edu.century.game;

import edu.century.game.entity.Entity;

public class Tile
{
	private int gridX, gridY;
	private Entity occupant;
	
	public Tile(int gridX, int gridY)
	{
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public Entity getOccupant()
	{
		return this.occupant;
	}
	public void setOccupant(Entity occupant)
	{
		if(this.occupant == null)
		{
			this.occupant = occupant;
			occupant.setCurrentTile(this);
		}
	}
	
	public int getGridX()
	{
		return this.gridX;
	}
	public int getGridY()
	{
		return this.gridY;
	}
}