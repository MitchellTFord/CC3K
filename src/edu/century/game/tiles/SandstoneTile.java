package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class SandstoneTile extends Tile
{
	public SandstoneTile(int id)
	{
		super(id, true);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(2, 5);
	}
}