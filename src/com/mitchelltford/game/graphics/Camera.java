package com.mitchelltford.game.graphics;

import java.awt.Rectangle;

import com.mitchelltford.game.entity.Creature;
import com.mitchelltford.game.floor.Cell;
import com.mitchelltford.game.floor.Floor;
import com.mitchelltford.game.tiles.Tile;

/**
 * @author Mitchell Ford
 */
public class Camera
{
	// The width and height of the viewport
	private int viewWidth, viewHeight;

	// The x and y coordinates of the top left corner of the viewport
	private double offsetX, offsetY;

	private Rectangle viewPort;

	// The Creature the Camera should follow
	private Creature targetCreature;

	public Camera(int viewWidth, int viewHeight, Creature targetCreature)
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.targetCreature = targetCreature;
		
		viewPort = new Rectangle();
	}

	public Camera(int viewWidth, int viewHeight, boolean useBuffer)
	{
		this(viewWidth, viewHeight, null);
	}

	public void updateCamera()
	{
		Floor floor = targetCreature.getCurrentCell().getFloor();
		int floorPixelWidth = (int) (floor.getGridWidth() * Tile.TILE_WIDTH * Tile.TILE_SCALE);
		int floorPixelHeight = (int) (floor.getGridHeight() * Tile.TILE_HEIGHT * Tile.TILE_SCALE);

		if(targetCreature != null)
		{
			offsetX = -(targetCreature.getPosX() - (viewWidth / 2) + (Tile.TILE_WIDTH * Tile.TILE_SCALE) / 2);
			offsetY = -(targetCreature.getPosY() - (viewHeight / 2) + (Tile.TILE_HEIGHT * Tile.TILE_SCALE) / 2);

			if(offsetX > 0)
			{
				offsetX = 0;
			} else if(offsetX < -floorPixelWidth + viewWidth)
			{
				offsetX = -floorPixelWidth + viewWidth;
			}
			if(offsetY > 0)
			{
				offsetY = 0;
			} else if(offsetY < -floorPixelHeight + viewHeight)
			{
				offsetY = -floorPixelHeight + viewHeight;
			}
			
			viewPort.setBounds((int) -offsetX, (int) -offsetY, viewWidth, viewHeight);
		}
	}

	public void setViewDimensions(int width, int height)
	{
		this.viewWidth = width;
		this.viewHeight = height;
	}
	
	public boolean inViewPort(Cell cell)
	{
		Rectangle cellBounds = new Rectangle();
		cellBounds.setBounds((int) (cell.getGridX() * Tile.TILE_WIDTH * Tile.TILE_SCALE), 
				(int) (cell.getGridY() * Tile.TILE_HEIGHT * Tile.TILE_SCALE), (int) (Tile.TILE_WIDTH * Tile.TILE_SCALE),
				(int) (Tile.TILE_HEIGHT * Tile.TILE_SCALE * 1.5));
		return viewPort.intersects(cellBounds);
	}
	
	public boolean inViewPort(Rectangle rect)
	{
		return viewPort.intersects(rect);
	}
	
	public double getOffsetX()
	{
		return offsetX;
	}

	public double getOffsetY()
	{
		return offsetY;
	}

	public void setTargetCreature(Creature targetCreature)
	{
		this.targetCreature = targetCreature;
	}
}
