package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Highlighter.HighlightPainter;

import edu.century.game.entity.Player;
 /**
  * The panel for displaying information about the player
  * @author Mitchell Ford
  */
public class LogPanel extends JPanel
{
	//The width/height of the panel
	private int width, height;
	
	//The scrollpane for the text area
	private JScrollPane scrollPane;
	
	//The text area for displaying player information
	private JTextArea textArea;
	
	//The layout of the panel
	private BorderLayout layout;
	
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
		
		layout = new BorderLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(width, height));
		scrollPane.setMaximumSize(new Dimension(width, height));
		scrollPane.setMinimumSize(new Dimension(width, height));
		
		add(scrollPane);
	}
	
	/**
	 * Updates the information on the player
	 * @param player the Player object
	 */
	public void appendLog(String text)
	{
		textArea.append(" - " + text + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public String getLog()
	{
		return textArea.getText();
	}
}
