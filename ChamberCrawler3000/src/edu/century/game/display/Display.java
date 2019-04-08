package edu.century.game.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.century.game.Game;
import edu.century.game.entity.Player;

public class Display extends JFrame
{
	private int width, height;
	
	private FloorDisplay floorPanel;
	
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
		
		floorPanel = new FloorDisplay(width * 2/3, height);
		add(floorPanel, BorderLayout.CENTER);
		
		if(useDPad)
		{
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout(2, 1));
			
			dPad = new DirectionalPad(game);
			leftPanel.add(dPad, BorderLayout.SOUTH);
			
			add(leftPanel, BorderLayout.WEST);
		}
		
		pack();
	}
	
	public FloorDisplay getFloorPanel()
	{
		return this.floorPanel;
	}
}