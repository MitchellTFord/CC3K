package edu.century.game.entity;

import edu.century.game.ai.Behavior;
import edu.century.game.ai.BehaviorTag;
import edu.century.game.ai.PursueBehavior;
import edu.century.game.ai.RandomMoveBehavior;
import edu.century.game.entity.race.Race;
import edu.century.game.floor.Cell;
import edu.century.game.state.GameState;

public class Enemy extends Creature
{
	protected Behavior behavior;

	public Enemy(Cell currentCell, Race race, BehaviorTag behaviorTag)
	{
		super(currentCell, race);
		switch(behaviorTag)
		{
			case RANDOM_MOVE:
				this.behavior = new RandomMoveBehavior(this);
				break;
			case PURSUE:
				this.behavior = new PursueBehavior(this);
				break;
			default:
				this.behavior = new RandomMoveBehavior(this);
				break;
		}
	}

	/**
	 * Triggered at the start of a turn, calls updateStats() and applyEffects()
	 */
	public void startTurn(GameState turnController)
	{
		this.turnController = turnController;

		this.updateStats();
		this.applyEffects();

		behavior.takeTurn();
	}
}
