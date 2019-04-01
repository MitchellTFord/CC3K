package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class DirtTile extends Tile
{
	int randX, randY;
	
	public DirtTile(int id)
	{
		super(id);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		randX = (int) Math.round(Assets.dirtSprites.getColumns() * Math.random());
		randY = (int) Math.round(Assets.dirtSprites.getRows() * Math.random());
		
		return Assets.dirtSprites.getSprite(randX, randY);
	}
}
