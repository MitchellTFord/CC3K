package com.mitchelltford.game.tiles;

import java.awt.image.BufferedImage;

import com.mitchelltford.game.graphics.Assets;

public class StoneSpireTile extends Tile
{
	public StoneSpireTile(int id)
	{
		super(id, false);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(1, 4);
	}
	
	public BufferedImage getForegroundTexture()
	{
		return Assets.obstacleSprites.getSprite(0, 0);
	}
}