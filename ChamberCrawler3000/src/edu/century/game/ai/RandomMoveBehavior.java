package edu.century.game.ai;

import edu.century.game.entity.Creature;
import edu.century.game.floor.Cell;

public class RandomMoveBehavior extends Behavior
{
	public RandomMoveBehavior(Creature creature)
	{
		super(creature);
	}

	@Override
	public void takeTurn()
	{
		Cell currentCell = creature.getCurrentCell();
		Cell[] neighborCells = currentCell.getNeighbors();
		
		//Determine whether or not an adjecent Cell is open
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
			creature.endTurn();
		}
		else
		{
			System.out.println("couldn't move");
			creature.endTurn();
		}
	}	
}
