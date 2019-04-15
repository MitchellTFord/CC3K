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
	private int viewX, viewY;

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

			viewX = targetCreature.getRenderingPosX() - (floorPixelWidth / 2) - viewX;
			viewY = targetCreature.getRenderingPosY() - (floorPixelHeight / 2) - viewY;
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
	
	public int getViewX()
	{
		return viewX;
	}
	
	public int getViewY()
	{
		return viewY;
	}
	
	public void setTargetCreature(Creature targetCreature)
	{
		this.targetCreature = targetCreature;
	}
}
