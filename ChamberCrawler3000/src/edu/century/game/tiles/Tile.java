package edu.century.game.tiles;

import java.awt.image.BufferedImage;

public class Tile
{
	public static final int TILE_WIDTH = 32,
							TILE_HEIGHT = 32;
	
	protected BufferedImage texture;
	protected int id;
	protected boolean occupiable;
	
	public static Tile[] tileIDs = new Tile[256];
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tileIDs[id] = this;
	}

	public BufferedImage getTexture()
	{
		return texture;
	}

	public boolean isOccupiable()
	{
		return occupiable;
	}
}
