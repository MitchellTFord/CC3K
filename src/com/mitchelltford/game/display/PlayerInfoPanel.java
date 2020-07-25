package com.mitchelltford.game.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mitchelltford.game.effect.Effect;
import com.mitchelltford.game.entity.Player;
 /**
  * The panel for displaying information about the player
  * @author Mitchell Ford
  */
public class PlayerInfoPanel extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//The width/height of the panel
	private int width, height;
	
	private BorderLayout layout;
	
	//The text area for displaying player information
	private JTextArea textArea;
	
	//Button for opening the effects pop-up
	private JButton showEffectsButton;
	
	//The text that the effects pop-up should display
	private Effect[] playerEffects;
	
	private Icon playerRaceIcon;
	
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
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, 0));
		setMaximumSize(new Dimension(width, height));
		
		layout = new BorderLayout();
		layout.setHgap(0);
		setLayout(layout);
		
		showEffectsButton = new JButton("Show Effects");
		showEffectsButton.addActionListener(this);
		add(showEffectsButton, BorderLayout.SOUTH);
		
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
		playerRaceIcon = player.getRace().getRaceIcon();
		playerEffects = player.getEffects();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		JComponent actionSource = (JComponent) actionEvent.getSource();
		if(actionSource.equals(showEffectsButton))
		{
			String effectsInfo = "";
			for(int i = 0; i < playerEffects.length; i++)
			{
				if(playerEffects[i] != null)
				{
					effectsInfo += playerEffects[i].toString() + "\n";
				}	
			}
			
			if(effectsInfo.equals(""))
			{
				effectsInfo = "This character has no effects applied to it";
			}
			
			JTextArea dialogTextArea = new JTextArea(6, 24);
			dialogTextArea.setText(effectsInfo);
			dialogTextArea.setEditable(false);
			JScrollPane dialogScrollPane = new JScrollPane(dialogTextArea);
			JOptionPane.showMessageDialog(this, dialogScrollPane, "Effects applied to this Character", JOptionPane.PLAIN_MESSAGE, playerRaceIcon);
		}
	}
}
