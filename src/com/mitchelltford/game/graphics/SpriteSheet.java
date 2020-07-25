package com.mitchelltford.game.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.Arrays;

import javax.swing.ImageIcon;

/**
 * SpriteSheets are collections of sprites created from a single image file
 * @author Mitchell Ford
 */
public class SpriteSheet
{
	//The full sheet to crop sprites from
	private BufferedImage sheet;
	
	//The 2d array for storing individual sprites
	private BufferedImage[][] sprites;
	
	//Data about SpriteSheets dimensions
	private int spriteHeight, spriteWidth, numSpritesTall, numSpritesWide;
	
	/**
	 * SpriteSheet constructor
	 * @param sheet the full image
	 * @param spriteWidth the width of sprites in pixels
	 * @param spriteHeight the height of sprites in pixels
	 * @param numSpritesWide the number of sprites wide the original sheet is
	 * @param numSpritesTall the number of sprites tall the original sheet is
	 */
	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight, int numSpritesWide, int numSpritesTall)
	{
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.numSpritesTall = numSpritesTall;
		this.numSpritesWide = numSpritesWide;

		sprites = new BufferedImage[numSpritesWide][numSpritesTall];
		
		makeSprites();
	}
	
	/**
	 * @param x the x index of the desired sprite in the sprites array
	 * @param y the y index of the desired sprite in the sprites array
	 * @return the sprite at the given indices of the sprites array
	 */
	public BufferedImage getSprite(int x, int y)
	{
		return sprites[x][y];
	}
	
	public ImageIcon getSpriteAsIcon(int x, int y)
	{
		return new ImageIcon(getSprite(x, y));
	}

	/**
	 * Populates the sprites array
	 */
	public void makeSprites()
	{	
		for(int y = 0; y < numSpritesTall; y++)
		{
			for(int x = 0; x < numSpritesWide; x++)
			{
				try
				{
					sprites[x][y] = crop(spriteWidth * x, spriteHeight * y, spriteWidth, spriteHeight);
				}
				catch(RasterFormatException e)
				{
					System.err.println(
							  "Attemped to crop " + spriteWidth + " x " + spriteHeight + " sub-image \n"
							+ "\t" + "at coordinates (" + (spriteWidth) + ", " + (spriteHeight) + "), "
							+ "(" + (spriteWidth * (x + 1)) + ", " + (spriteHeight * (y + 1)) + ") \n"
							+ "\t" + "but spritesheet image is " + sheet.getWidth() + " x " + sheet.getHeight() + "\n"
							);
					
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}

	/**
	 * Passes all of the arguments into sheet.getSubimage()
	 * This method shouldn't be necessary but the program will not run if you use getSubimage directly
	 * 
	 * @param x the y position of the top left corner of the cropped image in sheet
	 * @param y the x position of the top left corner of the cropped image in sheet
	 * @param width the width of the cropped image
	 * @param height the height of the cropped image
	 * @return the cropped image
	 */
	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	/**
	 * @return the number of sprites tall the original sheet is
	 */
	public int getNumSpritesTall()
	{
		return numSpritesTall;
	}
	
	/**
	 * @return the number of sprites wide the original sheet is
	 */
	public int getNumSpritesWide()
	{
		return numSpritesWide;
	}

	@Override
	public String toString() 
	{
		return "SpriteSheet [sheet=" + sheet + ", sprites=" + Arrays.toString(sprites) + ", spriteHeight="
				+ spriteHeight + ", spriteWidth=" + spriteWidth + ", numSpritesTall=" + numSpritesTall
				+ ", numSpritesWide=" + numSpritesWide + "]";
	}
}