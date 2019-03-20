package edu.century.game.tiles;

import java.awt.image.BufferedImage;

public class Tile
{
	//The width/height of Tiles
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	//The sprite of this Tile
	protected BufferedImage texture;
	
	//The ID of this Tile
	protected int id;
	
	//Whether or not Cells that inherit this Tile can have occupants
	protected boolean occupiable;
	
	//Array of Tile derived classes
	public static Tile[] tileIDs = new Tile[256];
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		//Add this Tile to tileIDs
		tileIDs[id] = this;
	}
	
	public void render(int x, int y)
	{
		
	}
	
	/**
	 * @return this Tile's occupiable boolean
	 */
	public boolean isOccupiable()
	{
		return occupiable;
	}
}
