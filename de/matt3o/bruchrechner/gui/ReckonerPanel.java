package de.matt3o.bruchrechner.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.matt3o.bruchrechner.Fraction;
import de.matt3o.bruchrechner.FractionException;
import de.matt3o.bruchrechner.MathException;

public class ReckonerPanel extends JPanel {
	private static final long serialVersionUID = 5312719316647891733L;
	private JPanel buttonPanel;
	private JButton reckonerButton;
	private OperationPanel operationPanel;
	private CalculatePanel value1Panel;
	private CalculatePanel value2Panel;
	private ReturnPanel returnPanel;
	private ErrorPanel errorPanel;
	private JFrame mainWindow;
	
	public ReckonerPanel( JFrame mainWindow ) {
		this.mainWindow = mainWindow;
		
		setLayout( new BorderLayout() );
		new JPanel();
		operationPanel = new OperationPanel();
		
		value1Panel = new CalculatePanel( false );
		value2Panel = new CalculatePanel( false );
		
		try {
			returnPanel = new ReturnPanel();
		} catch ( FractionException e ) {
			e.printStackTrace();
		}

		reloadReturnPanel();
	}
	
	public void reloadReturnPanel(){
		removeAll();
		
		JPanel calculatePanel = new JPanel();
		JPanel numberatorPanel = new JPanel();
		calculatePanel.setLayout( new BoxLayout( calculatePanel, BoxLayout.X_AXIS ) );
		
		numberatorPanel.add( value1Panel );
		numberatorPanel.add( operationPanel );
		numberatorPanel.add( value2Panel );
		
		calculatePanel.add( numberatorPanel );
	
		if ( returnPanel.hasReturn() ){
			calculatePanel.add( new DividerPanel() );
			calculatePanel.add( returnPanel );
		}
		
		buttonPanel = new JPanel();
		reckonerButton = new JButton( "Berechnen" );
		
		buttonPanel.add( reckonerButton );
		
		add( BorderLayout.NORTH, calculatePanel );
		add( BorderLayout.SOUTH, buttonPanel );
		
		setVisible( true );
		addActionListeners();
	}
	
	private void addActionListeners(){
		reckonerButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeErrorMessage();
				
				try{
					int numerator1 = Integer.parseInt( value1Panel.getNumerator().toString() );
					int numerator2 = Integer.parseInt( value2Panel.getNumerator().toString() );
					int denominator1 = Integer.parseInt( value1Panel.getDenominator().toString() );
					int denominator2 = Integer.parseInt( value2Panel.getDenominator().toString() );
					
					Fraction newFraction1 = new Fraction( numerator1, denominator1 );
					Fraction newFraction2 = new Fraction( numerator2, denominator2 );
					
					if ( operationPanel.getOperation() == "+" ){
						returnPanel = new ReturnPanel( newFraction1.addition( newFraction2 ) );
					} else if ( operationPanel.getOperation() == "-" ){
						returnPanel = new ReturnPanel( newFraction1.subtract( newFraction2 ) );
					} else if ( operationPanel.getOperation() == "*" ){
						returnPanel = new ReturnPanel( newFraction1.multiplicat( newFraction2 ) );
					} else if ( operationPanel.getOperation() == "/" ){
						returnPanel = new ReturnPanel( newFraction1.division( newFraction2 ) );
					}
				} catch( NumberFormatException ne ){
					addErrorMessage( "Der Bruch darf nur Zahlen enthalten." );
					return;
				} catch ( MathException me ) {
					addErrorMessage( me.getMessage() );
					return;
				}
				
				reloadReturnPanel();
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
