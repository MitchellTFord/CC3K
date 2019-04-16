package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.century.game.Game;
import edu.century.game.entity.Player;

public class Display extends JFrame
{
	private int width, height;
	
	private FloorDisplay floorPanel;
	
	private PlayerInfoPanel playerInfoPanel;
	
	private LogPanel logPanel;
	
	private Game game;
	
	private JPanel leftPanel;
	
	private DirectionalPad dPad;
	private boolean useDPad;
	
	public Display(Game game, String title, int width, int height, boolean useDPad)
	{
		super(title);
		this.game = game;
		this.width = width;
		this.height = height;
		this.useDPad = useDPad;
		init();
	}
	
	private void init()
	{
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Actually exit on close
		setResizable(false); //Not resizable
		setLocationRelativeTo(null); //Centered on screen
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		floorPanel = new FloorDisplay(width * 1/2, height);
		add(floorPanel, BorderLayout.CENTER);
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(width * 1/4, height));
		leftPanel.setMaximumSize(new Dimension(width * 1/4, height));
		leftPanel.setMinimumSize(new Dimension(width * 1/4, height));
		add(leftPanel, BorderLayout.WEST);
		
		if(useDPad)
		{
			dPad = new DirectionalPad(game);
			leftPanel.add(dPad, BorderLayout.SOUTH);
		}
		
		playerInfoPanel = new PlayerInfoPanel(width * 1/4, height);
		leftPanel.add(playerInfoPanel, BorderLayout.CENTER);
		
		logPanel = new LogPanel(width * 1/4, height);
		add(logPanel, BorderLayout.EAST);
		
		pack();
	}
	
	public FloorDisplay getFloorPanel()
	{
		return this.floorPanel;
	}
	
	public void updatePlayerInfoPanel(Player player)
	{
		playerInfoPanel.updatePlayerInfo(player);
	}
}