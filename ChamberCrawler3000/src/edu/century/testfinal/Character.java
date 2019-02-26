package edu.century.testfinal;

public class Character extends Entity
{
	private double health, maxHealth, attack, defence;
	private Item armor, weapon;
	private Race race;
	private Tile currentTile;
	private Effect[] effects = new effects[64];
	
	public Character(Tile currentTile, Race race)
	{
		super();
		
		this.currentTile = currentTile;
		this.race = race;
		
		this.health = this.maxHealth = 125 + this.race.getHealthMod();
		this.attack = 25 + this.race.getAttackMod();
		this.defence = 25 + this.race.getDefenceMod();
		
		this.effects[0] = this.race.
	}
}