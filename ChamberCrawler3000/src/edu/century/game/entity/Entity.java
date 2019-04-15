package edu.century.game.entity;

import java.awt.Graphics;

import edu.century.game.floor.Cell;

public abstract class Entity
{
	// The Cell this Entity occupies
	protected Cell currentCell;

	/**
	 * Entity constructor
	 * @param currentCell 
	 * 			the Cell this Entity occupies, a null value indicates that 
	 * 			this Entity should be placed at the Floor's player spawn location
	 */
	public Entity(Cell currentCell)
	{
		this.currentCell = currentCell;
		
		//If currentCell is null, placement of this entity MUST be handled by the State
		if(currentCell != null)
		{
			currentCell.setOccupant(this);
		}
	}
	
	/**
	 * Derived classes must implement a render method
	 * @param g the Graphics object to render with
	 * @param x the x position to render at
	 * @param y the y position to render at
	 */
	public abstract void render(Graphics g, double x, double y);

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