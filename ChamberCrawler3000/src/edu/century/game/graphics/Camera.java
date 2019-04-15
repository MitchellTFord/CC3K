package edu.century.game.graphics;

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

	// The Creature the Camera should follow
	private Creature targetCreature;

	public Camera(int viewWidth, int viewHeight, Creature targetCreature)
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.targetCreature = targetCreature;
	}
	
	public Camera(int viewWidth, int viewHeight)
	{
		this(viewWidth, viewHeight, null);
	}

	public void updateCamera()
	{
		if(targetCreature != null)
		{
			Floor floor = targetCreature.getCurrentCell().getFloor();
			int floorPixelWidth = (int) (floor.getGridWidth() * Tile.TILE_WIDTH * Tile.TILE_SCALE);
			int floorPixelHeight = (int) (floor.getGridHeight() * Tile.TILE_HEIGHT * Tile.TILE_SCALE);

			offsetX = viewWidth / 2 - targetCreature.getPosX() - (Tile.TILE_WIDTH + Tile.TILE_SCALE) / 2;
			offsetY = viewHeight / 2 - targetCreature.getPosY() - (Tile.TILE_HEIGHT + Tile.TILE_SCALE) / 2;
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
