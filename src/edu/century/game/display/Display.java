package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.century.game.Game;
import edu.century.game.entity.Player;

public class Display extends JFrame implements WindowListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width, height;
	
	private BorderLayout layout;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); //Not resizable
		setLocationRelativeTo(null); //Centered on screen
		setVisible(true);
		
		layout = new BorderLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(width * 1/4, height));
		leftPanel.setMaximumSize(new Dimension(width * 1/4, height));
		leftPanel.setMinimumSize(new Dimension(width * 1/4, height));
		add(leftPanel, BorderLayout.WEST);
		
		if(useDPad)
		{
			dPad = new DirectionalPad(game, width * 1/8);
			leftPanel.add(dPad, BorderLayout.SOUTH);
		}
		
		playerInfoPanel = new PlayerInfoPanel(width * 1/4, height);
		leftPanel.add(playerInfoPanel, BorderLayout.CENTER);
		
		logPanel = new LogPanel(width * 1/4, height);
		add(logPanel, BorderLayout.EAST);
		
		floorPanel = new FloorDisplay(width * 1/2, height);
		add(floorPanel, BorderLayout.CENTER);
		
		pack();
	}
	
	public String getLogText()
	{
		return logPanel.getLog();
	}
	
	public void appendLog(String line)
	{
		logPanel.appendLog(line);
	}
	
	public FloorDisplay getFloorPanel()
	{
		return this.floorPanel;
	}
	
	public void updatePlayerInfoPanel(Player player)
	{
		playerInfoPanel.updatePlayerInfo(player);
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		//new ConfirmExit((JComponent) this.getContentPane());
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}