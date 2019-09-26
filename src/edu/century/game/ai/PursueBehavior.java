package edu.century.game.ai;

import edu.century.game.entity.Creature;
import edu.century.game.entity.DamageType;
import edu.century.game.entity.Player;
import edu.century.game.floor.Cell;

/**
 * Behavior which causes the Creature to move toward and attack the Player
 * 
 * @author Mitchell Ford
 *
 */
public class PursueBehavior extends Behavior
{
	public PursueBehavior(Creature creature)
	{
		super(creature);
	}

	@Override
	public void takeTurn()
	{
		if(attackTarget(creature.getTurnController().getPlayer()))
		{
			creature.endTurn();
		}
		else if(moveTowardTarget(creature.getTurnController().getPlayer(), 3))
		{
			//creature.getTurnController().appendLog(creature.getName() + " moves toward the player");
			creature.endTurn();
		}
		else if(moveRandomly())
		{
			//creature.getTurnController().appendLog(creature.getName() + " moves randomly");
			creature.endTurn();
		}
		else
		{
			//creature.getTurnController().appendLog(creature.getName() + " couldn't move");
			creature.endTurn();
		}
	}

	private boolean attackTarget(Creature target)
	{
		if(creature.getCurrentCell().isAdjecent(target.getCurrentCell()))
		{
			if(Math.random() > Player.PLAYER_EVASION_CHANCE)
			{
				Creature.doDamage(creature, creature.getTurnController().getPlayer(), DamageType.PHYSICAL,
						creature.getAttack());
			}
			else
			{
				creature.getTurnController().appendLog(creature.getName() + " missed an attack against " + target.getName());
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean moveTowardTarget(Creature target, int minDistance)
	{
		Cell currentCell = creature.getCurrentCell();
		Cell[] neighborCells = currentCell.getNeighbors();

		if(manhattanDistance(currentCell, target.getCurrentCell()) > minDistance)
		{
			return false;
		}
		
		// Move closer to the target if possible
		for(int i = 0; i < neighborCells.length; i++)
		{
			if(neighborCells[i] != null)
			{
				if(neighborCells[i].getSpaceOpen()
						&& manhattanDistance(neighborCells[i], target.getCurrentCell()) 
						< manhattanDistance(creature.getCurrentCell(), target.getCurrentCell()))
				{
					if(creature.move(neighborCells[i]))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}

	private boolean moveRandomly()
	{
		Cell currentCell = creature.getCurrentCell();
		Cell[] neighborCells = currentCell.getNeighbors();

		// Determine whether or not an adjacent Cell is open
		boolean openNeighbor = false;
		for(int i = 0; i < neighborCells.length; i++)
		{
			if(neighborCells[i] != null)
			{
				if(neighborCells[i].getSpaceOpen())
				{
					openNeighbor = true;
				}
			}
		}

		if(openNeighbor)
		{
			boolean moved = false;
			while(!moved)
			{
				Cell randomNeighbor = neighborCells[(int) Math.round(Math.random() * (neighborCells.length - 1))];
				if(randomNeighbor != null)
				{
					if(creature.move(randomNeighbor))
					{
						moved = true;
					}
				}
			}
			return true;
		}
		return false;
	}
}
