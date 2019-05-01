package edu.century.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.century.game.display.CombinedPanel;
import edu.century.game.display.ComboboxToolTipRenderer;
import edu.century.game.entity.Player;
import edu.century.game.entity.race.Race;
import edu.century.game.floor.Floor;
import edu.century.game.floor.Floor.FloorFormatException;
import edu.century.game.graphics.Assets;

public class Launcher extends JFrame implements ActionListener
{
	// The Launcher object that will be used
	private static Launcher launcher;

	// Whether or not a directional pad should be used
	boolean useDPad = true;

	// The player object that will be passed into the new Game object
	private Player player;

	// The name of the new Player's Race
	private String playerName;

	// The new Player's Race object
	private Race playerRace;

	// The number of frames that should be rendered per second in game
	private int fps;
	
	//The loaded Floor
	private Floor floor;
	
	//The location of the Floor file
	private String floorFilePath;
	
	//The File containing Floor data
	private File floorFile;

	JPanel inputPanel, optionsPanel, characterPanel, floorPanel, bottomPanel, bottomLeftPanel, bottomRightPanel, playerNamePanel,
			fpsPanel;
	JLabel optionsLabel, raceIconLabel;
	JButton startButton, floorEditorButton, floorBrowseButton;
	JCheckBox useDPadCheckBox;
	JComboBox<String> raceComboBox;
	JComboBox<Integer> fpsComboBox;
	JTextField playerNameField, floorFileTextField;
	JFileChooser floorFileChooser;
	ImageIcon raceLabel;

	// The width/height of the launcher in pixels
	private int launcherWidth, launcherHeight;

	// The width/height of the game in pixels
	private int gameWidth, gameHeight;

