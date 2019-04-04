package edu.century.game.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	private int spriteHeight, spriteWidth, numSpritesTall, numSpritesWide;
	
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
	
	public BufferedImage getSprite(int x, int y)
	{
		return sprites[x][y];
	}

	public void makeSprites()
	{
		for(int y = 0; y < numSpritesTall; y++)
		{
			for(int x = 0; x < numSpritesWide; x++)
			{
				sprites[x][y] = crop(spriteWidth * x, spriteHeight * y, spriteWidth, spriteHeight);
			}
		}
	}
	
	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	public int getNumSpritesTall()
	{
		return numSpritesTall;
	}
	
	public int getNumSpritesWide()
	{
		return numSpritesWide;
	}
}