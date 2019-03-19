package edu.century.game;

import java.awt.image.BufferedImage;

import edu.century.game.entity.Entity;
import edu.century.game.tiles.Tile;

public class Cell
{
	private int gridX, gridY;
	private BufferedImage tileTexture, tileBorder;
	private boolean occupiable;
	private Entity occupant;
	
	
	public Cell(int gridX, int gridY, Tile tile)
	{
		this.gridX = gridX;
		this.gridY = gridY;
		
		this.tileTexture = tile.getTexture();
		this.occupiable = tile.isOccupiable();
	}
	
	public Entity getOccupant()
	{
		return this.occupant;
	}
	public void setOccupant(Entity occupant)
	{
		if(this.occupant == null && this.occupiable)
		{
			this.occupant = occupant;
			occupant.setCurrentCell(this);
		}
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		//tileTexture
		//tileBorder
		//Occupant.render()
	}
	
	public boolean getSpaceOpen()
	{
		return (this.occupant == null && this.occupiable);
	}
	
	public int getGridX()
	{
		return this.gridX;
	}
	
	public int getGridY()
	{
		return this.gridY;
	}
	
	public boolean getOccupiable()
	{
		return this.occupiable;
	}
}