# ChamberCrawler3000 #

![Demo GIF 1](https://github.com/MitchellTFord/CC3K/blob/master/doc/images/CC3K%20Demo%201.gif)

### What is ChamberCrawler3000? ###
CC3K is a project for computer science students developed by A. Roegiest and B. Lushman in 2013. A copy of the original document can be found [here](https://github.com/MitchellTFord/CC3K/blob/master/doc/cc3k%20assignment%20doc.pdf). The gameplay of CC3K is meant to be a recreation of the 1980 game [Rogue](https://en.wikipedia.org/wiki/Rogue_(video_game)), which in turn is meant to be a digital recreation of [Dungeons and Dragons](https://en.wikipedia.org/wiki/Dungeons_%26_Dragons).

### If someone else came up with the idea, what did I do? ###
The original project document called for C++ programming and ASCII graphics, but I needed to code in Java and use Swing for graphics to meet the requirements of the assignment I had been given. As such, I adapted the original project to my own needs. The design of the in-game races are entirely the work of Roegiest and Lushman, but all other code and design is my own work.

# Major Features #
* Launcher
  * Give your character a custom name
  * Choose your character's Race
  * Select a Floor file to play in
* Custom Floors (Levels)
  * User-made Floors allow for near infinite replayability
  * With a bit of practice, floors are easy and quick to make
  * There is no included editor, but Microsoft Excel or Google Sheets can be used effectively
* Races
  * Five distinct player Races allow for many different playstyles
  * Each Race (except Shade) has modifers to its base attributes and its own Racial Effect
  * The Races are: Shade (base), Drow, Vampire, Troll, and Goblin
* Effects
  * Effects can be helpful or harmful
  * Some Effects only modify base attributes, be it positively or negatively
  * Other Effects can heal, damage, or even steal gold from Enemies
* Enemies
  * Each type of enemy has varying attributes, some even have unique Effects
  * There are six types of Enemy in total: Human, Dwarf, Elf, Orc, Dragon, and Halfling
  * Enemies can have one of a few built-in behavior patterns, reflecting different levels of aggression
  * Although the player is able to move diagonally, Enemies are not
* Items
  * The player can pick up two different types of equipment which modify their attributes
  * Weapons generally increase attack, and Armor generally increases defence
  * Both of these can also apply unique Effects to the player
  * Players can pick potions up from the ground, fully restoring their health
* User Interface
  * The player controls their character using an eight directional buttons
  * Despite there not being many animations, combat and environmental events can be viewed in the log on the right side of the screen
  * The "show effects" button above the control pad can be used to display all of the Effects currently on the player

# Design #
### The Game Loop ###
![Game Loop](https://github.com/MitchellTFord/CC3K/blob/master/doc/images/game%20loop.png)
