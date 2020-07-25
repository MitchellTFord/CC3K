package com.mitchelltford.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets
{
	public static SpriteSheet cellBorders;

	public static SpriteSheet tileSprites;
	public static SpriteSheet obstacleSprites;
	public static SpriteSheet dPadButtons;
	public static SpriteSheet raceSprites;
	public static SpriteSheet itemSprites;

	public static BufferedImage missingSprite;
	public static BufferedImage tempPlayer;
	public static BufferedImage tempEnemy;

	public static File testFloor1;
	public static File mainFloor;

	/**
	 * Loads all needed image resources into memory and assigns them to variables
	 */
	public static void init()
	{
		tileSprites = new SpriteSheet(loadImage("/textures/TileSprites.png"), 32, 32, 6, 7);
		obstacleSprites = new SpriteSheet(loadImage("/textures/ObstacleSprites.png"), 32, 48, 1, 3);
		dPadButtons = new SpriteSheet(loadImage("/textures/DPadButtons.png"), 9, 9, 3, 3);
		raceSprites = new SpriteSheet(loadImage("/textures/RaceCreatureSprites.png"), 32, 32, 7, 2);
		itemSprites = new SpriteSheet(loadImage("/textures/Items.png"), 16, 16, 1, 3);

		missingSprite = loadImage("/textures/MissingTexture.png");

		testFloor1 = loadFile("/floors/testFloor1.floor");
		mainFloor = loadFile("/floors/mainFloor.floor");
		
		System.out.println("Asset Loading Complete");
	}

	/**
	 * Creates a BufferedImage object from a given file path
	 * 
	 * @param path the location of the image within the res directory
	 * @return the loaded BufferedImage
	 */
	private static BufferedImage loadImage(String path)
	{
		try
		{
			// Load the given image resource
			return ImageIO.read(Assets.class.getResource(path));
		} catch(IOException e)
		{
			System.err.println("Failed to load image file " + path + "\n");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/**
	 * Creates a File object from a given file path
	 * 
	 * @param path the location of the image within the res directory
	 * @return the loaded File
	 */
	private static File loadFile(String path)
	{
		try
		{
			return new File(Assets.class.getResource(path).getFile());
		}
		catch(Exception e)
		{
			System.err.println("Failed to load file " + path + "\n");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
