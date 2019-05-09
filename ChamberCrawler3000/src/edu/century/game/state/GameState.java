package edu.century.game.state;

import java.awt.Graphics;
import java.util.Iterator;

import edu.century.game.Game;
import edu.century.game.entity.Creature;
import edu.century.game.entity.DamageType;
import edu.century.game.entity.Enemy;
import edu.century.game.entity.Item;
import edu.century.game.entity.ItemType;
import edu.century.game.entity.Player;
import edu.century.game.floor.Floor;
import edu.century.game.graphics.Camera;
import edu.century.game.tiles.Tile;

/**
 * The State where most or all gameplay occurs
 * @author Mitchell Ford
 */
public class GameState extends State
{
	public static final double TURN_WAIT_SEC = 0;
	
	private Player player;
	private Floor floor;
	private Graphics g;
	private Camera camera;
	private Creature currentTurnHolder;
	private Iterator<Creature> turnQueue;
	private long waitTime;

//	/**
//	 * Constructor for GameState without Floor parameter
//	 * @param game the game object
//	 * @param player the player object
//	 * @param g the Graphics object to render things with
//	 */
//	public GameState(Game game, Player player, Graphics g, Camera camera)
//	{
//		//Calls the other GameState constructor with a new SampleFloor as its floor parameter
//		this(game, player, g, new SampleFloor(), camera);
//	}

	/**
	 * Constructor for GameState
	 * @param game the game object
	 * @param player the player object
	 * @param floor the floor object to manage and render
	 */
	public GameState(Game game, Player player, Floor floor, Camera camera)
	{
		super(game);
		this.player = player;
		this.floor = floor;
		this.camera = camera;
		
		//Spawn the player in the Floor
		if(!floor.getCell(floor.getPlayerSpawnX(), floor.getPlayerSpawnY()).setOccupant(player))
		{
			System.err.println("Failed to place the player in the floor");
			System.exit(1);
		}
		floor.addCreature(player);
		
		camera.setTargetCreature(player);
		
		turnQueue = floor.getCreatures().iterator();
		
		currentTurnHolder = player;
		player.startTurn(this);
	}

	@Override
	/**
	 * Manages turns
	 */
	public void update()
	{	
		if(currentTurnHolder == null)
		{
			nextCharactersTurn();
		}
		
		if(currentTurnHolder.isDoneWithTurn() && !currentTurnHolder.isMoving())
		{
			waitTime += Game.timePerUpdate;
			if(waitTime >= Game.timePerUpdate * TURN_WAIT_SEC * 30)
			{
				nextCharactersTurn();
				waitTime = 0;
			}
		}
		else if(Enemy.class.isAssignableFrom(currentTurnHolder.getClass()) && !camera.inViewPort(currentTurnHolder.getCurrentCell()))
		{
			currentTurnHolder.resetAnimationOffsets();
		}
	}

	@Override
	/**
	 * @param g the Graphics object to render things with
	 */
	public void render(Graphics g)
	{
		//Update rendering offsets
		camera.updateCamera();
		
		//Render the floor
		floor.render(g, camera);
		
		//Update the PlayerInfoPanel
		game.getDisplay().updatePlayerInfoPanel(player);
		
		if(currentTurnHolder != null)
		{
			g.drawImage(currentTurnHolder.getRace().getRaceSprite(), 10, 10, 32, 32, null);
		}
	}

	public void nextCharactersTurn()
	{
			currentTurnHolder = null;
			while(currentTurnHolder == null)
			{
				if(!turnQueue.hasNext())
				{
					turnQueue = floor.getCreatures().iterator();
				}

				currentTurnHolder = turnQueue.next();
				
				if(currentTurnHolder != null)
				{
					if(currentTurnHolder.isDead())
					{
						currentTurnHolder = null;
					}
				}
			}
			currentTurnHolder.startTurn(this);
			//camera.setTargetCreature(currentTurnHolder);
			
			System.out.println("It is now " + currentTurnHolder.getName() + "'s turn");
	}

	/**
	 * @param xComponent the x component of the DPad button that created this ActionEvent
	 * @param yComponent the y component of the DPad button that created this ActionEvent
	 */
	public void takePlayerDPadInput(int xComponent, int yComponent)
	{	
		//Check to see if it is the player's turn, ignore this input if it isn't
		if (currentTurnHolder.equals(player) && !player.isDoneWithTurn())
		{
			//Check to see if it was the middle button that was pressed
			if(xComponent == 0 && yComponent == 0)
			{
				
			}
			else
			{
				//Make sure the player isn't trying to move outside the floor's bounds
				if(player.getGridX() + xComponent >= 0 && player.getGridX() + xComponent < floor.getGridWidth()
						&& player.getGridY() + yComponent >= 0 && player.getGridY() + yComponent < floor.getGridHeight())
				{
					//See if the Cell the player is trying to enter is occupied
					if (floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant() == null)
					{
						if(floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupiable())
						{
							//Move
							
							player.move(player.getGridX() + xComponent, player.getGridY() + yComponent);
							
							//End turn
							player.endTurn();
						}	
					}
					else
					{
						//attack or otherwise interact with this Cell's occupant
						
						if(Creature.class.isAssignableFrom(floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant().getClass()))
						{
							//System.out.println("Target Cell's occpant is a Creature? " + Creature.class.isAssignableFrom(floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant().getClass()));
							Creature.doDamage((Creature) player, (Creature) floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant(), DamageType.PHYSICAL, player.getAttack());
						
							//End turn
							player.endTurn();
						}
						else if(Item.class.isAssignableFrom(floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant().getClass()))
						{
							//System.out.println("Target Cell's occpant is a Creature? " + Creature.class.isAssignableFrom(floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant().getClass()));
							Item item = (Item) floor.getCell(player.getGridX() + xComponent, player.getGridY() + yComponent).getOccupant();
							switch(item.getItemType())
							{
								case ARMOR:
									if(player.getArmor() == null)
									{
										player.setArmor(item);
										item.getCurrentCell().setOccupant(null);
										item.setCurrentCell(null);
										appendLog(player.getName() + " equipped " + item.getName());
									}
									break;
								case POTION:
									player.takeHeal(player.getMaxHealth());
									appendLog(player.getName() + " was fully healed by " + item.getName());
									item.getCurrentCell().setOccupant(null);
									item.setCurrentCell(null);
									break;
								case WEAPON:
									if(player.getWeapon() == null)
									{
										player.setWeapon(item);
										item.getCurrentCell().setOccupant(null);
										item.setCurrentCell(null);
										appendLog(player.getName() + " equipped " + item.getName());
									}
									break;
								default:
									break;
							}
						
							//End turn
							player.endTurn();
						}
					}
				}
			}
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void appendLog(String line)
	{
		game.getDisplay().appendLog(line);
	}
}