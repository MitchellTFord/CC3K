package edu.century.game.floor;

import edu.century.game.tiles.Tile;

public class SampleFloor extends Floor 
{
	public static final Tile[][] sampleTiles = 
		{
			{Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
		};
	
	public SampleFloor()
	{
		super(4, 4, sampleTiles);
	}
}
