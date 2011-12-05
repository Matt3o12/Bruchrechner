package de.matt3o.bruchrechner.gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class OperationPanel extends JPanel {
	private static final long serialVersionUID = -2574604368004988371L;
	private JComboBox comboBox;

	public OperationPanel() {
		reload();
	}
	
	public void reload(){
		String[] possibleOperations = {"+", "-", "*", ":"};
		comboBox = new JComboBox( possibleOperations );
		
		add( comboBox );	
	}
	
	public String getOperation(){
		return comboBox.getSelectedItem().toString();
	}
}
