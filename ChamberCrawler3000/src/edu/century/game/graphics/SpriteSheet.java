package edu.century.game.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	private int spriteHeight, spriteWidth, rows, columns;
	
	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight)
	{
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		
		rows = sheet.getHeight() / spriteHeight;
		columns = sheet.getWidth() / spriteWidth;
		BufferedImage[][] sprites = new BufferedImage[columns][rows];
	}
	
	public BufferedImage getSprite(int spriteX, int spriteY)
	{
		for(int y = 0; y < rows - 1; y++)
		{
			for(int x = 0; x < columns - 1; x++)
			{
				sprites[x][y] = sheet.getSubimage(spriteWidth *  x, spriteHeight * y, spriteWidth, spriteHeight);
			}
		}
		return sprites[spriteX][spriteY];
	}

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}
}