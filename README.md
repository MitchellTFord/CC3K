# ChamberCrawler3000
Final Project for CSCI 1082.90
By Brent Hojer and Mitchell Ford

ChamberCrawler3000 is a project for computer science students developed by A. Roegiest and B. Lushman in 2013.
http://csclub.uwaterloo.ca/~a2alsuda/1135/a5/cc3k.pdf
The origial document calls for the program to be written in C++ and to use the console for graphics and user input.
We will be using Java and will make use of the Swing toolkit for 2D graphics.

It is unlikely that we will be able to implement all of the features mentioned in the document, so we are going to prioritize key features and add more as time permits.

Key Features
-Player character (PC)
	-Movement
	-Attacking
	-Stats (health, attack, defense)
-Non-player characters, enemies (NPCs)
	-Will move randomly or in the direction of the player 
	-Able to attack the PC
	-Able to be attacked by the PC
	-Stats, like the PC, based on enemy type
-Items
	-Appear on the ground and can be placed into the PC's inventory
	-A few different types of potions (restore health,  boost attack or defence, etc.)
	-Weapons and armor that can be equipped by the PC
-A main floor/map
	-Hardcoded layout
	-2D array of tiles/cells that contain references to the character or item they contain

Potential Additional Features
-Races
	-Human (base), elf, dwarf, etc.
	-Affects stats or item usage in some way
-Classes
	-Warrior (base), archer, mage
	-Affects the stats of the PC and the types of items they use
	-Would require a reworking of the combat system, perhaps it should be implemented in a limited fashion from the start
	-Along with classes would come attacks other than directly damaging enemies (cone, AOE, ranged, etc.)
-New floors/maps
	-Could just mean adding more hardcoded floors
	-Could potentially be randomly/procedurally generated
