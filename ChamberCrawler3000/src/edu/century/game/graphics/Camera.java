package edu.century.game.graphics;

import java.awt.Rectangle;

import edu.century.game.entity.Creature;
import edu.century.game.floor.Floor;
import edu.century.game.tiles.Tile;

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
	
	private boolean useBuffer;

	public Camera(int viewWidth, int viewHeight, boolean useBuffer, Creature targetCreature)
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.useBuffer = useBuffer;
		this.targetCreature = targetCreature;
	}
	
	public Camera(int viewWidth, int viewHeight, boolean useBuffer)
	{
		this(viewWidth, viewHeight, useBuffer, null);
	}

	public void updateCamera()
	{
		Floor floor = targetCreature.getCurrentCell().getFloor();
		int floorPixelWidth = (int) (floor.getGridWidth() * Tile.TILE_WIDTH * Tile.TILE_SCALE);
		int floorPixelHeight = (int) (floor.getGridHeight() * Tile.TILE_HEIGHT * Tile.TILE_SCALE);
		
		if(targetCreature != null)
		{
			//if(Math.abs(offsetX - (viewWidth / 2 - targetCreature.getPosX() - (Tile.TILE_WIDTH + Tile.TILE_SCALE) / 2)) >= 2 * Tile.TILE_WIDTH * Tile.TILE_SCALE)
				
//				offsetX = viewWidth / 2 - targetCreature.getPosX() - (Tile.TILE_WIDTH + Tile.TILE_SCALE) / 2;
//				offsetY = viewHeight / 2 - targetCreature.getPosY() - (Tile.TILE_HEIGHT + Tile.TILE_SCALE) / 2;
				
				//if(useBuffer && Math.abs(offsetX + (targetCreature.getPosX() - (Tile.TILE_WIDTH + Tile.TILE_SCALE) / 2)) >= Tile.TILE_WIDTH * Tile.TILE_SCALE)
				{
					offsetX = -(targetCreature.getPosX() - (viewWidth / 2) + (Tile.TILE_WIDTH * Tile.TILE_SCALE) / 2);
					offsetY = -(targetCreature.getPosY() - (viewHeight / 2) + (Tile.TILE_HEIGHT * Tile.TILE_SCALE) / 2);
				}
				if(offsetX > 0)
				{
					offsetX = 0;
				}
				else if(offsetX < -floorPixelWidth + viewWidth)
				{
					offsetX = -floorPixelWidth + viewWidth;
				}
				if(offsetY > 0)
				{
					offsetY = 0;
				}
				else if(offsetY < -floorPixelHeight + viewHeight)
				{
					offsetY = -floorPixelHeight + viewHeight;
				}
				
				//offsetY = -(targetCreature.getPosY());
//				
//				offsetX = -(targetCreature.getPosX());
//				offsetY = -(targetCreature.getPosY());
		}

//		if(targetCreature.getRenderingPosX() + (floorPixelWidth / 2) < 0)
//		{
//			viewX = 0;
//		} else if(targetCreature.getRenderingPosX() + (floorPixelWidth / 2)
//				+ (Tile.TILE_WIDTH * Tile.TILE_SCALE) >= floorPixelWidth)
//		{
//			
//		}
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
