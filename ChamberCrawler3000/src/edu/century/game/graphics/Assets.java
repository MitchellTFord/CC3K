package edu.century.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.century.game.tiles.Tile;

public class Assets
{
	public static SpriteSheet cellBorders;
	public static SpriteSheet dirtSprites;
	public static SpriteSheet tileSprites;
	public static SpriteSheet dPadButtons;
	public static SpriteSheet raceSprites;
	
	public static BufferedImage missingSprite;
	public static BufferedImage tempPlayer;
	public static BufferedImage tempEnemy;
	
	/**
	 * Loads all needed image resources into memory and assigns them to variables
	 */
	public static void init()
	{
		//cellBorders = new SpriteSheet(loadImage("/textures/CellBorders.png"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 1);
		//dirtSprites = new SpriteSheet(loadImage("/textures/DirtSprites.png"), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 6, 1);
		tileSprites = new SpriteSheet(loadImage("/textures/TileSprites.png"), 32, 32, 6, 7);
		dPadButtons = new SpriteSheet(loadImage("/textures/DPadButtons.png"), 9, 9, 3, 3);
		raceSprites = new SpriteSheet(loadImage("/textures/RaceCreatureSprites.png"), 32, 32, 1, 7);
		
		missingSprite = loadImage("/textures/MissingTexture.png");
		tempPlayer = loadImage("/textures/TempPlayer.png");
		tempEnemy = loadImage("/textures/AngryFace.png");
		
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
				return ImageIO.read(Assets.class.getResource("/textures/MissingTexture.png"));
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
