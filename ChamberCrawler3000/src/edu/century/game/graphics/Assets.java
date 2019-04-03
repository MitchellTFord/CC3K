package edu.century.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.century.game.tiles.Tile;

public class Assets
{
	public static SpriteSheet cellBorders;
	public static SpriteSheet dirtSprites;
	
	/**
	 * Loads all needed image resources into memory and assigns them to variables
	 */
	public static void init()
	{
		//cellBorders = new SpriteSheet(loadImage("/textures/CellBorders.png"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 1);
		dirtSprites = new SpriteSheet(loadImage("/textures/DirtSprites.png"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 6);
		
		System.out.println("Asset Loading Complete");
	}
	
	/**
	 * Creates a BufferedImage object from a given file path
	 * @param path the location of the image within the res directory
	 * @return the loaded BufferedImage
	 */
	public static BufferedImage loadImage(String path)
	{
		try
		{
			//Load the given image resource
			return ImageIO.read(Assets.class.getResource(path));
		} catch (Exception e)
		{
			try
			{
				//Attempt to return a special "missing texture" sprite
				return ImageIO.read(Assets.class.getResourceAsStream("/textures/MissingTexture.png"));
			}
			catch (Exception f)
			{
				// Give up and close the program
				e.printStackTrace();
				System.exit(1);
			}
		}
		return null;
	}
}
