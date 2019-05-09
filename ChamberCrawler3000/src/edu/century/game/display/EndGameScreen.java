package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.century.game.entity.Player;
import edu.century.game.entity.race.Race;

public class EndGameScreen extends JFrame implements WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField scoreField;
	private JTextArea playerInfoTextArea, logTextArea;
	private JScrollPane logScrollPane;
	private Player player;
	private String logText;

	/**
	 * Create the frame.
	 */
	public EndGameScreen(Player player, String logText)
	{
		super("Game Over");
		this.player = player;
		this.logText = logText;
		init();
	}
	
	private void init()
	{
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false); // Not resizable
		setLocationRelativeTo(null); // Centered on screen
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		double score = player.getGold();
		if(player.getRace().equals(Race.playerRaces[0]))
		{
			score *= 1.5;
		}
		scoreField = new JTextField("Score: " + (int) Math.ceil(score));
		scoreField.setEditable(false);
		contentPane.add(scoreField, BorderLayout.NORTH);
		
		playerInfoTextArea = new JTextArea();
		playerInfoTextArea.setEditable(false);
		playerInfoTextArea.setPreferredSize(new Dimension(contentPane.getWidth() / 2, contentPane.getHeight()));
		playerInfoTextArea.setMinimumSize(new Dimension(contentPane.getWidth() / 3, contentPane.getHeight()));
		playerInfoTextArea.setColumns(16);
		playerInfoTextArea.setText(player.toString());
		contentPane.add(playerInfoTextArea, BorderLayout.WEST);
		
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logTextArea.setText(logText);
		logTextArea.setLineWrap(true);
		logTextArea.setWrapStyleWord(true);
		logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
		
		logScrollPane = new JScrollPane(logTextArea);
		logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		logScrollPane.setPreferredSize(new Dimension(contentPane.getWidth() * 2/3, contentPane.getHeight()));
		logScrollPane.setMinimumSize(new Dimension(contentPane.getWidth() / 2, contentPane.getHeight()));
		contentPane.add(logScrollPane, BorderLayout.CENTER);
		
		//pack();
		addWindowListener(this);
		setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		this.dispose();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}
