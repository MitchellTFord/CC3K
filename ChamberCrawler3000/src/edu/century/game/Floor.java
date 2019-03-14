package edu.century.game;

import edu.century.game.tiles.Tile;

public class Floor
{
	protected int gridWidth, gridHeight;
	protected Cell[][] cells;
	
	public Floor(int gridWidth, int gridHeight, Tile[][] tiles)
	{
		cells = new Cell[gridWidth][gridHeight];
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{	
				cells[gridX][gridY] = new Cell(gridX, gridY, tiles[gridX][gridY]);
			}
		}
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{	
				cells[gridX][gridY].render();
			}
		}
	}
	
	public Cell getCell(int gridX, int gridY)
	{
		if(gridX >= 0 && gridX < this.gridWidth && gridY >= 0 && gridY < this.gridHeight)
		{
			return cells[gridX][gridY];
		}
		else
		{
			return null;
		}
	}
	
	
}