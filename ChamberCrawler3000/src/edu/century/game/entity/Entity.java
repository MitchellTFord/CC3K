package edu.century.game.entity;

import java.awt.Graphics;

import edu.century.game.floor.Cell;

public abstract class Entity
{
	// The Cell this Entity occupies
	protected Cell currentCell;

	public Entity(Cell currentCell)
	{
		this.currentCell = currentCell;
		if(currentCell != null)
		{
			currentCell.setOccupant(this);
		}
	}
	
	public abstract void render(Graphics g, int offsetX, int offsetY);

	/**
	 * @return this Entity's currentCell
	 */
	public Cell getCurrentCell()
	{
		return this.currentCell;
	}

	/**
	 * Sets this Entity's currentCell, should only be called by Cells
	 * 
	 * @param currentCell
	 *            the Cell to make this the occupant of
	 */
	public void setCurrentCell(Cell currentCell)
	{
		this.currentCell = currentCell;
	}

	/**
	 *  @return the x index of this Entity's Cell's position in floor's cells array
	 */
	public int getGridX()
	{
		return this.currentCell.getGridX();
	}

	/**
	 *  @return the y index of this Entity's Cell's position in floor's cells array
	 */
	public int getGridY()
	{
		return this.currentCell.getGridY();
	}
}