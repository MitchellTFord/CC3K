package edu.century.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.century.game.tiles.Tile;

public class Assets
{
	public static SpriteSheet cellBorders;
	public static SpriteSheet dirtSprites;
	
	public static void init()
	{
		cellBorders = new SpriteSheet(loadImage("textures/CellBorders"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		dirtSprites = new SpriteSheet(loadImage("textures/DirtSprites"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}
	
	public static BufferedImage loadImage(String path)
	{
		try
		{
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
