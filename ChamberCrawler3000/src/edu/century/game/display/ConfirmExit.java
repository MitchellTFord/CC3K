package edu.century.game.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfirmExit extends JFrame implements ActionListener
{
	private JLabel messageLabel = new JLabel("Are you sure you want to exit?");
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	public ConfirmExit()
	{
		super("Confirm Exit");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((new Dimension(300, 150)));
		
		add(new CombinedPanel(messageLabel), BorderLayout.CENTER);
		add(new CombinedPanel(yesButton, noButton), BorderLayout.SOUTH);
		
		yesButton.addActionListener(this);
		noButton.addActionListener(this);
		
		setVisible(true);
	}
	
	public ConfirmExit(JComponent parent)
	{
		this();
		setLocationRelativeTo(parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		JComponent actionSource = (JComponent) actionEvent.getSource();
		if(actionSource.equals(yesButton))
		{
			System.exit(0);
		}
		else if(actionSource.equals(noButton))
		{
			dispose();
		}
	}
}
