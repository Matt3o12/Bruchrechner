package de.matt3o.bruchrechner.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.matt3o.bruchrechner.Fraction;
import de.matt3o.bruchrechner.MathException;

public class TrimPanel extends JPanel {
	private static final long serialVersionUID = 1536216821348697033L;
	private JPanel trimTextPanel, trimButtonPanel;
	private JButton calculateButton;
	private ReturnPanel returnPanel;
	private ErrorPanel errorPanel;
	private JFrame mainWindow;
	private CalculatePanel calculatePanel;

	
	public TrimPanel( JFrame mainWindow ) {
		this.mainWindow = mainWindow;
		
		calculateButton = new JButton( "Berechnen" );
		calculatePanel = new CalculatePanel();
		
		try{
			returnPanel = new ReturnPanel();
		} catch( Exception e ){
			e.printStackTrace();
		}
		
		reload();
		addActionListeners();
	}
	
	public void reload(){
		trimTextPanel = new JPanel();
		trimButtonPanel = new JPanel();
		removeAll();
		
		setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );
		trimTextPanel.setBorder( BorderFactory.createEmptyBorder( 0, 20, 20, 0 ) );
		trimTextPanel.setLayout( new BoxLayout( trimTextPanel, BoxLayout.X_AXIS ) );
		trimTextPanel.add( BorderLayout.WEST, calculatePanel );
				
//		JButton testButton = new JButton( "Test Button" );
		
		trimButtonPanel.add( calculateButton );
//		trimButtonPanel.add( testButton ); 
		
		if ( returnPanel.hasReturn() ){
			trimTextPanel.add( BorderLayout.CENTER, new DividerPanel() );
			trimTextPanel.add( BorderLayout.EAST, returnPanel );	
		}
				
		add( BorderLayout.WEST, trimTextPanel );		
		add( BorderLayout.SOUTH, trimButtonPanel );
	}
	
	private void addActionListeners() {
		calculateButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeErrorMessage();
				
				try{
					int numerator = Integer.parseInt( calculatePanel.getNumerator().toString() );
					int denominator = Integer.parseInt( calculatePanel.getDenominator().toString() );
					Fraction newFraction = new Fraction( numerator, denominator );
					
					returnPanel = new ReturnPanel( newFraction.trim() );
				} catch( NumberFormatException ne ){
					addErrorMessage( "Der Bruch darf nur Zahlen enthalten." );
					return;
				} catch ( MathException me ) {
					addErrorMessage( me.getMessage() );
					return;
				}
				
				reload();
				setVisible( true );
			}
		});
		
	}
	
	private void addErrorMessage( Object message ) {
		if ( errorPanel != null){
			if ( errorPanel.isErrorSet() ){
				removeErrorMessage();
			}
		}
		
		errorPanel = new ErrorPanel( message );
		mainWindow.setSize( mainWindow.getSize().width, mainWindow.getSize().height + 50 );
		
		add( errorPanel );
		
		if ( isVisible() ){
			setVisible( true );
		}
	}

	private void removeErrorMessage() {
		if ( errorPanel == null ){
			return;
		}
		
		if ( !errorPanel.isErrorSet() ){
			return;
		}
		
		remove( errorPanel );
		errorPanel = new ErrorPanel();
		
		mainWindow.setSize( mainWindow.getSize().width, mainWindow.getSize().height - 50 );
		
		if ( isVisible() ){
			setVisible( true );
		}
	}
}
