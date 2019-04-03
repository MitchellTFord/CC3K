package edu.century.game.floor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.century.game.entity.Entity;
import edu.century.game.tiles.Tile;

public class Cell
{
	// The Floor that this Cell is in
	private Floor floor;

	// The indices of this Cell's position on floor's cells array
	private int gridX, gridY;

	// The x and y values of the position that render() should use
	private int x, y;

	// The texture of the colored border around this Cell
	private BufferedImage cellBorder;
	
	//The texture assigned to this Cell by a Tile
	private BufferedImage tileTexture;

	// Whether or not this cell can have an occupant, determined by tile
	private boolean occupiable;

	// The Entity occupying this cell
	private Entity occupant;

	/**
	 * 
	 * @param floor
	 *            the floor this Cell is in
	 * @param gridX
	 *            the x index of this Cell's position in floor's cells array
	 * @param gridY
	 *            the y index of this Cell's position in floor's cells array
	 * @param tile
	 *            the Tile this Cell with inherit its properties from
	 */
	public Cell(Floor floor, int gridX, int gridY, Tile tile)
	{
		this.gridX = gridX;
		this.gridY = gridY;
		this.floor = floor;
		this.occupiable = tile.isOccupiable();
		this.tileTexture = tile.getTexture();
	}

	public void render(Graphics g, int offsetX, int offsetY)
	{
		//TODO: prevent non-visible Cells from rendering
		
		// Update this Cell's rendering position based on the passed in offset
		// values
		updatePos(offsetX, offsetY);

		//Render tile texture
		g.drawImage(tileTexture, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);

		//Render cellBorder if it is something other than null
		if(cellBorder != null)
		{
			g.drawImage(cellBorder, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
		}

		// Calls occupant's render() method if this Cell has an occupant
		if (occupant != null)
		{
			occupant.render(g, x, y);
		}
	}

	/**
	 * @return this Cell's occupant
	 */
	public Entity getOccupant()
	{
		return this.occupant;
	}

	/**
	 * Sets this Cell's occupant to the passed in Entity, updates the new
	 * occupant's currentCell
	 * 
	 * @param occupant
	 *            the Entity to make this Cell's occupant
	 */
	public void setOccupant(Entity occupant)
	{
		// This function shouldn't be called if this Cell already has an
		// occupant, but it checks to make sure it doesn't have an occupant
		// anyway
		if (this.occupant == null && this.occupiable)
		{
			this.occupant = occupant;

			// Tells the new occupant what cell it now belongs to
			occupant.setCurrentCell(this);
		}
	}

	/**
	 * @return the Floor this Cell is on/in
	 */
	public Floor getFloor()
	{
		return this.floor;
	}

	/**
	 * @return true if this cell doesn't have an occupant and is able to have an
	 *         occupant
	 */
	public boolean getSpaceOpen()
	{
		return(this.occupant == null && this.occupiable);
	}

	/**
	 * @return the x index of this Cell's position in floor's cells array
	 */
	public int getGridX()
	{
		return this.gridX;
	}

	/**
	 * @return the y index of this Cell's position in floor's cells array
	 */
	public int getGridY()
	{
		return this.gridY;
	}

	/**
	 * Updates the rendering position of this cell
	 * 
	 * @param offsetX
	 *            the number of pixels that cells are offset in the x direction
	 * @param offsetY
	 *            the number of pixels that cells are offset in the y direction
	 */
	public void updatePos(int offsetX, int offsetY)
	{
		x = gridX * Tile.TILE_WIDTH + offsetX;
		y = gridY * Tile.TILE_HEIGHT + offsetY;
	}

	/**
	 * @return true if this Cell can have an occupant
	 */
	public boolean getOccupiable()
	{
		return this.occupiable;
	}

	/**
	 * Checks whether this Cell is adjacent to the passed in cell
	 * 
	 * @param otherCell
	 *            the cell with which to test adjacency
	 * @return true if this Cell is adjacent to the passed in Cell otherCell
	 */
	public boolean isAdjecent(Cell otherCell)
	{
		return Math.abs(gridX - otherCell.getGridX()) <= 1 && Math.abs(gridY - otherCell.getGridY()) <= 1;
	}
}