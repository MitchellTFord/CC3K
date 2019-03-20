package edu.century.game;

import edu.century.game.tiles.Tile;

public class Floor
{
	// The number of cells wide/tall this Floor is
	protected int gridWidth, gridHeight;

	// All of the cells on this Floor, ordered [gridX][gridY]
	protected Cell[][] cells;

	// Keeps track of all the Character objects on this Floor
	protected Character[] characters;

	public Floor(int gridWidth, int gridHeight, Tile[][] tiles)
	{
		// Initializes the cells array with dimensions defined by the
		// constructor
		cells = new Cell[gridWidth][gridHeight];

		// Iterates through all of the positions in the cells array
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{
				// Creates a new Cell object in the current position
				cells[gridX][gridY] = new Cell(this, gridX, gridY, tiles[gridX][gridY]);
			}
		}

		// Initializes the characters array with a length long enough to keep
		// track of a Character object in every Cell object in cells
		characters = new Character[gridWidth * gridHeight];
	}

	public void update()
	{

	}

	public void render(int offsetX, int offsetY)
	{
		// Iterates through all of the positions in the cells array
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{
				// Calls render() for each Cell object in cells using passed in
				// offset values
				cells[gridX][gridY].render(offsetX, offsetY);
			}
		}
	}

	public Cell getCell(int gridX, int gridY)
	{
		// Checks to see if the given gridX and gridY values are within the
		// bounds of cells
		if (gridX >= 0 && gridX < this.gridWidth && gridY >= 0 && gridY < this.gridHeight)
		{
			// Return the requested Cell object
			return cells[gridX][gridY];
		} else
		{
			// Return null because the given gridX and gridY values aren't
			// within the bounds of cells
			return null;
		}
	}
}