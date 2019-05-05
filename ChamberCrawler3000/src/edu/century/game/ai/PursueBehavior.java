package edu.century.game.ai;

import edu.century.game.entity.Creature;
import edu.century.game.entity.DamageType;
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
			System.out.println("Attacking");
			creature.endTurn();
		}
		else if(moveTowardTarget(creature.getTurnController().getPlayer()))
		{
			System.out.println("Moving toward target");
			creature.endTurn();
		}
		else if(moveRandomly())
		{
			System.out.println("Moving randomly");
			creature.endTurn();
		}
		else
		{
			System.out.println("Couldn't move");
			creature.endTurn();
		}
	}

	private boolean attackTarget(Creature target)
	{
		if(creature.getCurrentCell().isAdjecent(target.getCurrentCell()))
		{
			Creature.doDamage(creature, creature.getTurnController().getPlayer(), DamageType.PHYSICAL,
					creature.getAttack());
			creature.doAttackAnimation(creature.getGridX() - target.getGridX(), creature.getGridY() - target.getGridY());
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean moveTowardTarget(Creature target)
	{
		Cell currentCell = creature.getCurrentCell();
		Cell[] neighborCells = currentCell.getNeighbors();

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
