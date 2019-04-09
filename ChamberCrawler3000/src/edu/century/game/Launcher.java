package edu.century.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launcher extends JFrame implements ActionListener
{
	private static Launcher launcher;
	
	boolean useDPad = true;
	
	JPanel optionsPanel, startPanel;
	JButton startButton;
	JCheckBox useDPadCheckBox;
	
	private int width, height;
	
	public static void main(String[] args)
	{
		launcher = new Launcher(300, 300);
		
//		Game game = new Game("Chamber Crawler 3000", 640, 360, true);
//		game.start();
	}
	
	public Launcher(int width, int height)
	{
		super("CC3K Launcher");
		this.width = width;
		this.height = height;
		
		buildLauncher();
	}
	
	private void buildLauncher()
	{
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Actually exit on close
		setResizable(false); //Not resizable
		setLocationRelativeTo(null); //Centered on screen
		setLayout(new BorderLayout());
		
		buildOptionsPanel();
		
		buildStartPanel();
		
		setVisible(true);
	}
	
	private void buildStartPanel()
	{
		startPanel = new JPanel();
		startPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(startPanel, BorderLayout.SOUTH);
		
		startButton = new JButton("Start Game");
		startButton.addActionListener(this);
		startPanel.add(startButton);
	}
	
	private void buildOptionsPanel()
	{
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(2, 2));
		this.add(optionsPanel, BorderLayout.NORTH);
		
		JCheckBox useDPadCheckBox = new JCheckBox("Use DPad");
		useDPadCheckBox.addActionListener(this);
		optionsPanel.add(useDPadCheckBox);
	}
	
	private void startGame(int width, int height, boolean useDPad)
	{
		setVisible(false);
		
		Game game = new Game("Chamber Crawler 3000", width, height, useDPad);
		game.start();
		
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		String actionText = actionEvent.getActionCommand();
		if(actionText.equals("Start Game"))
		{
			//TODO: have this invocation use width and height variables
			startGame(640, 360, useDPad);
		} else if(actionText.equals("Use DPad"));
		{
			//useDPad = useDPadCheckBox.isSelected();
		}
	}
	
}