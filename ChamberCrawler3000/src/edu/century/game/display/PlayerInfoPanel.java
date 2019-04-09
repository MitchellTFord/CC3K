package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.century.game.entity.Creature;

public class PlayerInfoPanel extends JPanel
{
	private int width, height;
	
	private JTextArea textArea;
	
	public PlayerInfoPanel(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
		
		init();
	}
	
	private void init()
	{
		setLayout(new BorderLayout());
		
		setBackground(Color.gray);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.CENTER);
	}
	
	public void updatePlayerInfo(Creature player)
	{
		textArea.setText(player.toString());
	}
}
