package com.mitchelltford.game.tiles;

import java.awt.image.BufferedImage;

import com.mitchelltford.game.graphics.Assets;

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