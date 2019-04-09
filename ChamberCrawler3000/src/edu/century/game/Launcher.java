package edu.century.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.century.game.display.CombinedPanel;
import edu.century.game.display.ComboboxToolTipRenderer;
import edu.century.game.entity.Player;
import edu.century.game.entity.race.Race;
import edu.century.game.entity.race.player_races.*;

public class Launcher extends JFrame implements ActionListener
{
	private static Launcher launcher;

	boolean useDPad = true;

	Player player;
	String playerName;
	Race playerRace;

	JPanel inputPanel, optionsPanel, characterPanel, bottomPanel, bottomLeftPanel, bottomRightPanel, playerNamePanel;
	JLabel optionsLabel;
	JButton startButton, floorEditorButton;
	JCheckBox useDPadCheckBox;
	JComboBox<String> raceComboBox;
	JTextField playerNameField;

	private int launcherWidth, launcherHeight;
	private int gameWidth, gameHeight;

	public static void main(String[] args)
	{
		launcher = new Launcher(400, 400);

		// Game game = new Game("Chamber Crawler 3000", 640, 360, true);
		// game.start();
	}

	public Launcher(int width, int height)
	{
		super("CC3K Launcher");
		this.launcherWidth = width;
		this.launcherHeight = height;

		buildLauncher();
	}

	private void buildLauncher()
	{
		setSize(launcherWidth, launcherHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Actually exit on
														// close
		setResizable(false); // Not resizable
		setLocationRelativeTo(null); // Centered on screen
		setLayout(new BorderLayout());

		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 1));
		add(inputPanel, BorderLayout.NORTH);

		buildOptionsPanel();
		buildCharacterPanel();

		buildBottomPanel();

		setVisible(true);
	}

	private void buildBottomPanel()
	{
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		this.add(bottomPanel, BorderLayout.SOUTH);

		bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(bottomLeftPanel);
		
		floorEditorButton = new JButton("Launch Floor Editor");
		floorEditorButton.addActionListener(this);
		bottomLeftPanel.add(floorEditorButton);
		
		bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(bottomRightPanel);
		
		startButton = new JButton("Start Game");
		startButton.addActionListener(this);
		bottomRightPanel.add(startButton);
	}

	private void buildOptionsPanel()
	{
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(0, 3));
		optionsPanel.setBorder(BorderFactory.createTitledBorder("Game Options"));
		optionsPanel.setPreferredSize(new Dimension(launcherWidth - 16, 80));
		inputPanel.add(optionsPanel);

		useDPadCheckBox = new JCheckBox("Use DPad");
		useDPadCheckBox.setSelected(true);
		useDPadCheckBox.addActionListener(this);
		optionsPanel.add(useDPadCheckBox);

		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
	}

	private void buildCharacterPanel()
	{
		characterPanel = new JPanel();
		characterPanel.setLayout(new GridLayout(0, 2));
		characterPanel.setBorder(BorderFactory.createTitledBorder("Character Creation"));
		characterPanel.setPreferredSize(new Dimension(launcherWidth - 16, 20));
		inputPanel.add(characterPanel);

		playerNameField = new JTextField("Adventurer");
		playerNameField.addActionListener(this);
		playerNameField.setColumns(12);
		playerNamePanel = new CombinedPanel(new JLabel("Name"), playerNameField);
		characterPanel.add(playerNamePanel);

		raceComboBox = new JComboBox<String>();
		ComboboxToolTipRenderer raceComboBoxRenderer = new ComboboxToolTipRenderer();
		raceComboBox.setRenderer(raceComboBoxRenderer);
		List<String> raceComboBoxTooltips = new ArrayList<String>();
		for(int i = 0; i < Race.playerRaceStrings.length; i++)
		{
			raceComboBox.addItem(Race.playerRaceStrings[i]);
			raceComboBoxTooltips.add(Race.playerRaceDescriptions[i]);
		}
		raceComboBoxRenderer.setTooltips(raceComboBoxTooltips);
		raceComboBox.setEditable(false);
		raceComboBox.setSelectedItem(raceComboBox.getItemAt(0));
		raceComboBox.addActionListener(this);
		characterPanel.add(new CombinedPanel(new JLabel("Race"), raceComboBox));
	}

	private void startGame(int width, int height, boolean useDPad, Player player)
	{
		this.setVisible(false);

		Game game = new Game("Chamber Crawler 3000", width, height, useDPad, player);
		game.start();

		this.dispose();
	}
	
	private void startFloorEditor()
	{
		//TODO: implement a floor editor
	}

	private Race resolvePlayerRace(String str)
	{
		if (str.equals("Drow"))
		{
			return new Drow();
		} else if (str.equals("Goblin"))
		{
			return new Goblin();
		} else if (str.equals("Troll"))
		{
			return new Troll();
		} else if (str.equals("Vampire"))
		{
			return new Vampire();
		} else
		{
			return new Shade();
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		JComponent actionSource = (JComponent) actionEvent.getSource();
		if (actionSource.equals(startButton))
		{
			playerName = playerNameField.getText();
			playerRace = resolvePlayerRace((String) raceComboBox.getSelectedItem());

			player = new Player(null, playerRace, playerName);

			// TODO: have this invocation use width and height variables
			startGame(640, 360, useDPad, player);
		} else if (actionSource.equals(useDPadCheckBox))
		{
			useDPad = useDPadCheckBox.isSelected();
		} else if(actionSource.equals(floorEditorButton));
		{
			startFloorEditor();
		}
	}
}