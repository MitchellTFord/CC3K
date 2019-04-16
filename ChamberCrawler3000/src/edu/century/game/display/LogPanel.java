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
public class LogPanel extends JPanel
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
	public LogPanel(int width, int height)
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
		setBackground(Color.pink);
		
		//Sets the layout of the panel to a BorderLayout
		setLayout(new BorderLayout());
		
		//Player info text area
		textArea = new JTextArea("--- Game Log ---");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.CENTER);
	}
	
	/**
	 * Updates the information on the player
	 * @param player the Player object
	 */
	public void appendLog(String text)
	{
		textArea.append(text);
		
//		if(getFontMetrics(getFont()).stringWidth(text) < textArea.getWidth())
//		{
//		textArea.append(text);
//		}
//		else
//		{
//			String[] textParts = new String[4];	
//		}
	}
}
