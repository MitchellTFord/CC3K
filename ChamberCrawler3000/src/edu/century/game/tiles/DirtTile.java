package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class DirtTile extends Tile
{
	int rand;
	
	public DirtTile(int id)
	{
		super(id);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		rand = (int) Math.round((Assets.dirtSprites.getNumSprites() - 1) * Math.random());
		
		return Assets.dirtSprites.getSprite(rand);
	}
}