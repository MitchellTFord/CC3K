package edu.century.game.state;

import java.awt.Graphics;

import edu.century.game.Game;
import edu.century.game.entity.Creature;
import edu.century.game.entity.Player;
import edu.century.game.floor.Floor;
import edu.century.game.floor.SampleFloor;

/**
 * The State where most or all gameplay occurs
 * @author Mitchell Ford
 */
public class GameState extends State
{
	private Player player;
	private Floor floor;
	private Graphics g;
	private Creature currentTurnHolder;

	/**
	 * Constructor for GameState without Floor parameter
	 * @param game the game object
	 * @param player the player object
	 * @param g the Graphics object to render things with
	 */
	public GameState(Game game, Player player, Graphics g)
	{
		//Calls the other GameState constructor with a new SampleFloor as its floor parameter
		this(game, player, g, new SampleFloor());
	}

	/**
	 * Constructor for GameState
	 * @param game the game object
	 * @param player the player object
	 * @param g the Graphics object to render things with
	 * @param floor the floor object to manage and render
	 */
	public GameState(Game game, Player player, Graphics g, Floor floor)
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
	/**
	 * Manages turns
	 */
	public void update()
	{
		//TODO: manage turns
	}

	@Override
	/**
	 * @param g the Graphics object to render things with
	 */
	public void render(Graphics g)
	{
		//Render the floor
		floor.render(g, 0, 0);
		
		//Update the PlayerInfoPanel
		game.getDisplay().updatePlayerInfoPanel(player);
	}

	/**
	 * @param xComponent the x component of the DPad button that created this ActionEvent
	 * @param yComponent the y component of the DPad button that created this ActionEvent
	 */
	public void takePlayerDPadInput(int xComponent, int yComponent)
	{
		//Check to see if it is the player's turn, ignore this input if it isn't
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
				//attack or otherwise interact with this Cell's occupant
				
				//End turn
			}
		}
	}
}