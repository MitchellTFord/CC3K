package com.mitchelltford.game.entity;

public enum DamageType
{
	//doDamage() calls target.takeDamage(Math.ceil((100 / (100 + target.getDefence())) * damage), caster)
	PHYSICAL, 
	
	//doDamage() calls target.takeDamage(damage, caster) until some sort of magic defense is implemented
	ELEMENTAL;
}
