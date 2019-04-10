package edu.century.game.display;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Mitchell Ford
 */
public class CombinedPanel extends JPanel
{
	/**
	 * Constructor for CombinedPanel
	 * @param a The component to be put on the left
	 * @param b The component to be put on the right
	 */
	public CombinedPanel(JComponent a, JComponent b)
	{
		super(new FlowLayout(FlowLayout.CENTER));
		add(a);
		add(b);
	}
}
