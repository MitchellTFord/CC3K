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

	// The texture of the colored border around this Cell
	private BufferedImage cellBorder;

	// The texture assigned to this Cell by a Tile
	private BufferedImage tileTexture;

	// The texture assigned to this Cell's foreground by a Tile
	private BufferedImage foregroundTexture = null;

	// Whether or not this cell can have an occupant, determined by tile
	private boolean occupiable;

	// The Entity occupying this cell
	private Entity occupant;

	/**
	 * 
	 * @param floor
	 *              the floor this Cell is in
	 * @param gridX
	 *              the x index of this Cell's position in floor's cells array
	 * @param gridY
	 *              the y index of this Cell's position in floor's cells array
	 * @param tile
	 *              the Tile this Cell with inherit its properties from
	 */
	public Cell(Floor floor, int gridX, int gridY, Tile tile)
	{
		this.gridX = gridX;
		this.gridY = gridY;
		this.floor = floor;
		this.occupiable = tile.isOccupiable();
		this.tileTexture = tile.getTexture();
		this.foregroundTexture = tile.getForegroundTexture();
	}

	public void render(Graphics g, int renderX, int renderY)
	{
		// TODO: prevent non-visible Cells from rendering

		// Render cellBorder if it is something other than null
		if(cellBorder != null)
		{
			g.drawImage(cellBorder, (int) renderX, (int) renderY, (int) (Tile.TILE_WIDTH * Tile.TILE_SCALE),
					(int) (Tile.TILE_HEIGHT * Tile.TILE_SCALE), null);
		}
	}

	public void renderTile(Graphics g, double offsetX, double offsetY)
	{
		// Render tile texture
		g.drawImage(tileTexture, (int) (gridX * Tile.TILE_WIDTH * Tile.TILE_SCALE + offsetX),
				(int) (gridY * Tile.TILE_HEIGHT * Tile.TILE_SCALE + offsetY), (int) (Tile.TILE_WIDTH * Tile.TILE_SCALE),
				(int) (Tile.TILE_HEIGHT * Tile.TILE_SCALE), null);
	}

	public void renderOccupant(Graphics g, double offsetX, double offsetY)
	{
		// Calls occupant's render() method if this Cell has an occupant
		if(occupant != null)
		{
			occupant.render(g, offsetX, offsetY);
		}
	}

	public void renderForeground(Graphics g, double offsetX, double offsetY)
	{
		if(foregroundTexture != null)
		{
			// Render tile foreground texture
			g.drawImage(foregroundTexture, (int) (gridX * Tile.TILE_WIDTH * Tile.TILE_SCALE + offsetX),
					(int) (gridY * Tile.TILE_HEIGHT * Tile.TILE_SCALE - ((Tile.OBSTACLE_HEIGHT_SCALE - 1) * Tile.TILE_HEIGHT * Tile.TILE_SCALE) + offsetY),
					(int) (Tile.TILE_WIDTH * Tile.TILE_SCALE),
					(int) (Tile.TILE_HEIGHT * Tile.TILE_SCALE * Tile.OBSTACLE_HEIGHT_SCALE), null);
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
	 *                 the Entity to make this Cell's occupant
	 */
	public boolean setOccupant(Entity newOccupant)
	{
		// This function shouldn't be called if this Cell already has an
		// occupant, but it checks to make sure it doesn't have an occupant
		// anyway
		if(newOccupant != null)
		{
			if(this.occupant == null && this.occupiable)
			{
				this.occupant = newOccupant;

				// Tells the new occupant what cell it now belongs to
				newOccupant.setCurrentCell(this);

				return true;
			}
			return false;
		} else
		{
			this.occupant = null;
			return true;
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
		return (this.occupant == null && this.occupiable);
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
	 *                  the cell with which to test adjacency
	 * @return true if this Cell is adjacent to the passed in Cell otherCell
	 */
	public boolean isAdjecentWithCorners(Cell otherCell)
	{
		return Math.abs(gridX - otherCell.getGridX()) <= 1 && Math.abs(gridY - otherCell.getGridY()) <= 1;
	}
	
	/**
	 * Checks whether this Cell is adjacent to the passed in cell
	 * 
	 * @param otherCell
	 *                  the cell with which to test adjacency
	 * @return true if this Cell is adjacent to the passed in Cell otherCell
	 */
	public boolean isAdjecent(Cell otherCell)
	{
		return (Math.abs(gridX - otherCell.getGridX()) == 1 && Math.abs(gridY - otherCell.getGridY()) == 0)
				|| (Math.abs(gridX - otherCell.getGridX()) == 0 && Math.abs(gridY - otherCell.getGridY()) == 1);
	}
	
	/**
	 * @return The up-to-four Cells adjacent to this Cell
	 */
	public Cell[] getNeighbors()
	{
		Cell[] neighbors = new Cell[4];
		if(gridX - 1 >= 0)
		{
			neighbors[0] = floor.getCell(gridX - 1, gridY);
		}
		if(gridX + 1 < floor.getGridWidth())
		{
			neighbors[1] = floor.getCell(gridX + 1, gridY);
		}
		if(gridY - 1 >= 0)
		{
			neighbors[2] = floor.getCell(gridX, gridY - 1);
		}
		if(gridY + 1 < floor.getGridWidth())
		{
			neighbors[3] = floor.getCell(gridX, gridY + 1);
		}
		return neighbors;
	}

	public boolean hasForegroundTexture()
	{
		return foregroundTexture != null;
	}
}