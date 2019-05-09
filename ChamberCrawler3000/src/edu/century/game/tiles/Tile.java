package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class Tile
{
	//The width/height of Tiles
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	public static final double TILE_SCALE = 2;
	
	public static final double OBSTACLE_HEIGHT_SCALE = 1.5;
	
	//The ID of this Tile
	protected int id;
	
	//Whether or not Cells that inherit this Tile can have occupants
	protected boolean occupiable;
	
	//Array of Tile derived classes
	public static Tile[] tiles = new Tile[256];
	
	//Number of derived Tile classes
	public static int numTiles = 0;
	
	//Tile derived classes
	public static Tile dirtTile = new DirtTile(0);
	public static Tile dirtSpireTile = new DirtSpireTile(1);
	public static Tile stoneTile = new StoneTile(2);
	public static Tile stoneSpireTile = new StoneSpireTile(3);
	public static Tile sandstoneTile = new SandstoneTile(4);
	public static Tile sandstoneSpireTile = new SandstoneSpireTile(5);
	
	public Tile(int id, boolean occupiable)
	{
		this.id = id;
		this.occupiable = occupiable;
		
		//Add this Tile to tileIDs array
		tiles[id] = this;
		
		//Increment the number of Tiles
		numTiles++;
	}
	
	/**
	 * Should be overridden by Tile derived classes
	 * @return a BufferedImage to be used as this Tile's texture
	 */
	public BufferedImage getTexture()
	{
		//Use a special "missing texture" sprite if this method isn't overridden
		return Assets.missingSprite;
	}
	
	/**
	 * Should be overridden by Tile derived classes who have foreground textures
	 * @return a BufferedImage to be used as this Tile's foreground texture
	 */
	public BufferedImage getForegroundTexture()
	{
		return null;
	}
	
	/**
	 * @return this Tile's occupiable boolean
	 */
	public boolean isOccupiable()
	{
		return occupiable;
	}
}
