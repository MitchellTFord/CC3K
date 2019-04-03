package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;
import edu.century.game.graphics.SpriteSheet;

public class Tile
{
	//The width/height of Tiles
	public static final int TILE_WIDTH = 16,
							TILE_HEIGHT = 16;
	
	//The ID of this Tile
	protected int id;
	
	//Whether or not Cells that inherit this Tile can have occupants
	protected boolean occupiable;
	
	//Array of Tile derived classes
	public static Tile[] tileIDs = new Tile[256];
	
	//Tile derived classes
	public static Tile dirtTile = new DirtTile(0);
	
	public Tile(int id)
	{
		this.id = id;
		
		//Add this Tile to tileIDs array
		tileIDs[id] = this;
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
