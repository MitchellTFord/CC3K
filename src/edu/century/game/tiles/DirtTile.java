package edu.century.game.tiles;

import java.awt.image.BufferedImage;

import edu.century.game.graphics.Assets;

public class DirtTile extends Tile
{
	public DirtTile(int id)
	{
		super(id, true);
	}
	
	@Override
	public BufferedImage getTexture()
	{
		return Assets.tileSprites.getSprite(1, 5);
	}
}