package edu.century.game.display;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CombinedPanel extends JPanel
{
	public CombinedPanel(JComponent a, JComponent b)
	{
		super(new FlowLayout(FlowLayout.RIGHT));
		add(a);
		add(b);
	}
}
