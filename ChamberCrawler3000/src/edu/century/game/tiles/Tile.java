package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;
import edu.century.game.graphics.SpriteSheet;

public class Tile
{
	//The width/height of Tiles
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	public static final double TILE_SCALE = 2;
	
	//The ID of this Tile
	protected int id;
	
	//Whether or not Cells that inherit this Tile can have occupants
	protected boolean occupiable;
	
	//Array of Tile derived classes
	public static Tile[] tileIDs = new Tile[256];
	
	//Number of derived Tile classes
	public static int numTiles = 0;
	
	//Tile derived classes
	public static Tile dirtTile = new DirtTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile sandTile = new SandstoneTile(2);
	
	public Tile(int id, boolean occupiable)
	{
		this.id = id;
		this.occupiable = occupiable;
		
		//Add this Tile to tileIDs array
		tileIDs[id] = this;
		
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
		return Assets.loadImage("textures/MissingTexture");
	}
	
	/**
	 * @return this Tile's occupiable boolean
	 */
	public boolean isOccupiable()
	{
		return occupiable;
	}
}
