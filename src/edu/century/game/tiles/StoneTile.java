package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class StoneTile extends Tile
{
	public StoneTile(int id)
	{
		super(id, true);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(1, 4);
	}
}
