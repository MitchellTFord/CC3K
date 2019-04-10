package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.century.game.entity.Player;
 /**
  * The panel for displaying information about the player
  * @author Mitchell Ford
  */
public class PlayerInfoPanel extends JPanel
{
	//The width/height of the panel
	private int width, height;
	
	//The text area for displaying player information
	private JTextArea textArea;
	
	/**
	 * Constructor for PlayerInfoPanel
	 * @param width the width of the panel
	 * @param height the height of the panel
	 */
	public PlayerInfoPanel(int width, int height)
	{
		//Calls JPanel's constructor
		super();
		
		this.width = width;
		this.height = height;
		
		//Build the panel
		init();
	}
	
	/**
	 * Builds the panel
	 */
	private void init()
	{
		//Temp
		setBackground(Color.gray);
		
		//Sets the layout of the panel to a BorderLayout
		setLayout(new BorderLayout());
		
		//Player info text area
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.CENTER);
	}
	
	/**
	 * Updates the information on the player
	 * @param player the Player object
	 */
	public void updatePlayerInfo(Player player)
	{
		textArea.setText(player.toString());
	}
}
