package com.mitchelltford.game.entity;

import com.mitchelltford.game.ai.Behavior;
import com.mitchelltford.game.ai.BehaviorTag;
import com.mitchelltford.game.ai.PursueBehavior;
import com.mitchelltford.game.ai.RandomMoveBehavior;
import com.mitchelltford.game.entity.race.Race;
import com.mitchelltford.game.floor.Cell;
import com.mitchelltford.game.state.GameState;

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