	// The main method for this whole project
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				launcher = new Launcher(400, 400);
			}
		});
	}

	/**
	 * Constructor for Launcher
	 * 
	 * @param width  the width of the launcher in pixels
	 * @param height the height of the launcher in pixels
	 */
	public Launcher(int width, int height)
	{
		super("CC3K Launcher");
		this.launcherWidth = width;
		this.launcherHeight = height;

		System.out.println("Launcher created");
		
		Assets.init();
		
		buildLauncher();
	}

	/**
	 * Build the GUI elements of the launcher
	 */
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

		// Build the options panel
		buildOptionsPanel();

		// Build the character-creation panel
		buildCharacterPanel();

		//Build the floor panel
		buildFloorPanel();
		
		// Build the panel at the bottom which contains the floor editor and start
		// buttons
		buildBottomPanel();

		// Set this window to visible
		// This must be at the end of this method or GUI elements may not appear
		setVisible(true);
	}

	/**
	 * Build the bottom panel with the floor editor and start buttons
	 */
	private void buildBottomPanel()
	{
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		this.add(bottomPanel, BorderLayout.SOUTH);

		bottomLeftPanel = new JPanel();
		bottomLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(bottomLeftPanel);

		// Floor editor button
		floorEditorButton = new JButton("Launch Floor Editor");
		floorEditorButton.addActionListener(this);
		bottomLeftPanel.add(floorEditorButton);

		bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(bottomRightPanel);

		// Start game button
		startButton = new JButton("Start Game");
		startButton.addActionListener(this);
		bottomRightPanel.add(startButton);
	}

	/**
	 * Build the game options panel
	 */
	private void buildOptionsPanel()
	{
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(0, 3));
		optionsPanel.setBorder(BorderFactory.createTitledBorder("Game Options"));
		optionsPanel.setPreferredSize(new Dimension(launcherWidth - 16, 100));
		inputPanel.add(optionsPanel);

		// Use DPad check box
		useDPadCheckBox = new JCheckBox("Use DPad");
		useDPadCheckBox.setSelected(true);
		useDPadCheckBox.addActionListener(this);
		optionsPanel.add(useDPadCheckBox);

		// FPS combo box
		Integer[] fpsOptions = { 15, 30, 45, 60 };
		fpsComboBox = new JComboBox<Integer>(fpsOptions);
		fpsComboBox.setEditable(false);
		fpsComboBox.setSelectedIndex(1);
		fpsComboBox.addActionListener(this);
		fpsPanel = new CombinedPanel(new JLabel("FPS"), fpsComboBox);
		optionsPanel.add(fpsPanel);

		// Temporary place-holders
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
		optionsPanel.add(new JLabel("placeholder"));
	}

	/**
	 * Build the character-creation panel
	 */
	private void buildCharacterPanel()
	{
		characterPanel = new JPanel();
		characterPanel.setLayout(new GridLayout(1, 2));
		characterPanel.setBorder(BorderFactory.createTitledBorder("Character Creation"));
		characterPanel.setPreferredSize(new Dimension(launcherWidth - 16, 20));
		inputPanel.add(characterPanel);

		// Character name field with label
		playerNameField = new JTextField("Adventurer");
		playerNameField.addActionListener(this);
		playerNameField.setColumns(12);
		playerNamePanel = new CombinedPanel(new JLabel("Name"), playerNameField);
		characterPanel.add(playerNamePanel);

		// Character race combo box with tooltips
		raceComboBox = new JComboBox<String>();
		ComboboxToolTipRenderer raceComboBoxRenderer = new ComboboxToolTipRenderer();
		raceComboBox.setRenderer(raceComboBoxRenderer);
		List<String> raceComboBoxTooltips = new ArrayList<String>();
		for(int i = 0; i < Race.playerRaces.length; i++)
		{
			raceComboBox.addItem(Race.playerRaces[i].getRaceName());
			raceComboBoxTooltips.add(Race.playerRaces[i].toString());
		}
		raceComboBoxRenderer.setTooltips(raceComboBoxTooltips);
		raceComboBox.setEditable(false);
		raceComboBox.setSelectedIndex(0);
		raceComboBox.addActionListener(this);
		
		raceIconLabel = new JLabel(Race.playerRaces[raceComboBox.getSelectedIndex()].getRaceIcon());
		
		characterPanel.add(new CombinedPanel(new JLabel("Race"), raceComboBox, raceIconLabel));
	}
	
	private void buildFloorPanel()
	{
		floorPanel = new JPanel();
		floorPanel.setLayout(new GridLayout(1, 2));
		floorPanel.setBorder(BorderFactory.createTitledBorder("Floor Selection"));
		floorPanel.setPreferredSize(new Dimension(launcherWidth - 16, 120));
		
		JPanel floorPanelRow0 = new JPanel(new FlowLayout());
		floorPanel.add(floorPanelRow0);
		
		floorFilePath = null;
	
		//Floor file browse button
		floorBrowseButton = new JButton("Browse");
		floorBrowseButton.addActionListener(this);
		floorPanelRow0.add(floorBrowseButton);
		
		//File name text field
		floorFileTextField = new JTextField("testFloor1.floor");
		floorFileTextField.setEditable(false);
		floorFileTextField.setColumns(10);
		floorPanelRow0.add(floorFileTextField);
		
		// File chooser
		floorFileChooser = new JFileChooser();
		floorFileChooser.setFileFilter(new FileNameExtensionFilter("Floor Files", "txt", "floor"));
		floorFileChooser.addActionListener(this);
		
		add(floorPanel);
	}

	/**
	 * Start the game with the given parameters, dispose of this launcher window
	 * 
	 * @param width   the width of the Game window in pixels
	 * @param height  the height of the Game window in pixels
	 * @param useDPad whether or not a DPad should be used
	 * @param player  the new player object
	 */
	private void startGame(int width, int height, boolean useDPad, Player player, Floor floor)
	{
		// Make this window invisible
		this.setVisible(false);
		
		// Create a new Game object and invoke its start() method
		Game game = new Game("Chamber Crawler 3000", width, height, useDPad, player, fps, floor);
		game.start();

		// Dispose of this launcher object
		this.dispose();
	}

	/**
	 * Launches the floor editor
	 */
	private void startFloorEditor()
	{
		// TODO: implement a floor editor
	}

	/**
	 * Resolves a String to a Race
	 * 
	 * @param str the string to resolve a Race object from
	 * @return a Race object corresponding to the given string
	 */
	private Race resolvePlayerRace(String str)
	{
		if(str.equals("Drow"))
		{
			return Race.playerRaces[1];
		} else if(str.equals("Goblin"))
		{
			return Race.playerRaces[2];
		} else if(str.equals("Troll"))
		{
			return Race.playerRaces[3];
		} else if(str.equals("Vampire"))
		{
			return Race.playerRaces[4];
		} else
		{
			// Return Shade by default
			return Race.playerRaces[0];
		}
	}

	@Override
	/**
	 * Handles all the actions in the launcher window
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
		// The component that the ActionEvent came from
		JComponent actionSource = (JComponent) actionEvent.getSource();

		// Determines which component the ActionEvent came from
		// and executes the appropriate code
		if(actionSource.equals(startButton))
		{
			playerName = playerNameField.getText();
			playerRace = resolvePlayerRace((String) raceComboBox.getSelectedItem());
			useDPad = useDPadCheckBox.isSelected();
			fps = (Integer) fpsComboBox.getSelectedItem();
			player = new Player(null, playerRace, playerName);

			if(floor == null)
			{
				try
				{
					floor = new Floor(Assets.testFloor1);
				} catch(NumberFormatException | FileNotFoundException | FloorFormatException e)
				{
					e.printStackTrace();
					System.exit(1);
				}
				
				
				// Launch the game
				startGame(640, 360, useDPad, player, floor);
			}
		} else if(actionSource.equals(floorEditorButton))
		{
			// Launch the floor editor
			startFloorEditor();
		} else if(actionSource.equals(raceComboBox))
		{
			raceIconLabel.setIcon(Race.playerRaces[raceComboBox.getSelectedIndex()].getRaceIcon());
		} else if(actionSource.equals(floorBrowseButton))
		{
			// Open the file chooser and get the return value
			int returnValue = floorFileChooser.showOpenDialog(getParent());

			// If the user pressed "OK" in the file chooser dialog
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				// Store the chosen file as a file object
				floorFile = floorFileChooser.getSelectedFile();
				
				//Attempt to load the chosen floor file
				try 
				{
					floor = new Floor(floorFile);
					
					//Set the text of floorFileTextField to the name of the chosen file
					floorFileTextField.setText(floorFile.getName());
				} catch (FileNotFoundException | FloorFormatException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}