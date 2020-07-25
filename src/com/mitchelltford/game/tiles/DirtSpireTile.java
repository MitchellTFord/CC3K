package com.mitchelltford.game.tiles;

import java.awt.image.BufferedImage;

import com.mitchelltford.game.graphics.Assets;

public class DirtSpireTile extends Tile
{
	public DirtSpireTile(int id)
	{
		super(id, false);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(1, 5);
	}
	
	public BufferedImage getForegroundTexture()
	{
		return Assets.obstacleSprites.getSprite(0, 2);
	}
}