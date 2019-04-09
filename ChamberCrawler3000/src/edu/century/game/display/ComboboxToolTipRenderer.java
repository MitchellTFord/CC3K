package edu.century.game.display;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

public class ComboboxToolTipRenderer extends DefaultListCellRenderer
{
	List<String> tooltips;
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		JComponent component = (JComponent) super.getListCellRendererComponent(list, 
				value, index, isSelected, cellHasFocus);
		
		if(index > -1 && value != null && tooltips != null)
		{
			list.setToolTipText(tooltips.get(index));
		}
		
		return component;
	}
	
	public void setTooltips(List<String> tooltips)
	{
		this.tooltips = tooltips;
	}
}
