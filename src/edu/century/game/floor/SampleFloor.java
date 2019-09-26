package edu.century.game.floor;

import edu.century.game.tiles.Tile;

public class SampleFloor extends Floor 
{
	public static final Tile[][] sampleTiles = 
		{
			{Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile, Tile.stoneTile, Tile.stoneTile},
			{Tile.dirtTile, Tile.stoneTile, Tile.dirtTile, Tile.dirtTile, Tile.stoneTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.stoneTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.stoneTile ,Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.dirtTile, Tile.stoneTile, Tile.dirtTile, Tile.dirtTile, Tile.dirtTile},
			{Tile.dirtTile, Tile.stoneTile, Tile.dirtTile, Tile.dirtTile, Tile.stoneTile, Tile.dirtTile}
		};
	
	public SampleFloor()
	{
		super(6, 6, 0, 0, sampleTiles);
	}
}
