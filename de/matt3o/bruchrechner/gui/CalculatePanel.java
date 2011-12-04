package de.matt3o.bruchrechner.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatePanel extends JPanel {
	private static final long serialVersionUID = 606017820626378690L;
	private boolean displayInfo;
	
	public CalculatePanel() {
		this( true );
	}
	
	public CalculatePanel( boolean displayInfo ){
		this.displayInfo = displayInfo;
		setLayout( new FlowLayout( FlowLayout.LEFT ) );
		
		addComponenten();	
	}
	
	private void addComponenten(){
		JPanel panel = new JPanel();
		panel.setLayout( new GridLayout( 2, 2 ) );
		
		if ( displayInfo )
			panel.add( new JLabel( "ZŠhler:" ) );
		panel.add( new JTextField( 3 ) );
		if ( displayInfo )
			panel.add( new JLabel( "Nenner:" ) );
		panel.add( new JTextField( 3 ) );
		
		add( panel );
	}
}
