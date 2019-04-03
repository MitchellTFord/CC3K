package edu.century.game.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;
	private BufferedImage[] sprites;
	private int spriteHeight, spriteWidth, numSprites;
	
	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight, int numSprites)
	{
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.numSprites = numSprites;

		sprites = new BufferedImage[numSprites];
		
		makeSprites();
	}
	
	public BufferedImage getSprite(int index)
	{
		return sprites[index];
	}

	public void makeSprites()
	{
		for(int i = 0; i < numSprites; i++)
		{
			sprites[i] = crop(spriteWidth * i, 0, spriteWidth, spriteHeight);
		}
	}
	
	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	public int getNumSprites()
	{
		return numSprites;
	}
}