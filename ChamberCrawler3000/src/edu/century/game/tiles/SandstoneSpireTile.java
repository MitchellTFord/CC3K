package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class SandstoneSpireTile extends Tile
{
	public SandstoneSpireTile(int id)
	{
		super(id, false);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(2, 5);
	}
	
	public BufferedImage getForegroundTexture()
	{
		return Assets.obstacleSprites.getSprite(0, 1);
	}
}