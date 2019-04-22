package edu.century.game.floor;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.century.game.tiles.Tile;

/**
 * 
 * @author Mitchell Ford
 *
 */
public class Floor
{
	// The number of cells wide/tall this Floor is
	protected int gridWidth, gridHeight;

	// All of the cells on this Floor, ordered [gridX][gridY]
	protected Cell[][] cells;

	// Keeps track of all the Character objects on this Floor
	protected Character[] characters;
	
	//Number of Characters currently in the characters array
	protected int numCharacters;
	
	// The gridX and gridY locations of the player's spawn location
	protected int playerSpawnX, playerSpawnY;
	
	/**
	 * Floor constructor with playerSpawn parameters
	 * @param gridWidth the number of Cells wide this Floor should be
	 * @param gridHeight the number of Cells tall this Floor should be
	 * @param playerSpawnX the x index of the cells array that the player should spawn at
	 * @param playerSpawnY the y index of the cells array that the player should spawn at
	 * @param tiles the two dimensional array of Tile objects that the Cells in this Floor should inherit their textures and occupiability from
	 */
	public Floor(int gridWidth, int gridHeight, int playerSpawnX, int playerSpawnY, Tile[][] tiles)
	{
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.playerSpawnX = playerSpawnX;
		this.playerSpawnY = playerSpawnY;
		
		// Initializes the cells array with dimensions defined by the
		// constructor
		cells = new Cell[gridWidth][gridHeight];

		// Iterates through all of the positions in the cells array
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{
				// Creates a new Cell object in the current position, assigns a Tile object to each
				if(tiles != null)
				{
					cells[gridX][gridY] = new Cell(this, gridX, gridY, tiles[gridX][gridY]);
				}
				else
				{
					//Assign all created Cells Tile.dirtTile
					cells[gridX][gridY] = new Cell(this, gridX, gridY, getRandomTile());
				}
			}
		}

		// Initializes the characters array with a length long enough to keep
		// track of a Character object in every Cell object in cells
		characters = new Character[gridWidth * gridHeight];
	}
	
	/**
	 * Floor constructor without playerSpawns
	 * @param gridWidth the number of Cells wide this Floor should be
	 * @param gridHeight the number of Cells tall this Floor should be
	 * @param tiles the two dimensional array of Tile objects that the Cells in this Floor should inherit their textures and occupiability from
	 */
	public Floor(int gridWidth,  int gridHeight, Tile[][] tiles)
	{
		//Set playerSpawn to the center of the Floor
		this(gridWidth, gridHeight, Math.round(gridWidth / 2), Math.round(gridHeight / 2), tiles);
	}

	/**
	 * Floor constructor without playerSpawns or tiles
	 * @param gridWidth the number of Cells wide this Floor should be
	 * @param gridHeight the number of Cells tall this Floor should be
	 */
	public Floor(int gridWidth, int gridHeight)
	{
		//Passes in null for tiles for you
		this(gridWidth, gridHeight, null);
	}
	
	public Floor(File file) throws FloorFormatException
	{		
		try
		{
			// Open a new input stream
			Scanner fileInput = new Scanner(new FileInputStream(file));
			
			if(fileInput.hasNext("\\d+,\\d+\\n"))
			{
				String[] dimensionTokens = fileInput.nextLine().split(",");
				gridWidth = Integer.parseInt(dimensionTokens[0]);
				gridHeight = Integer.parseInt(dimensionTokens[1]);
			}
			
			if(fileInput.hasNext("\\d+,\\d+\\n"))
			{
				String[] playerSpawnTokens = fileInput.nextLine().split(",");
				playerSpawnX = Integer.parseInt(playerSpawnTokens[0]);
				playerSpawnY = Integer.parseInt(playerSpawnTokens[1]);
			}
			
			String linePattern = "((\\d+|(\\d:\\d)),){" + (gridWidth - 1) + "}(\\d+|(\\d:\\d))";
			
			String filePattern = "(" + linePattern + "\\n)" + "{" + (gridHeight - 1) + "}" + linePattern;
			
			if(fileInput.hasNext(filePattern))
			{
				System.out.println("Good format");
			}
			
			fileInput.close();
		}
		catch(FileNotFoundException e)
		{
			
		}
	}

	public void render(Graphics g, double offsetX, double offsetY)
	{
		// Render Tile textures
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{
				// Calls renderTile() for each Cell object in cells using passed in
				// offset values
				cells[gridX][gridY].renderTile(g, offsetX, offsetY);
			}
		}
		
		//Render Entity textures
		for(int gridY = 0; gridY < gridHeight; gridY++)
		{
			for(int gridX = 0; gridX < gridWidth; gridX++)
			{
				// Calls renderOccupant() for each Cell object in cells using passed in
				// offset values
				cells[gridX][gridY].renderOccupant(g, offsetX, offsetY);
			}
		}
	}

	private Tile getRandomTile()
	{
		return Tile.tileIDs[(int) Math.round(Math.random() * (Tile.numTiles - 1))];
	}
	
	public Cell getCell(int gridX, int gridY)
	{
		// Checks to see if the given gridX and gridY values are within the
		// bounds of cells
		if (gridX >= 0 && gridX < this.gridWidth && gridY >= 0 && gridY < this.gridHeight)
		{
			// Return the requested Cell object
			return cells[gridX][gridY];
		} else
		{
			// Return null because the given gridX and gridY values aren't
			// within the bounds of cells
			return null;
		}
	}
	
//	public void addCharacter(Character newCharacter)
//	{
//		sortCharacters();
//		
//		if(numCharacters >= characters.length)
//		{
//			expandCharacters();
//		}
//		
//		for(int i = 0; i < characters.length; i++)
//		{
//			if(characters[i] == null)
//			{
//				characters[i] = newCharacter;
//				
//				return;
//			}
//		}
//	}
	
//	public void removeCharacter(Character removeCharacter)
//	{
//		sortCharacters();
//		
//		for(int i = 0; i < characters.length; i++)
//		{
//			if(characters[i].equals(removeCharacter))
//			{
//				characters[i] = null;
//				
//				return;
//			}
//		}
//	}
	
//	public void expandCharacters()
//	{
//		//Temporary array of twice the size
//		Character[] newCharacters = new Character[numCharacters * 2];
//		
//		//Duplicate the items from the original array to the new array
//		for(int i = 0; i < numCharacters; i++)
//		{
//			newCharacters[i] = characters[i];
//		}
//		
//		//Set the reference to the original array to the temporary array
//		characters = newCharacters;
//	}
	
	public class FloorFormatException extends Exception
	{
		private FloorFormatException(String errorMessage)
		{
			super(errorMessage);
		}
	}
	
//	/**
//	 * Sorts the characters array
//	 */
//	public void sortCharacters()
//	{
//		//TODO: implement sortCharacters()
//	}
	
	/**
	 * @return this Floor's characters array
	 */
	public Character[] getCharacters()
	{
		return this.characters;
	}
	
	/**
	 * @return the number of Cells wide this Floor is
	 */
	public int getGridWidth()
	{
		return gridWidth;
	}
	
	/**
	 * @return the number of Cells tall this Floor is
	 */
	public int getGridHeight()
	{
		return gridHeight;
	}

	/**
	 * @return the gridX coordinate that the player should spawn at
	 */
	public int getPlayerSpawnX()
	{
		return playerSpawnX;
	}
	
	/**
	 * @return the gridY coordinate that the player should spawn at
	 */
	public int getPlayerSpawnY()
	{
		return playerSpawnY;
	}
}