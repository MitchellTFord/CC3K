package edu.century.game.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.century.game.entity.Player;

public class Display extends JFrame
{
	private int width, height;
	
	private FloorDisplay floorPanel;
	
	//Temp
	private JPanel leftPanel;
	private Player player;
	
	private DirectionalPad dPad;
	private boolean useDPad;
	
	public Display(Player player, String title, int width, int height, boolean useDPad)
	{
		super(title);
		this.player = player;
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
			
			dPad = new DirectionalPad();
			dPad.player = player;
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