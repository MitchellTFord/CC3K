package edu.century.game.entity;

import edu.century.game.Cell;

public abstract class Entity
{
	protected Cell currentCell;
	
	public abstract void update();
	public abstract void render();
	
	public Entity(Cell currentCell)
	{
		this.currentCell = currentCell;
	}
	
	public Cell getCurrentCell()
	{
		return this.currentCell;
	}
	public void setCurrentCell(Cell currentCell)
	{
		this.currentCell = currentCell;
	}
	
	public int getGridX()
	{
		return this.currentCell.getGridX();
	}
	public int getGridY()
	{
		return this.currentCell.getGridY();
	}
}