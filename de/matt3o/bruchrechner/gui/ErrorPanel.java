package de.matt3o.bruchrechner.gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorPanel extends JPanel {
	private static final long serialVersionUID = 6260833299695041591L;
	private String errorMessage;
	private boolean isErrorSet;
	
	public ErrorPanel( Object errorMessage ) {
		this.errorMessage = errorMessage.toString();
		this.isErrorSet = true;
				
		addComponenten();
		setError( true );
	}

	public ErrorPanel() {
		setError( false );

	}

	private void addComponenten() {
		JLabel messageLabel = new JLabel( errorMessage );
		messageLabel.setForeground( Color.RED );
		
		add( messageLabel );
	}
	
	public boolean isErrorSet(){
		return isErrorSet;
	}
	
	public void setError( boolean isErrorSet ){
		this.isErrorSet = isErrorSet;
	}
}
