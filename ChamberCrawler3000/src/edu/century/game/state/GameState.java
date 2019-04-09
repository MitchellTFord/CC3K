package edu.century.game.state;

import java.awt.Graphics;

import edu.century.game.Game;
import edu.century.game.entity.Creature;
import edu.century.game.floor.Floor;
import edu.century.game.floor.SampleFloor;

public class GameState extends State
{
	private Creature player;
	private Floor floor;
	private Graphics g;
	private Creature currentTurnHolder;

	public GameState(Game game, Creature player, Graphics g)
	{
		this(game, player, g, new SampleFloor());
	}

	public GameState(Game game, Creature player, Graphics g, Floor floor)
	{
		super(game);
		this.player = player;
		this.g = g;
		this.floor = floor;
		
		floor.getCell(floor.getPlayerSpawnX(), floor.getPlayerSpawnY()).setOccupant(player);
		
		currentTurnHolder = player;
		player.startTurn();
	}

	@Override
	public void update()
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		floor.render(g, 0, 0);
		
		game.getDisplay().updatePlayerInfoPanel(player);
	}

	public void takePlayerDPadInput(int xComponent, int yComponent)
	{
		if (currentTurnHolder.equals(player))
		{
			//Make sure the player isn't trying to move outside the floor's bounds
			if(player.getGridX() + xComponent >= 0 && player.getGridX() + xComponent < floor.getGridWidth()
					&& player.getGridY() + yComponent >= 0 && player.getGridY() + yComponent < floor.getGridHeight())
			{
				//See if the Cell the player is trying to enter is occupied
				if (floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getSpaceOpen())
				{
					//Move
					player.move(player.getGridX() + xComponent, player.getGridY() + yComponent);
					
					//End turn
				}
			}
			else
			{
				//attack or otherwise interact
				
				//End turn
			}
		}
	}
}